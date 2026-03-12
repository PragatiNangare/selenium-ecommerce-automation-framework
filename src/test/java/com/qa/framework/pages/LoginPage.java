package com.qa.framework.pages;

import com.qa.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver){
    super(driver);
    }
    public LoginPage setUsername(String enterUsername){
        WebElement username = waitUtil.waitForVisibility(By.id("user-name"));
        username.sendKeys(enterUsername);
        return this;
    }

    public String getEnteredUsername(){
        WebElement username = waitUtil.waitForVisibility(By.id("user-name"));
        return username.getAttribute("value");
    }

    public LoginPage setPassword(String enterPassword){
        WebElement password = waitUtil.waitForVisibility(By.id("password"));
        password.sendKeys(enterPassword);
        return this;
    }

    public String getEnteredPassword(){
        WebElement password = waitUtil.waitForVisibility(By.id("password"));
        return password.getAttribute("value");

    }

    public boolean isLoginButtonDisplayed() {
        return waitUtil.waitForVisibility(By.id("login-button")).isDisplayed();
    }

    public ProductPage clickLogin(){
        WebElement loginBtn = waitUtil.waitForClickable(By.id("login-button"));
        loginBtn.click();
        return new ProductPage(driver);
    }

    public String getProductPgTitle(){
        WebElement productPgTitle =
                waitUtil.waitForVisibility(By.className("title"));
        return productPgTitle.getText();
    }

    public String getErrorMessage(){
        WebElement errorMsg = waitUtil.waitForVisibility(By.cssSelector("h3[data-test = 'error']"));
        return errorMsg.getText();
    }

}
