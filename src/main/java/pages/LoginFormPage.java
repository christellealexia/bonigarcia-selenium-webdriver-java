package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginFormPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By submitButton = By.xpath("//button[@type='submit']");
    private By successMessage = By.id("success");
    private By errorMessage = By.cssSelector(".alert-danger, .error");

    public LoginFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void clickElement(WebElement element) {
        scrollToElement(element);
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public void enterUsername(String username) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
        scrollToElement(el);
        el.clear();
        el.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        scrollToElement(el);
        el.clear();
        el.sendKeys(password);
    }

    public void submitLogin() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        clickElement(btn);
    }

    public WebElement getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
    }

    public boolean hasErrorMessage() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement error = shortWait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        } catch (Exception e) {
            return null;
        }
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        submitLogin();
    }

    public String getUsernameValue() {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(usernameInput));
        return el.getAttribute("value");
    }

    public boolean isLoginSuccessful() {
        try {
            return getSuccessMessage().isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clearForm() {
        WebElement usernameEl = wait.until(ExpectedConditions.presenceOfElementLocated(usernameInput));
        WebElement passwordEl = wait.until(ExpectedConditions.presenceOfElementLocated(passwordInput));
        usernameEl.clear();
        passwordEl.clear();
    }
}