package forms;

import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.WebFormPage;

import java.time.Duration;

public class FormsTest extends BaseTest {

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test(description = "Test complete web form submission with all fields", priority = 1)
    public void testCompleteWebFormSubmission() {
        System.out.println("Starting Web Form Test...");
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToWebForm();
        sleep(1000);

        WebFormPage webFormPage = new WebFormPage(driver);
        
        System.out.println("Entering first name...");
        webFormPage.enterFirstName("Christelle");
        sleep(800);
        
        System.out.println("Entering password...");
        webFormPage.enterPassword("Christellehere$$");
        sleep(800);
        
        System.out.println("Entering textarea...");
        webFormPage.enterTextarea("I am the coolest in the city lol!!");
        sleep(800);
        
        System.out.println("Selecting number from dropdown...");
        webFormPage.selectNumber("2");
        sleep(800);
        
        System.out.println("Checking checkbox...");
        webFormPage.checkCheckbox();
        sleep(800);
        
        System.out.println("Selecting radio button...");
        webFormPage.selectRadioButton();
        sleep(800);
        
        System.out.println("Submitting form...");
        webFormPage.submitForm();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("submitted"));
        
        System.out.println("Form submitted successfully!");
        sleep(2000);
        
        Assert.assertTrue(webFormPage.isFormSubmitted(), 
            "Form submission failed - URL does not contain 'submitted'");
    }

    @Test(description = "Test login form with valid credentials", priority = 2)
    public void testLoginFormWithValidCredentials() {
        System.out.println("Starting Login Form Test...");
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToLoginForm();
        sleep(2000);

        pages.LoginFormPage loginFormPage = new pages.LoginFormPage(driver);

        System.out.println("Current URL: " + driver.getCurrentUrl());

        System.out.println("Entering username...");
        loginFormPage.enterUsername("user");
        sleep(1000);
        
        System.out.println("Entering password...");
        loginFormPage.enterPassword("user");
        sleep(1000);
        
        System.out.println("Submitting login form...");
        loginFormPage.submitLogin();
        sleep(2000);

        System.out.println("Current URL after submit: " + driver.getCurrentUrl());
        System.out.println("Login successful!");
        sleep(2000);
        
        Assert.assertFalse(driver.getCurrentUrl().contains("login-form.html"), 
            "Should navigate away from login page after successful login");
    }

    @Test(description = "Test login form with invalid credentials", priority = 3)
    public void testLoginFormWithInvalidCredentials() {
        System.out.println("Starting Invalid Login Test...");
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToLoginForm();
        sleep(2000);

        pages.LoginFormPage loginFormPage = new pages.LoginFormPage(driver);

        System.out.println("Current URL: " + driver.getCurrentUrl());

        System.out.println("Entering invalid username...");
        loginFormPage.enterUsername("invalidUser");
        sleep(1000);
        
        System.out.println("Entering invalid password...");
        loginFormPage.enterPassword("invalidPassword");
        sleep(1000);
        
        System.out.println("Submitting login with invalid credentials...");
        loginFormPage.submitLogin();
        sleep(3000);

        System.out.println("Current URL after invalid login: " + driver.getCurrentUrl());
        System.out.println("Verifying login failed as expected...");

        Assert.assertTrue(driver.getCurrentUrl().contains("login-form.html"),
            "User should remain on login page with invalid credentials");
    }
}