package com.qa.framework.pages;

import com.qa.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver){
        super(driver);
    }

    public String getProductPgTitle(){
        WebElement ProductPgTitle = waitUtil.waitForVisibility(By.className("title"));
        return ProductPgTitle.getText();
    }

    public ProductPage addProductToCart(String productName){
        String formattedName = productName.toLowerCase();
        formattedName = formattedName.replace(" ","-");
        String id = "add-to-cart-" + formattedName;
        WebElement productAdded = waitUtil.waitForClickable(By.id(id));
        productAdded.click();
        return this;
    }

    public String getCartBadgeCount(){
        WebElement badge = waitUtil.waitForVisibility(By.className("shopping_cart_badge"));
        return badge.getText();
    }

    public CartPage clickCartIcon(){
       WebElement cartIcon =  waitUtil.waitForClickable(By.className("shopping_cart_link"));
       cartIcon.click();
        return new CartPage(driver);
    }






}
