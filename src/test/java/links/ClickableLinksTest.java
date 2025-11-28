package links;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ClickableLinksPage;


public class ClickableLinksTest extends BaseTest {

    private ClickableLinksPage clickableLinksPage;

    @BeforeMethod
    public void setUpPage() {
        clickableLinksPage = new ClickableLinksPage(driver);
    }

    @Test(priority = 1)
    public void testClickLinkByText() {
        clickableLinksPage.navigateToPage();
        Assert.assertTrue(clickableLinksPage.getPageHeader().contains("Web form"));
    }

    @Test(priority = 2)
    public void testNavigateToHomeAndBack() {
        clickableLinksPage.navigateToPage();
        clickableLinksPage.clickHomeLink();
        pause(1000);
        Assert.assertTrue(getCurrentUrl().contains("selenium-webdriver-java"));
    }

    @Test(priority = 3)
    public void testGetLinkCount() {
        clickableLinksPage.navigateToPage();
        int linkCount = clickableLinksPage.getLinkCount();
        Assert.assertTrue(linkCount > 0, "Page should have at least one link");
        System.out.println("Total links on page: " + linkCount);
    }

    @Test(priority = 4)
    public void testLinkIsDisplayed() {
        clickableLinksPage.navigateToPage();
        boolean isDisplayed = clickableLinksPage.isLinkDisplayed("Home");
        Assert.assertTrue(isDisplayed, "Home link should be displayed");
    }

    @Test(priority = 5)
    public void testGetLinkHref() {
        clickableLinksPage.navigateToPage();
        String href = clickableLinksPage.getLinkHref("Home");
        Assert.assertNotNull(href, "Link href should not be null");
        Assert.assertTrue(href.contains("selenium-webdriver-java"),
                "Href should contain base URL");
    }

    @Test(priority = 6)
    public void testClickMultipleLinks() {
        navigateToBaseUrl();
        clickableLinksPage.clickLinkByText("Web form");
        pause(1000);
        Assert.assertTrue(getCurrentUrl().contains("web-form.html"));

        clickableLinksPage.clickHomeLink();
        pause(1000);
        Assert.assertTrue(getCurrentUrl().contains("index.html") ||
                getCurrentUrl().endsWith("selenium-webdriver-java/"));
    }
}
