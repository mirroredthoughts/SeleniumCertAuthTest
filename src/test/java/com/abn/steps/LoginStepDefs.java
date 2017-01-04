package com.abn.steps;

import com.abn.pages.LoginPage;
import com.abn.utils.CertAuthentication;
import com.abn.utils.DriverUtil;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class LoginStepDefs extends BaseStepDefs {
    LoginPage loginPage;

    @After
    public void afterScenario(Scenario scenario) throws Exception {
        if(scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot,"image/png");
            driver.quit();
        } else
            driver.quit();
    }


    @Given("^User login as \"([^\"]*)\"$")
    public void userLoginAs(String arg0) throws Throwable {
        new CertAuthentication().callCertificate("certs/test.p12");
        driver = new DriverUtil().getDriver();
        driver.get("http://accountsdemo.herokuapp.com/users/sign_in");
        Thread.sleep(5000);
        loginPage = new LoginPage(driver);
    }
}
