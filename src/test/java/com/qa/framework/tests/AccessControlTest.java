package com.qa.framework.tests;

import com.qa.framework.base.BaseTest;
import com.qa.framework.pages.LoginPage;
import com.qa.framework.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.logging.Logger;

public class AccessControlTest extends BaseTest {
    private static final Logger log = Logger.getLogger(AccessControlTest.class.getName());
    @Test
    public void verifyUserCannotAccessProductPgWithoutLogin(){

        log.info("Starting access control test - unauthorized product page access");

        String inventoryUrl = prop.getProperty("url") + "inventory.html";
        log.info("Navigating directly to: " + inventoryUrl);

        driver.get(inventoryUrl);

        LoginPage loginPage = new LoginPage(driver);
        log.info("LoginPage initialized after redirect");

        String actualError = loginPage.getErrorMessage();
        log.info("Captured error message: " + actualError);

        Assert.assertEquals(actualError,
                "Epic sadface: You can only access '/inventory.html' when you are logged in.");
        log.info("Error message assertion passed");

        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
        log.info("Verified login button is displayed - user is on login page");

        log.info("Access control test completed successfully");
    }

}
