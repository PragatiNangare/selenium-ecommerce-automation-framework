package com.qa.framework.tests;

import com.qa.framework.base.BaseTest;
import com.qa.framework.pages.CartPage;
import com.qa.framework.pages.CheckoutPage;
import com.qa.framework.pages.LoginPage;
import com.qa.framework.pages.ProductPage;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {
    private static final Logger logger = Logger.getLogger(CheckoutTest.class.getName());
    @Test
    public void verifyUserCanCompletePurchase(){

        logger.info("Starting checkout flow test");

        LoginPage loginPage = new LoginPage(driver);

        logger.info("Logging in with valid credentials");

        ProductPage productPage = loginPage
                .setUsername("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();

        logger.info("Adding product to cart");

        productPage.addProductToCart("Sauce Labs Backpack");

        logger.info("Opening cart page");

        CartPage cartPage = productPage.clickCartIcon();

        logger.info("Navigating to checkout");

        CheckoutPage checkoutPage = cartPage.clickCheckout();

        logger.info("Entering checkout information");

        checkoutPage
                .enterFirstName("John")
                .enterLastName("Doe")
                .enterPostalCode("1234");

        logger.info("Continuing to overview page");

        checkoutPage.clickContinue();
        System.out.println("Current URL after Continue: " + driver.getCurrentUrl());


        logger.info("Finishing the order");

        checkoutPage.clickFinish();

        String confirmationMessage = checkoutPage.getConfirmationMessage();

        logger.info("Verifying order confirmation message");

        Assert.assertEquals(confirmationMessage, "Thank you for your order!");

        logger.info("Checkout flow test completed successfully");
    }

    @Test
    public void verifyUserCanCancelCheckout(){

        logger.info("Starting cancel checkout test");

        LoginPage loginPage = new LoginPage(driver);

        logger.info("Logging in with valid credentials");

        ProductPage productPage = loginPage
                .setUsername("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();

        logger.info("Adding product to cart");

        productPage.addProductToCart("Sauce Labs Backpack");

        logger.info("Opening cart page");

        CartPage cartPage = productPage.clickCartIcon();

        logger.info("Navigating to checkout page");

        CheckoutPage checkoutPage = cartPage.clickCheckout();

        logger.info("Cancelling checkout");

        cartPage = checkoutPage.clickCancel();

        logger.info("Verifying user is redirected back to cart page");

        Assert.assertTrue(cartPage.isProductDisplayed("Sauce Labs Backpack"));

        logger.info("Cancel checkout test completed successfully");
    }







}

