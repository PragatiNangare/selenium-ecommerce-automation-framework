package com.qa.framework.base;

import org.openqa.selenium.WebDriver;
import com.qa.framework.utils.WaitUtil;

public class BasePage {
    protected WebDriver driver;
    protected WaitUtil waitUtil;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);

    }

}
