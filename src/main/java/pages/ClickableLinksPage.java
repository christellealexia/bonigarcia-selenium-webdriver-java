package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Set;


public class ClickableLinksPage extends BasePage {

    // Locators
    private By webFormLink = By.linkText("Web form");
    private By practicePageHeader = By.tagName("h1");
    private By homeLink = By.linkText("Home");
    private By allLinks = By.tagName("a");
    private By imageLink = By.cssSelector("img[src*='hands']");

    public ClickableLinksPage(WebDriver driver) {
        super(driver);
    }


    public void navigateToPage() {
        navigateToHomePage();
        clickElement(webFormLink);
    }


    public void clickLinkByText(String linkText) {
        clickElement(By.linkText(linkText));
    }


    public void clickLinkByPartialText(String partialLinkText) {
        clickElement(By.partialLinkText(partialLinkText));
    }


    public void clickHomeLink() {
        clickElement(homeLink);
    }


    public int getLinkCount() {
        return driver.findElements(allLinks).size();
    }

    public String clickLinkAndSwitchWindow(By locator) {
        String parentWindow = driver.getWindowHandle();
        clickElement(locator);

        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                return window;
            }
        }
        return parentWindow;
    }

    public void closeAndSwitchToParent(String parentWindow) {
        driver.close();
        driver.switchTo().window(parentWindow);
    }


    public boolean isLinkDisplayed(String linkText) {
        return isElementDisplayed(By.linkText(linkText));
    }

    public String getLinkHref(String linkText) {
        return getAttribute(By.linkText(linkText), "href");
    }


    public void clickImageLink() {
        clickElement(imageLink);
    }


    public String getPageHeader() {
        return getText(practicePageHeader);
    }
}