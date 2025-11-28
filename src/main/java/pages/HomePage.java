package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class HomePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    // Locators for main navigation elements
    private By pageTitle = By.tagName("h1");
    private By navigationCards = By.cssSelector(".card");

    // Navigation link locators for different practice pages
    private By webFormLink = By.linkText("Web form");
    private By loginFormLink = By.linkText("Login form");
    private By slowCalculatorLink = By.linkText("Slow calculator");
    private By dragAndDropLink = By.linkText("Drag and drop");
    private By dropdownMenuLink = By.linkText("Dropdown menu");
    private By modalDialogLink = By.linkText("Modal dialog");
    private By infiniteScrollLink = By.linkText("Infinite scroll");
    private By loadingImagesLink = By.linkText("Loading images");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToHomePage() {
        driver.get(BASE_URL);
        waitForPageLoad();
    }

    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void navigateToWebForm() {
        clickLink(webFormLink);
    }


    public void navigateToLoginForm() {
        clickLink(loginFormLink);
    }


    public void navigateToSlowCalculator() {
        clickLink(slowCalculatorLink);
    }

    public void navigateToDragAndDrop() {
        clickLink(dragAndDropLink);
    }

    public void navigateToDropdownMenu() {
        clickLink(dropdownMenuLink);
    }

    public void navigateToModalDialog() {
        clickLink(modalDialogLink);
    }


    public void navigateToInfiniteScroll() {
        clickLink(infiniteScrollLink);
    }


    public void navigateToLoadingImages() {
        clickLink(loadingImagesLink);
    }


    protected void clickLink(By locator) {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(locator));
        link.click();
    }


    public boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getNavigationCardsCount() {
        return driver.findElements(navigationCards).size();
    }

    public boolean isHomePageLoaded() {
        return getCurrentUrl().equals(BASE_URL) && isElementDisplayed(pageTitle);
    }
}