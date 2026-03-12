package com.qa.framework.pages;

import com.qa.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.*;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstName){
        WebElement firstNameField= waitUtil.waitForVisibility(By.id("first-name"));
        firstNameField.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName){
        WebElement lastNameField = waitUtil.waitForVisibility(By.id("last-name"));
        lastNameField.sendKeys(lastName);
        return this;
    }

    public CheckoutPage enterPostalCode(String postalCode){
        WebElement postalCodeField = waitUtil.waitForVisibility(By.id("postal-code"));
        postalCodeField.sendKeys(postalCode);
        return this;
    }

    public CheckoutPage clickContinue(){
        WebElement continueBtn = waitUtil.waitForClickable(By.id("continue"));
        continueBtn.click();

        waitUtil.waitForVisibility(By.id("finish"));

        return this;
    }

    public CheckoutPage clickFinish(){
        WebElement finishBtn = waitUtil.waitForClickable(By.id("finish"));
        finishBtn.click();
        return this;

    }

    public  String getConfirmationMessage(){
        WebElement confirmationMessage = waitUtil.waitForVisibility(By.className("complete-header"));
         return confirmationMessage.getText();

    }

    public CartPage clickCancel(){
        WebElement cancelBtn = waitUtil.waitForClickable(By.id("cancel"));
        cancelBtn.click();
        return new CartPage(driver);
    }









}
