package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DropdownPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    // this page has three bootstrap dropdowns with different click types
    // first one is left-click, second is right-click, third is double-click
    @FindBy(id = "my-dropdown-1")
    private WebElement leftClickDropdown;

    @FindBy(id = "my-dropdown-2")
    private WebElement rightClickDropdown;

    @FindBy(id = "my-dropdown-3")
    private WebElement doubleClickDropdown;

    public DropdownPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // open the left-click dropdown (first one)
    public void openLeftClickDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(leftClickDropdown));
        leftClickDropdown.click();
        // wait for the menu to show up, try different selectors
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    org.openqa.selenium.By.xpath("//button[@id='my-dropdown-1']/following-sibling::ul[@class='dropdown-menu']")));
        } catch (Exception e) {
            // try alternative selector
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    org.openqa.selenium.By.xpath("//ul[@class='dropdown-menu' and contains(@class, 'show')]")));
        }
    }

    // open the right-click dropdown (second one) using context menu
    public void openRightClickDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(rightClickDropdown));
        // right click to open the context menu
        actions.contextClick(rightClickDropdown).perform();
        // wait for the context menu to show
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                org.openqa.selenium.By.id("context-menu-2")));
    }

    // open the double-click dropdown (third one)
    public void openDoubleClickDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(doubleClickDropdown));
        // double click to open the menu
        actions.doubleClick(doubleClickDropdown).perform();
        // wait for the menu to show
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                org.openqa.selenium.By.id("context-menu-3")));
    }

    // click on an option in a specific dropdown menu by its text
    // dropdownMenuId can be empty for first dropdown, "context-menu-2" for right-click, "context-menu-3" for double-click
    public void selectOptionByText(String optionText, String dropdownMenuId) {
        String menuXpath;
        if (dropdownMenuId == null || dropdownMenuId.isEmpty()) {
            // first dropdown menu, try different selectors
            try {
                menuXpath = "//button[@id='my-dropdown-1']/following-sibling::ul[@class='dropdown-menu']//a[text()='" + optionText + "']";
                wait.until(ExpectedConditions.elementToBeClickable(
                        org.openqa.selenium.By.xpath(menuXpath)));
            } catch (Exception e) {
                menuXpath = "//ul[@class='dropdown-menu']//a[text()='" + optionText + "']";
            }
        } else {
            // context menu for right-click or double-click
            menuXpath = "//ul[@id='" + dropdownMenuId + "']//a[text()='" + optionText + "']";
        }

        // wait for the option to be visible and clickable
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                org.openqa.selenium.By.xpath(menuXpath)));

        // scroll into view first
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", option);

        // try regular click first, fallback to javascript
        try {
            option.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", option);
        }
    }

    // get all options from a specific dropdown menu
    public List<WebElement> getAllOptions(String dropdownMenuId) {
        String menuXpath;
        if (dropdownMenuId == null || dropdownMenuId.isEmpty()) {
            // try different selectors for the first dropdown
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

    // select option by index from a specific dropdown
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