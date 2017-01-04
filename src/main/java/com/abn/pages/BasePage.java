package com.abn.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.util.List;
import java.util.concurrent.TimeUnit;


public abstract class BasePage <P extends BasePage> {


    protected WebDriver driver;
    protected WebDriverWait waitTime;
    private static final String PAGE_TITLE = "";
    protected static final long ELEMENT_WAIT = 10;
    protected static final long IMPLICIT_WAIT = 20;
    protected static final int PAGE_LOAD_TIMEOUT = 30;
    protected static final int POLLING_RATE = 2;
    protected static final int SPINNER_TO_APPEAR_TIMEOUT = 5;
    protected static final int SPINNER_TO_DISAPPEAR_TIMEOUT = 30;
    protected static final int SPINNER_POLLING_RATE = 50;
    protected static final int INDEXING_TIMEOUT = 40;
    protected static final int INDEXING_POLLING_RATE = 5;


    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void implicitWaitMethod() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    protected void waitForElement(ExpectedCondition expectedCondition, WebElement element) {
        waitTime = new WebDriverWait(driver, ELEMENT_WAIT);
        waitTime.until(expectedCondition);
    }

    protected abstract void instantiatePage(P page);

    protected abstract ExpectedCondition<?> getPageLoadCondition();

    protected void waitForPageToLoad(ExpectedCondition<?> expectedCondition) {

        Wait wait = new FluentWait(driver)
                .withTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(POLLING_RATE, TimeUnit.SECONDS);
        wait.until(getPageLoadCondition());
    }

    public void scrollToPageBottom() throws Exception {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");
    }

    public void scrollToElement(WebElement element) throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void waitForJQuery() {
        (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                return (Boolean) js.executeScript("return !!window.jQuery && window.jQuery.active == 0");
            }
        });
    }

    public void enterText(WebElement webElement, String message) throws Exception {
        if (!(webElement == null)) {
            if (webElement.isDisplayed()) {
                webElement.clear();
                webElement.sendKeys(message);
            } else {

                throw new Exception("Text element not found");
            }
        }
    }

    public void clickButton(WebElement webElement) throws Exception {
        if (isElementPresent(webElement)) {
            if (webElement.isEnabled()) {
                webElement.click();
            } else {

                throw new Exception(webElement.toString() + " not clickable");
            }
        } else {

            throw new Exception(webElement.toString() + " not visible");
        }
    }

    public boolean isElementPresent(WebElement webElement) {
        try {
            webElement.isDisplayed();
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void clickByIndex(List<WebElement> element, int index) throws Exception {
        if (!(element == null)) {
            if (element.get(index).isDisplayed()) {
                element.get(index).click();
            } else {
                throw new Exception(element.get(index).toString() + "not clicked");
            }
        }
    }

    public void clickIcon(WebElement element, String message) throws Exception {
        if (isElementPresent(element)) {
            element.click();
        } else {
            throw new Exception(message + "not found");
        }
    }

    public void selectDropDownText(WebElement element, String textValue) throws Exception {
        try {
            Select selectValue = new Select(element);
            selectValue.selectByVisibleText(textValue);
        } catch (NoSuchElementException ex) {
            throw new Exception(textValue + "not found", ex);
        }
    }

    public boolean verifyPageLoadErrorNotPresent(WebElement element) throws Exception {
        boolean status = isElementPresent(element);
        if (status) {
            throw new Exception("Page Load Error");
        } else
            return status;
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

}

