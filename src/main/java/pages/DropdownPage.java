package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DropdownPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By leftClickDropdown = By.id("my-dropdown-1");
    private By rightClickDropdown = By.id("my-dropdown-2");
    private By doubleClickDropdown = By.id("my-dropdown-3");

    public DropdownPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public void openLeftClickDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(leftClickDropdown));
        driver.findElement(leftClickDropdown).click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    org.openqa.selenium.By.xpath("//button[@id='my-dropdown-1']/following-sibling::ul[@class='dropdown-menu']")));
        } catch (Exception e) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    org.openqa.selenium.By.xpath("//ul[@class='dropdown-menu' and contains(@class, 'show')]")
            ));
        }
    }

    public void openRightClickDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(rightClickDropdown));
        actions.contextClick(driver.findElement(rightClickDropdown)).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                org.openqa.selenium.By.id("context-menu-2")));
    }

    public void openDoubleClickDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(doubleClickDropdown));
        actions.doubleClick(driver.findElement(doubleClickDropdown)).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                org.openqa.selenium.By.id("context-menu-3")));
    }

    public void selectOptionByText(String optionText, String dropdownMenuId) {
        String menuXpath;
        if (dropdownMenuId == null || dropdownMenuId.isEmpty()) {
            try {
                menuXpath = "//button[@id='my-dropdown-1']/following-sibling::ul[@class='dropdown-menu']//a[text()='" + optionText + "']";
                wait.until(ExpectedConditions.elementToBeClickable(
                        org.openqa.selenium.By.xpath(menuXpath)));
            } catch (Exception e) {
                menuXpath = "//ul[@class='dropdown-menu']//a[text()='" + optionText + "']";
            }
        } else {
            menuXpath = "//ul[@id='" + dropdownMenuId + "']//a[text()='" + optionText + "']";
        }

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                org.openqa.selenium.By.xpath(menuXpath)));

        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", option);

        try {
            option.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", option);
        }
    }

    public List<WebElement> getAllOptions(String dropdownMenuId) {
        String menuXpath;
        if (dropdownMenuId == null || dropdownMenuId.isEmpty()) {
            try {
                menuXpath = "//button[@id='my-dropdown-1']/following-sibling::ul[@class='dropdown-menu']//a[@class='dropdown-item']";
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        org.openqa.selenium.By.xpath(menuXpath)));
            } catch (Exception e) {
                menuXpath = "//ul[@class='dropdown-menu']//a[@class='dropdown-item']";
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        org.openqa.selenium.By.xpath(menuXpath)));
            }
        } else {
            menuXpath = "//ul[@id='" + dropdownMenuId + "']//a[@class='dropdown-item']";
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    org.openqa.selenium.By.xpath(menuXpath)));
        }
        return driver.findElements(org.openqa.selenium.By.xpath(menuXpath));
    }

    public void selectOptionByIndex(int index, String dropdownMenuId) {
        List<WebElement> options = getAllOptions(dropdownMenuId);
        if (index >= 0 && index < options.size()) {
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(options.get(index)));
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", option);
            try {
                option.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", option);
            }
        }
    }
}