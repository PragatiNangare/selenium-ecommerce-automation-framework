package com.qa.framework.tests;

import com.qa.framework.base.BaseTest;
import com.qa.framework.pages.CartPage;
import com.qa.framework.pages.LoginPage;
import com.qa.framework.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.logging.Logger;

public class AddToCartTest extends BaseTest {
    private static final Logger log = Logger.getLogger(AddToCartTest.class.getName());
    @Test
    public void verifyUserCanAddProductToCart(){
        log.info("Starting Add To Cart Test");
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage
                .setUsername("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();
        log.info("User logged in successfully");

        log.info("Adding product: Sauce Labs Backpack");
        productPage.addProductToCart("Sauce Labs Backpack");

        String badgeCount = productPage.getCartBadgeCount();
        log.info("Cart badge count is: " + badgeCount);
        Assert.assertTrue(badgeCount.contains("1"));
        log.info("Badge count assertion passed");

        log.info("Navigating to Cart page");
        CartPage cartPage = productPage.clickCartIcon();

        boolean isDisplayed = cartPage.isProductDisplayed("Sauce Labs Backpack");
        log.info("Product presence in cart: " + isDisplayed);

        Assert.assertTrue(isDisplayed);
        log.info("Product successfully verified in cart");

        log.info("Add To Cart Test completed successfully");
    }

    @Test
    public void verifyUserCanAddMultipleProductsToCart(){
        log.info("Starting Add To Cart Test for Multiple products");
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage
                .setUsername("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();
        log.info("User logged in successfully");

        log.info("Adding Products to cart");
        productPage.addProductToCart("Sauce Labs Backpack");
        productPage.addProductToCart("Sauce Labs Bike Light");


        String badgeCount = productPage.getCartBadgeCount();
        log.info("Cart badge count is: " + badgeCount);

        Assert.assertTrue(badgeCount.contains("2"));
        log.info("Badge count assertion passed");

        CartPage cartPage = productPage.clickCartIcon();
        log.info("Navigating to Cart page");
        boolean isDisplayed = cartPage.isProductDisplayed("Sauce Labs Backpack");
        Assert.assertTrue(isDisplayed);
        log.info("Product presence in cart: " + isDisplayed);
        boolean isBikeLightDisplayed = cartPage.isProductDisplayed("Sauce Labs Bike Light");
        Assert.assertTrue(isBikeLightDisplayed);
        log.info("Product presence in cart: " + isBikeLightDisplayed);
        log.info("Product successfully verified in cart");
        log.info("Add To Cart Test completed successfully");



    }

    @Test
    public void verifyUserCanRemoveProductFromCart(){
        log.info("Starting Remove Product Test");
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage
                .setUsername("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();
        log.info("User logged in successfully");

        log.info("Adding product before removing");
        productPage.addProductToCart("Sauce Labs Backpack");

        Assert.assertEquals(productPage.getCartBadgeCount(), "1");
        log.info("Badge verified as 1");

        CartPage cartPage = productPage.clickCartIcon();
        log.info("Navigated to Cart Page");

        cartPage.clickRemoveProduct("Sauce Labs Backpack");
        log.info("Product removed from cart");

        boolean isStillPresent = cartPage.isProductDisplayed("Sauce Labs Backpack");

        Assert.assertFalse(isStillPresent);
        log.info("Verified product is removed successfully");

    }

    @Test
    public void verifyUserCanContinueShopping(){
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage
                .setUsername("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();
        log.info("User logged in successfully");

        log.info("Adding product before removing");
        productPage.addProductToCart("Sauce Labs Backpack");
        CartPage cartPage = productPage.clickCartIcon();
        log.info("Navigated to Cart Page");
        productPage = cartPage.clickContinueShopping();

        log.info("Verifying user is redirected to inventory page");

        Assert.assertEquals(productPage.getProductPgTitle(), "Products");


    }



}
