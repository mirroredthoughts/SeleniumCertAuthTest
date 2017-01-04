package com.abn.pages;

import com.fasterxml.jackson.databind.deser.Deserializers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;


public class LoginPage extends BasePage<LoginPage> {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void implicitWaitMethod() {
        super.implicitWaitMethod();
    }

    @Override
    protected void waitForElement(ExpectedCondition expectedCondition, WebElement element) {
        super.waitForElement(expectedCondition, element);
    }

    @Override
    protected void instantiatePage(LoginPage page) {

    }

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return null;
    }

    @Override
    protected void waitForPageToLoad(ExpectedCondition<?> expectedCondition) {
        super.waitForPageToLoad(expectedCondition);
    }
}
