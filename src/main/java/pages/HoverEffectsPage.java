package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HoverEffectsPage extends BasePage {

    // Locators for mouse over elements
    private By mouseOverLink = By.linkText("Mouse over");
    private By imageContainer = By.cssSelector(".container");
    private By hoverableImage1 = By.cssSelector("img[src*='compass']");
    private By hoverableImage2 = By.cssSelector("img[src*='award']");
    private By hoverableImage3 = By.cssSelector("img[src*='landscape']");
    private By captionOverlay = By.className("caption");
    private By allImages = By.tagName("img");

    // Dropdown hover (from dropdown page)
    private By dropdownMenuLink = By.linkText("Dropdown menu");
    private By dropdownToggle = By.id("my-dropdown");
    private By dropdownMenu = By.className("dropdown-menu");

    public HoverEffectsPage(WebDriver driver) {
        super(driver);
    }


    public void navigateToMouseOverPage() {
        navigateToHomePage();
        clickElement(mouseOverLink);
    }


    public void navigateToDropdownPage() {
        navigateToHomePage();
        clickElement(dropdownMenuLink);
    }


    public void hoverOverFirstImage() {
        hoverOverElement(hoverableImage1);
    }


    public void hoverOverSecondImage() {
        hoverOverElement(hoverableImage2);
    }


    public void hoverOverThirdImage() {
        hoverOverElement(hoverableImage3);
    }


    public void hoverOverImageByIndex(int index) {
        By imageLocator = By.cssSelector("img:nth-of-type(" + index + ")");
        hoverOverElement(imageLocator);
    }


    public String getCaptionText() {
        return getText(captionOverlay);
    }


    public boolean isCaptionDisplayed() {
        return isElementDisplayed(captionOverlay);
    }


    public String getImageOpacity(By imageLocator) {
        hoverOverElement(imageLocator);
        return getCssValue(imageLocator, "opacity");
    }


    public String getCaptionBackgroundColor() {
        return getCssValue(captionOverlay, "background-color");
    }


    public void hoverOverDropdownToggle() {
        hoverOverElement(dropdownToggle);
    }

    public boolean isDropdownMenuVisible() {
        return isElementDisplayed(dropdownMenu);
    }


    public boolean verifyHoverEffect(By locator, String cssProperty, String expectedValue) {
        hoverOverElement(locator);
        String actualValue = getCssValue(locator, cssProperty);
        return actualValue.contains(expectedValue);
    }


    public int getHoverableImagesCount() {
        return driver.findElements(allImages).size();
    }


    public void hoverAndClickElement(By hoverLocator, By clickLocator) {
        hoverAndClick(hoverLocator, clickLocator);
    }


    public void moveMouseAway() {
        hoverOverElement(imageContainer);
    }
}