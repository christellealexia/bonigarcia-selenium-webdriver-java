package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DialogBoxesPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By alertButton = By.id("my-alert");

    private By confirmButton = By.id("my-confirm");

    private By promptButton = By.id("my-prompt");

    private By confirmText = By.id("confirm-text");

    private By promptText = By.id("prompt-text");

    public DialogBoxesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAlertButton() {
        wait.until(ExpectedConditions.elementToBeClickable(alertButton));
        driver.findElement(alertButton).click();
    }

    public Alert getAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public void clickConfirmButton() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        driver.findElement(confirmButton).click();
    }

    public void clickPromptButton() {
        wait.until(ExpectedConditions.elementToBeClickable(promptButton));
        driver.findElement(promptButton).click();
    }

    public String getConfirmText() {
        wait.until(ExpectedConditions.presenceOfElementLocated(confirmText));
        return driver.findElement(confirmText).getText();
    }

    public String getPromptText() {
        wait.until(ExpectedConditions.presenceOfElementLocated(promptText));
        return driver.findElement(promptText).getText();
    }
}
