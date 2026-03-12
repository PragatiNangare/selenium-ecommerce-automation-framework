package com.qa.framework.pages;

import com.qa.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver){
        super(driver);
    }

    public boolean isProductDisplayed(String productName){

        List<WebElement> products =
                driver.findElements(By.className("inventory_item_name"));

        for (WebElement product : products){
            if(product.getText().trim().equals(productName)){
                return true;
            }
        }

        return false;
    }

    public  CartPage clickRemoveProduct(String productName){
        String formattedName = productName.toLowerCase();
        formattedName = formattedName.replace(" ","-");
        String id = "remove-"+ formattedName;
        WebElement removedProduct = waitUtil.waitForClickable(By.id(id));
        removedProduct.click();
        return this;
    }

    public CheckoutPage clickCheckout(){
        WebElement checkoutBtn = waitUtil.waitForClickable(By.id("checkout"));
        checkoutBtn.click();
        return new CheckoutPage(driver);
    }

    public ProductPage clickContinueShopping(){
        WebElement continueShoppingBtn = waitUtil.waitForVisibility(By.id("continue-shopping"));
        continueShoppingBtn.click();
        return new ProductPage(driver);
    }






}
