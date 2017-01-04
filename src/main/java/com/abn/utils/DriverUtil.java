package com.abn.utils;

import com.google.common.base.Splitter;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.SystemClock;

import java.awt.*;
import java.security.InvalidParameterException;
import java.util.List;


public class DriverUtil {

    private static WebDriver driver;
    protected DesiredCapabilities desiredCapabilities = null;
    protected ChromeOptions chromeOptions = null;

    public enum Browser {
        FIREFOX,
        CHROME,
        IE,
        HTMLUNIT,
    }

    public DriverUtil() {
        initializeSeleniumDriver(Browser.CHROME,"src/test/resources/chromeDriver.exe");
    }

    public void initializeSeleniumDriver(Browser browser , String driverPath) {

        switch(browser) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver",driverPath);
                loadChromeDesiredCapabilities();
                driver = new ChromeDriver(desiredCapabilities);
                try {
                    maximiseChromeBrowser(driver);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;

            case HTMLUNIT:
                break;

            default:
                throw new InvalidParameterException("Browser passed is not valid");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void loadChromeDesiredCapabilities() {
        desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(CapabilityType.PROXY,CertAuthentication.getProxy());
        desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
        chromeOptions = new ChromeOptions();

        List<String>chromeArguments = Splitter.on(",").trimResults().omitEmptyStrings()
                .splitToList("--safebrowsing-disable-extension-blacklist,--reduce-security-for-testing,--disable-extensions");

        chromeOptions.addArguments(chromeArguments);
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY,chromeOptions);
    }

    public void maximiseChromeBrowser(WebDriver driver) throws Exception {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int width = (int) toolkit.getScreenSize().getWidth();
        int height = (int) toolkit.getScreenSize().getHeight();
        driver.manage().window().setSize(new Dimension(width,height));
    }



}
