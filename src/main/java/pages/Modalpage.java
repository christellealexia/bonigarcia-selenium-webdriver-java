package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Modalpage {
    private WebDriver driver;
    private WebDriverWait wait;

    // the button to launch the modal
    private By modalButton = By.id("my-modal");

    // modal content/body
    private By modalBody = By.className("modal-body");

    // close button in the modal footer (note: html uses "model-button" class in this app)
    private By closeButton = By.xpath("//button[contains(@class, 'btn-secondary') and contains(@class, 'model-button')]");

    // save button (also closes modal)
    private By saveButton = By.xpath("//button[@class='btn btn-primary model-button']");

    public Modalpage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // click the button to open the modal
    public void openModal() {
        wait.until(ExpectedConditions.elementToBeClickable(modalButton));
        driver.findElement(modalButton).click();
        // wait for modal to actually show up (it gets "show" class when visible)
        wait.until(ExpectedConditions.attributeContains(
            By.id("example-modal"), "class", "show"));
    }

    // get the modal element after its opened
    public WebElement getModal() {
        // find the modal by id and wait for it to be visible
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal")));
    }

    // close the modal using the close button
    public void closeModal() {
        wait.until(ExpectedConditions.elementToBeClickable(closeButton));
        driver.findElement(closeButton).click();
        // wait for modal to disappear (no longer has "show" class)
        wait.until((WebDriver d) -> {
            WebElement m = driver.findElement(By.id("example-modal"));
            String modalClass = m.getAttribute("class");
            return modalClass == null || !modalClass.contains("show");
        });
    }

    // get the modal body content
    public WebElement getModalContent() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalBody));
        return driver.findElement(modalBody);
    }

    // close modal using save button (also closes it)
    public void saveAndCloseModal() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        driver.findElement(saveButton).click();
        // wait for modal to close
        wait.until((WebDriver d) -> {
            WebElement m = driver.findElement(By.id("example-modal"));
            String modalClass = m.getAttribute("class");
            return modalClass == null || !modalClass.contains("show");
        });
    }
}
