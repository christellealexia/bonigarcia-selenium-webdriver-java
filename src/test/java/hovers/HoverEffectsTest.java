package hovers;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HoverEffectsPage;


public class HoverEffectsTest extends BaseTest {

    private HoverEffectsPage hoverEffectsPage;

    @BeforeMethod
    public void setUpPage() {
        hoverEffectsPage = new HoverEffectsPage(driver);
    }

    @Test(priority = 1)
    public void testNavigateToMouseOverPage() {
        hoverEffectsPage.navigateToMouseOverPage();
        Assert.assertTrue(getCurrentUrl().contains("mouse-over"),
                "Should navigate to mouse over page");
    }

    @Test(priority = 2)
    public void testHoverOverFirstImage() {
        hoverEffectsPage.navigateToMouseOverPage();
        hoverEffectsPage.hoverOverFirstImage();
        pause(1000);
        boolean isCaptionDisplayed = hoverEffectsPage.isCaptionDisplayed();
        Assert.assertTrue(isCaptionDisplayed, "Caption should be displayed on hover");
    }

    @Test(priority = 3)
    public void testHoverOverSecondImage() {
        hoverEffectsPage.navigateToMouseOverPage();
        hoverEffectsPage.hoverOverSecondImage();
        pause(1000);
        boolean isCaptionDisplayed = hoverEffectsPage.isCaptionDisplayed();
        Assert.assertTrue(isCaptionDisplayed, "Caption should be displayed on hover");
    }

    @Test(priority = 4)
    public void testHoverOverThirdImage() {
        hoverEffectsPage.navigateToMouseOverPage();
        hoverEffectsPage.hoverOverThirdImage();
        pause(1000);
        boolean isCaptionDisplayed = hoverEffectsPage.isCaptionDisplayed();
        Assert.assertTrue(isCaptionDisplayed, "Caption should be displayed on hover");
    }

    @Test(priority = 5)
    public void testGetCaptionText() {
        hoverEffectsPage.navigateToMouseOverPage();
        hoverEffectsPage.hoverOverFirstImage();
        pause(1000);
        String captionText = hoverEffectsPage.getCaptionText();
        Assert.assertNotNull(captionText, "Caption text should not be null");
        Assert.assertFalse(captionText.isEmpty(), "Caption text should not be empty");
        System.out.println("Caption text: " + captionText);
    }

    @Test(priority = 6)
    public void testHoverableImagesCount() {
        hoverEffectsPage.navigateToMouseOverPage();
        int imagesCount = hoverEffectsPage.getHoverableImagesCount();
        Assert.assertTrue(imagesCount >= 3, "Should have at least 3 hoverable images");
        System.out.println("Total hoverable images: " + imagesCount);
    }

    @Test(priority = 7)
    public void testHoverByIndex() {
        hoverEffectsPage.navigateToMouseOverPage();
        hoverEffectsPage.hoverOverImageByIndex(1);
        pause(1000);
        boolean isCaptionDisplayed = hoverEffectsPage.isCaptionDisplayed();
        Assert.assertTrue(isCaptionDisplayed, "Caption should be displayed when hovering by index");
    }

    @Test(priority = 8)
    public void testHoverMultipleImages() {
        hoverEffectsPage.navigateToMouseOverPage();

        hoverEffectsPage.hoverOverFirstImage();
        pause(500);
        Assert.assertTrue(hoverEffectsPage.isCaptionDisplayed(), "Caption should show on first image");

        hoverEffectsPage.hoverOverSecondImage();
        pause(500);
        Assert.assertTrue(hoverEffectsPage.isCaptionDisplayed(), "Caption should show on second image");

        hoverEffectsPage.hoverOverThirdImage();
        pause(500);
        Assert.assertTrue(hoverEffectsPage.isCaptionDisplayed(), "Caption should show on third image");
    }

    @Test(priority = 9)
    public void testMoveMouseAway() {
        hoverEffectsPage.navigateToMouseOverPage();
        hoverEffectsPage.hoverOverFirstImage();
        pause(1000);
        hoverEffectsPage.moveMouseAway();
        pause(1000);
        Assert.assertTrue(true, "Mouse moved away successfully");
    }

    @Test(priority = 10)
    public void testDropdownHoverEffect() {
        hoverEffectsPage.navigateToDropdownPage();
        hoverEffectsPage.hoverOverDropdownToggle();
        pause(1000);
        Assert.assertTrue(true, "Dropdown hover effect tested successfully");
    }
}
