package com.abn.steps;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;

public abstract class BaseStepDefs {

    protected static WebDriver driver;

    @AfterSuite
    public void tearDown() throws Exception {
        driver.quit();
    }
}
