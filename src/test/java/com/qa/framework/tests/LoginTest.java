package com.qa.framework.tests;
import com.qa.framework.base.BaseTest;
import com.qa.framework.pages.LoginPage;
import com.qa.framework.pages.ProductPage;
import com.qa.framework.utils.JsonDataReader;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.logging.Logger;


public class LoginTest extends BaseTest {
    private static final Logger log = Logger.getLogger(LoginTest.class.getName());

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return JsonDataReader.getInvalidLoginData();
    }

    @Test
    public void loginTest() {
        log.info("Starting valid login test");
        LoginPage loginPage = new LoginPage(driver);
        log.info("LoginPage initialized");

        ProductPage productPage = loginPage
                .setUsername("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();

        log.info("Clicked login button");

        String actualTitle = productPage.getProductPgTitle();
        log.info("Product page title fetched: " + actualTitle);

        Assert.assertEquals(actualTitle, "Products");
        log.info("Valid login test passed");
    }

    @Test(dataProvider = "invalidLoginData")
    public void invalidLogin(String username, String password, String expectedError){
        log.info("Starting invalid login test with username: " + username);

        LoginPage loginPage = new LoginPage(driver);

        loginPage
                .setUsername(username)
                .setPassword(password)
                .clickLogin();
        log.info("Login attempted with invalid credentials");


        String actualError = loginPage.getErrorMessage();
        log.info("Error message captured: " + actualError);

        Assert.assertEquals(actualError, expectedError);

        log.info("Invalid login validation completed successfully");

    }
}








