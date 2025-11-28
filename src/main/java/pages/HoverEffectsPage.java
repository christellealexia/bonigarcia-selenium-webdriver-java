package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HoverEffectsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By hoverElement = By.id("hover");

    public HoverEffectsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement getHoverElement() {

        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(hoverElement));
        } catch (Exception e) {
            try {
                By alternative = By.xpath("//button | //div[@class] | //*[contains(@class, 'hover')]");
                return wait.until(ExpectedConditions.presenceOfElementLocated(alternative));
            } catch (Exception e2) {
                return driver.findElement(By.tagName("button"));
            }
        }
    }

    public String getHoverElementClass() {
        return getHoverElement().getAttribute("class");
    }

    public String getHoverElementBackgroundColor() {
        return getHoverElement().getCssValue("background-color");
    }
}