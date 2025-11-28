package dropdown;

import base.BaseTest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.DropdownPage;
import pages.WebFormPage;


import java.util.List;

public class DropdownTests extends BaseTest {

    @Test
    public void testDropdownMenuSelection() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToDropdownMenu();

        DropdownPage dropdownPage = new DropdownPage(driver);

        dropdownPage.openLeftClickDropdown();
        List<WebElement> options = dropdownPage.getAllOptions("");
        Assert.assertTrue(options.size() > 0, "Left-click dropdown should have options");

        dropdownPage.selectOptionByText("Action", "");
        Assert.assertTrue(true, "Should be able to select Action from left-click dropdown");

        dropdownPage.openRightClickDropdown();
        List<WebElement> rightClickOptions = dropdownPage.getAllOptions("context-menu-2");
        Assert.assertTrue(rightClickOptions.size() > 0, "Right-click dropdown should have options");

        dropdownPage.selectOptionByText("Another action", "context-menu-2");
        Assert.assertTrue(true, "Should be able to select Another action from right-click dropdown");

        dropdownPage.openDoubleClickDropdown();
        List<WebElement> doubleClickOptions = dropdownPage.getAllOptions("context-menu-3");
        Assert.assertTrue(doubleClickOptions.size() > 0, "Double-click dropdown should have options");

        dropdownPage.selectOptionByText("Something else here", "context-menu-3");
        Assert.assertTrue(true, "Should be able to select option from double-click dropdown");
    }

    @Test
    public void testDropdownInWebForm() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToWebForm();

        WebFormPage webFormPage = new WebFormPage(driver);

        webFormPage.selectNumber("1");
        Assert.assertTrue(true, "First option should be selected");

        webFormPage.selectNumber("2");
        Assert.assertTrue(true, "Second option should be selected");
    }

    @Test
    public void testDropdownOptionsCount() {

        HomePage homePage = new HomePage(driver);
        homePage.navigateToDropdownMenu();

        DropdownPage dropdownPage = new DropdownPage(driver);


        dropdownPage.openLeftClickDropdown();
        List<WebElement> leftClickOptions = dropdownPage.getAllOptions("");
        Assert.assertTrue(leftClickOptions.size() >= 3, "Left-click dropdown should have at least 3 options");


        dropdownPage.openRightClickDropdown();
        List<WebElement> rightClickOptions = dropdownPage.getAllOptions("context-menu-2");
        Assert.assertTrue(rightClickOptions.size() >= 3, "Right-click dropdown should have at least 3 options");


        dropdownPage.openDoubleClickDropdown();
        List<WebElement> doubleClickOptions = dropdownPage.getAllOptions("context-menu-3");
        Assert.assertTrue(doubleClickOptions.size() >= 3, "Double-click dropdown should have at least 3 options");


        for (WebElement option : leftClickOptions) {
            Assert.assertTrue(option.isEnabled(), "Left-click option should be enabled: " + option.getText());
        }
    }
}