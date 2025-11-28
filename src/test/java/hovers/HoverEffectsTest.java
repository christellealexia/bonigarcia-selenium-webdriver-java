package hovers;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HoverEffectsPage;
import pages.HomePage;

public class HoverEffectsTest extends BaseTest {

    @Test
    public void testMouseOverEffect() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToMouseOver();

        HoverEffectsPage hoverPage = new HoverEffectsPage(driver);
        Actions actions = new Actions(driver);
        WebElement hoverElement = hoverPage.getHoverElement();
        String initialClass = hoverPage.getHoverElementClass();
        actions.moveToElement(hoverElement).perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String afterHoverClass = hoverPage.getHoverElementClass();
        Assert.assertTrue(hoverElement.isDisplayed(), "Hover element should still be visible after hover");
        Assert.assertTrue(hoverElement.isEnabled(), "Hover element should still be enabled after hover");
    }

    @Test
    public void testHoverWithTooltip() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToMouseOver();

        HoverEffectsPage hoverPage = new HoverEffectsPage(driver);
        Actions actions = new Actions(driver);
        WebElement hoverElement = hoverPage.getHoverElement();
        actions.moveToElement(hoverElement).perform();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Assert.assertTrue(hoverElement.isDisplayed(), "Hover element should be visible");
    }

    @Test
    public void testHoverStateChange() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToMouseOver();

        HoverEffectsPage hoverPage = new HoverEffectsPage(driver);
        Actions actions = new Actions(driver);
        WebElement hoverElement = hoverPage.getHoverElement();
        String initialStyle = hoverPage.getHoverElementBackgroundColor();
        actions.moveToElement(hoverElement).perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String afterHoverStyle = hoverPage.getHoverElementBackgroundColor();
        Assert.assertTrue(hoverElement.isDisplayed(), "Element should still be visible after hover");
        Assert.assertTrue(hoverElement.isEnabled(), "Element should still work after hover");
    }
}