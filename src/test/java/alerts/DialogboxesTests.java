package alerts;

import base.BaseTest;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DialogBoxesPage;
import pages.HomePage;

public class DialogboxesTests extends BaseTest {

    @Test
    public void testAlertDialogBox() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToDialogBoxes();

        DialogBoxesPage dialogBoxesPage = new DialogBoxesPage(driver);
        dialogBoxesPage.clickAlertButton();

        Alert alert = dialogBoxesPage.getAlert();
        Assert.assertNotNull(alert, "Alert should be present");
        Assert.assertEquals(alert.getText(), "Hello world!", "Alert text should match");
        alert.accept();
    }

    @Test
    public void testConfirmDialogBox() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToDialogBoxes();

        DialogBoxesPage dialogBoxesPage = new DialogBoxesPage(driver);
        dialogBoxesPage.clickConfirmButton();

        Alert confirmAlert = dialogBoxesPage.getAlert();
        Assert.assertNotNull(confirmAlert, "Confirm dialog should be present");
        confirmAlert.accept();
        Assert.assertTrue(dialogBoxesPage.getConfirmText().contains("true"), 
            "Confirm result should show accepted");
    }

    @Test
    public void testPromptDialogBox() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToDialogBoxes();

        DialogBoxesPage dialogBoxesPage = new DialogBoxesPage(driver);
        dialogBoxesPage.clickPromptButton();

        Alert promptAlert = dialogBoxesPage.getAlert();
        String inputText = "Selenium Test";
        promptAlert.sendKeys(inputText);
        promptAlert.accept();
        Assert.assertTrue(dialogBoxesPage.getPromptText().contains(inputText), 
            "Prompt result should contain the input text");
    }
}
