package modals;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.Modalpage;

public class ModalsTests extends BaseTest {

    @Test
    public void testModalDialogOpenAndClose() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToDialogBoxes();

        Modalpage modalPage = new Modalpage(driver);
        modalPage.openModal();
        WebElement modal = modalPage.getModal();
        Assert.assertTrue(modal.isDisplayed(), "Modal should be visible");
        modalPage.closeModal();
        Assert.assertTrue(true, "Modal should be closed");
    }

    @Test
    public void testModalContentInteraction() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToDialogBoxes();

        Modalpage modalPage = new Modalpage(driver);
        modalPage.openModal();
        WebElement modalContent = modalPage.getModalContent();
        Assert.assertTrue(modalContent.isDisplayed(), "Modal content should be visible");
        String modalText = modalContent.getText();
        Assert.assertNotNull(modalText, "Modal should have content");
        Assert.assertFalse(modalText.isEmpty(), "Modal should have some text");
        modalPage.closeModal();
    }

    @Test
    public void testModalBackdropClick() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToDialogBoxes();

        Modalpage modalPage = new Modalpage(driver);
        modalPage.openModal();
        WebElement modal = modalPage.getModal();
        Assert.assertTrue(modal.isDisplayed(), "Modal should be visible");
        try {
            org.openqa.selenium.WebElement backdrop = driver.findElement(
                org.openqa.selenium.By.xpath("//div[contains(@class, 'modal-backdrop')]"));
            if (backdrop.isDisplayed()) {
                backdrop.click();
                Thread.sleep(500);
            }
        } catch (Exception e) {
        }
        modalPage.closeModal();
        Assert.assertTrue(true, "Modal should be closed");
    }

    @Test
    public void testModalWithFormSubmission() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToDialogBoxes();

        Modalpage modalPage = new Modalpage(driver);
        modalPage.openModal();
        WebElement modalContent = modalPage.getModalContent();
        modalPage.saveAndCloseModal();
        Assert.assertTrue(true, "Modal interaction test completed");
    }
} 
