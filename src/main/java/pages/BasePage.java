package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

/**
 * BasePage - Extends HomePage and provides common utilities for all page objects
 * All specific page objects (FormsPage, AlertsPage, etc.) will extend this class
 */
public class BasePage extends HomePage {

    protected Actions actions;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        super(driver);
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
    }

    // ==================== WAIT STRATEGIES ====================

    /**
     * Wait for element to be visible
     */
    protected WebElement waitForVisibility(By locator, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable
     */
    protected WebElement waitForClickable(By locator, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for element to be invisible
     */
    protected boolean waitForInvisibility(By locator, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Wait for presence of all elements
     */
    protected List<WebElement> waitForAllElements(By locator, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    // ==================== CLICK ACTIONS ====================

    /**
     * Click element with wait
     */
    protected void clickElement(By locator) {
        waitForClickable(locator, 10).click();
    }

    /**
     * Click using JavaScript
     */
    protected void clickWithJS(By locator) {
        WebElement element = driver.findElement(locator);
        js.executeScript("arguments[0].click();", element);
    }

    // ==================== INPUT ACTIONS ====================

    /**
     * Type text into input field
     */
    protected void typeText(By locator, String text) {
        WebElement element = waitForVisibility(locator, 10);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Type text with Keys
     */
    protected void typeTextWithKeys(By locator, Keys key) {
        WebElement element = waitForVisibility(locator, 10);
        element.sendKeys(key);
    }

    /**
     * Get text from element
     */
    protected String getText(By locator) {
        return waitForVisibility(locator, 10).getText();
    }

    /**
     * Get attribute value
     */
    protected String getAttribute(By locator, String attribute) {
        return driver.findElement(locator).getAttribute(attribute);
    }

    // ==================== DROPDOWN ACTIONS ====================

    /**
     * Select dropdown by visible text
     */
    protected void selectDropdownByText(By locator, String text) {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByVisibleText(text);
    }

    /**
     * Select dropdown by value
     */
    protected void selectDropdownByValue(By locator, String value) {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByValue(value);
    }

    /**
     * Select dropdown by index
     */
    protected void selectDropdownByIndex(By locator, int index) {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByIndex(index);
    }

    /**
     * Get selected dropdown option text
     */
    protected String getSelectedDropdownText(By locator) {
        Select dropdown = new Select(driver.findElement(locator));
        return dropdown.getFirstSelectedOption().getText();
    }

    // ==================== HOVER ACTIONS ====================

    /**
     * Hover over element
     */
    protected void hoverOverElement(By locator) {
        WebElement element = waitForVisibility(locator, 10);
        actions.moveToElement(element).perform();
    }

    /**
     * Hover and click
     */
    protected void hoverAndClick(By hoverLocator, By clickLocator) {
        WebElement hoverElement = waitForVisibility(hoverLocator, 10);
        actions.moveToElement(hoverElement).perform();
        clickElement(clickLocator);
    }

    // ==================== SCROLL ACTIONS ====================

    /**
     * Scroll to element
     */
    protected void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Scroll to bottom of page
     */
    protected void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Scroll by amount
     */
    protected void scrollByAmount(int x, int y) {
        js.executeScript("window.scrollBy(" + x + "," + y + ");");
    }

    // ==================== ALERT ACTIONS ====================

    /**
     * Accept alert
     */
    protected void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    /**
     * Dismiss alert
     */
    protected void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    /**
     * Get alert text
     */
    protected String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }

    /**
     * Type in alert prompt
     */
    protected void typeInAlert(String text) {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }




    protected WebElement waitForModal(By locator) {
        return waitForVisibility(locator, 10);
    }

    /**
     * Close modal
     */
    protected void closeModal(By closeButtonLocator) {
        clickElement(closeButtonLocator);
    }




    protected void uploadFile(By locator, String filePath) {
        WebElement uploadElement = driver.findElement(locator);
        uploadElement.sendKeys(filePath);
    }




    protected void pressKeys(Keys... keys) {
        actions.sendKeys(keys).perform();
    }


    protected void pressKeyOnElement(By locator, Keys key) {
        WebElement element = driver.findElement(locator);
        element.sendKeys(key);
    }

    // ==================== VERIFICATION METHODS ====================

    /**
     * Check if element is enabled
     */
    protected boolean isElementEnabled(By locator) {
        return driver.findElement(locator).isEnabled();
    }

    /**
     * Check if element is selected
     */
    protected boolean isElementSelected(By locator) {
        return driver.findElement(locator).isSelected();
    }

    /**
     * Get CSS value
     */
    protected String getCssValue(By locator, String property) {
        return driver.findElement(locator).getCssValue(property);
    }

    /**
     * Take a pause
     */
    protected void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}