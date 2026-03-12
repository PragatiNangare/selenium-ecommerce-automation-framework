package com.qa.framework.base;
import com.qa.framework.utils.ConfigReader;
import com.qa.framework.utils.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import org.testng.ITestResult;
import java.lang.reflect.Method;


import java.util.Properties;
import com.aventstack.extentreports.*;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    private static final Logger logger = LogManager.getLogger(BaseTest.class);    protected WebDriver driver;
    protected Properties prop;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    public void captureScreenshot(String testName) {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        File destination = new File("screenshots/" + testName + ".png");

        try {
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot saved for failed test: " + testName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @BeforeSuite
    public void setupReport(){
        extent = ExtentManager.getExtentReports();
    }

    @BeforeMethod
    public void setup(Method method){

        test = extent.createTest(method.getDeclaringClass().getSimpleName() + " :: " + method.getName());

        logger.info("Initializing properties file...");
        prop = ConfigReader.initProperties();

        logger.info("Launching browser: " + prop.getProperty("browser"));

        if (prop.getProperty("browser").equals("chrome")){
            driver = new ChromeDriver();
            logger.info("Chrome browser launched successfully.");
        }

        driver.manage().window().maximize();
        logger.info("Browser window maximized.");

        logger.info("Navigating to URL: " + prop.getProperty("url"));
        driver.get(prop.getProperty("url"));
    }

    @AfterMethod
    public void tearDown(ITestResult result){

        if(result.getStatus() == ITestResult.FAILURE){
            logger.info("Test failed. Capturing screenshot...");
            test.fail(result.getThrowable());
            captureScreenshot(result.getName());
        }

        else if(result.getStatus() == ITestResult.SUCCESS){
            test.pass("Test passed successfully");
        }

        else if(result.getStatus() == ITestResult.SKIP){
            test.skip("Test skipped");
        }

        if(driver != null){
            logger.info("Closing browser...");
            driver.quit();
            logger.info("Browser closed successfully.");
        }
    }

    @AfterSuite
    public void flushReport(){
        extent.flush();
    }





}
