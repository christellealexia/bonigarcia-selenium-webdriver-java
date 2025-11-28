package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WebFormPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstNameInput = By.id("my-text-id");
    private By passwordInput = By.name("my-password");
    private By textareaInput = By.name("my-textarea");
    private By numberSelect = By.name("my-select");
    private By checkbox = By.id("my-check-2");
    private By radioButton = By.id("my-radio-2");
    private By submitButton = By.xpath("//button[@type='submit']");
    private By successMessage = By.id("success");

    public WebFormPage(WebDriver driver) {
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

    public void enterFirstName(String firstName) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(firstNameInput));
        scrollToElement(el);
        el.clear();
        el.sendKeys(firstName);
    }

    public void enterPassword(String password) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        scrollToElement(el);
        el.clear();
        el.sendKeys(password);
    }

    public void enterTextarea(String text) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(textareaInput));
        scrollToElement(el);
        el.clear();
        el.sendKeys(text);
    }

    public void selectNumber(String value) {
        WebElement selectEl = wait.until(ExpectedConditions.presenceOfElementLocated(numberSelect));
        scrollToElement(selectEl);
        Select dropdown = new Select(selectEl);
        dropdown.selectByValue(value);
    }

    public void checkCheckbox() {
        WebElement cb = wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        if (!cb.isSelected()) {
            clickElement(cb);
        }
    }

    public void toggleCheckbox() {
        WebElement cb = wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        clickElement(cb);
    }

    public void selectRadioButton() {
        WebElement rb = wait.until(ExpectedConditions.elementToBeClickable(radioButton));
        clickElement(rb);
    }

    public void submitForm() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        clickElement(btn);
    }

    public boolean isFormSubmitted() {
        return driver.getCurrentUrl().contains("submitted");
    }

    public WebElement getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
    }

    public String getFirstNameValue() {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(firstNameInput));
        return el.getAttribute("value");
    }

    public String getPasswordValue() {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(passwordInput));
        return el.getAttribute("value");
    }

    public String getTextareaValue() {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(textareaInput));
        return el.getAttribute("value");
    }

    public String getSelectedNumberValue() {
        WebElement selectEl = wait.until(ExpectedConditions.presenceOfElementLocated(numberSelect));
        Select dropdown = new Select(selectEl);
        return dropdown.getFirstSelectedOption().getAttribute("value");
    }

    public boolean isCheckboxSelected() {
        WebElement cb = wait.until(ExpectedConditions.presenceOfElementLocated(checkbox));
        return cb.isSelected();
    }

    public boolean isRadioButtonSelected() {
        WebElement rb = wait.until(ExpectedConditions.presenceOfElementLocated(radioButton));
        return rb.isSelected();
    }
}