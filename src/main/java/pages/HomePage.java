package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class HomePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    private By pageTitle = By.tagName("h1");
    private By navigationCards = By.cssSelector(".card");
    
    
    private By webFormLink = By.linkText("Web form");
    private By loginFormLink = By.linkText("Login form");
    private By slowCalculatorLink = By.linkText("Slow calculator");
    private By dragAndDropLink = By.linkText("Drag and drop");
    private By dropdownMenuLink = By.linkText("Dropdown menu");
    private By modalDialogLink = By.linkText("Modal dialog");
    private By infiniteScrollLink = By.linkText("Infinite scroll");
    private By loadingImagesLink = By.linkText("Loading images");
    private By dialogBoxesLink = By.linkText("Dialog boxes");
    private By mouseOverLink = By.linkText("Mouse over");
    private By longPageLink = By.linkText("Long page");
    private By downloadFilesLink = By.linkText("Download files");
    private By slowLoginLink = By.linkText("Slow login");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void scrollToElement(WebElement element) {
        System.out.println("Executing scroll to element...");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Scroll completed.");
    }

    private void clickElementSafely(WebElement element) {
        scrollToElement(element);
        try {
            element.click();
        } catch (Exception e) {
            System.out.println("Normal click failed, using JavaScript click...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
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

    public void navigateToMouseOver() {
        clickLink(mouseOverLink);
    }

    public void navigateToLongPage() {
        clickLink(longPageLink);
    }

    public void navigateToDownloadFiles() {
        clickLink(downloadFilesLink);
    }

    public void navigateToSlowLogin() {
        clickLink(slowLoginLink);
    }

    public void navigateToDialogBoxes() {
        clickLink(dialogBoxesLink);
    }

    protected void clickLink(By locator) {
        System.out.println("Waiting for link to be clickable...");
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(locator));
        
        System.out.println("Scrolling to link on homepage...");
        scrollToElement(link);
        
        System.out.println("Attempting to click link...");
        clickElementSafely(link);
        
        System.out.println("Link clicked, waiting for page to load...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}