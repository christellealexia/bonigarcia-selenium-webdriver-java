package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FileUploadPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By fileInput = By.xpath("//input[@type='file']");

    private By downloadLink = By.xpath("//a[contains(@href, '.txt') or contains(@href, '.pdf') or contains(@href, '.zip')]");

    public FileUploadPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void uploadFile(String filePath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
        driver.findElement(fileInput).sendKeys(filePath);
    }

    public void clickDownloadLink() {
        wait.until(ExpectedConditions.elementToBeClickable(downloadLink));
        driver.findElement(downloadLink).click();
    }

    public WebElement getFileInput() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
    }
}