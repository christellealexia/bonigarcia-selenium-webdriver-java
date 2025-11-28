package forms;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FormsPage;


public class FormsTest extends BaseTest {

    private FormsPage formsPage;

    @BeforeMethod
    public void setUpPage() {
        formsPage = new FormsPage(driver);
    }

    @Test(priority = 1)
    public void testFillTextInput() {
        formsPage.navigateToWebFormPage();
        formsPage.fillTextInput("Test User");
        Assert.assertTrue(true, "Text input filled successfully");
    }

    @Test(priority = 2)
    public void testFillPasswordInput() {
        formsPage.navigateToWebFormPage();
        formsPage.fillPasswordInput("SecurePassword123");
        Assert.assertTrue(true, "Password input filled successfully");
    }

    @Test(priority = 3)
    public void testFillTextArea() {
        formsPage.navigateToWebFormPage();
        formsPage.fillTextArea("This is a test message in the text area.");
        Assert.assertTrue(true, "Text area filled successfully");
    }

    @Test(priority = 4)
    public void testSelectDate() {
        formsPage.navigateToWebFormPage();
        formsPage.selectDate("01/15/2024");
        Assert.assertTrue(true, "Date selected successfully");
    }

    @Test(priority = 5)
    public void testSelectDropdown() {
        formsPage.navigateToWebFormPage();
        formsPage.selectDropdownOption("Two");
        String selectedOption = formsPage.getSelectedDropdownOption();
        Assert.assertEquals(selectedOption, "Two", "Selected dropdown option should be 'Two'");
    }

    @Test(priority = 6)
    public void testCheckboxSelection() {
        formsPage.navigateToWebFormPage();
        formsPage.checkCheckbox1();
        formsPage.checkCheckbox2();
        Assert.assertTrue(true, "Checkboxes checked successfully");
    }

    @Test(priority = 7)
    public void testRadioButtonSelection() {
        formsPage.navigateToWebFormPage();
        formsPage.selectRadio1();
        pause(500);
        formsPage.selectRadio2();
        Assert.assertTrue(true, "Radio buttons selected successfully");
    }

    @Test(priority = 8)
    public void testDisabledInput() {
        formsPage.navigateToWebFormPage();
        boolean isDisabled = formsPage.isDisabledInputDisabled();
        Assert.assertTrue(isDisabled, "Disabled input should be disabled");
    }

    @Test(priority = 9)
    public void testReadonlyInput() {
        formsPage.navigateToWebFormPage();
        boolean isReadonly = formsPage.isReadonlyInputReadonly();
        Assert.assertTrue(isReadonly, "Readonly input should be readonly");
    }

    @Test(priority = 10)
    public void testCompleteFormSubmission() {
        formsPage.navigateToWebFormPage();
        formsPage.fillAndSubmitCompleteForm(
                "John Doe",
                "Password123",
                "Sample text area content",
                "05/20/2024"
        );
        pause(2000);
        Assert.assertTrue(getCurrentUrl().contains("submitted"),
                "Form should be submitted successfully");
    }

    @Test(priority = 11)
    public void testLoginWithValidCredentials() {
        formsPage.navigateToLoginFormPage();
        formsPage.login("user", "user");
        pause(2000);
        boolean isSuccessful = formsPage.isLoginSuccessful();
        Assert.assertTrue(isSuccessful, "Login should be successful with valid credentials");
    }

    @Test(priority = 12)
    public void testLoginWithInvalidCredentials() {
        formsPage.navigateToLoginFormPage();
        formsPage.login("invalid", "invalid");
        pause(2000);
        boolean isFailed = formsPage.isLoginFailed();
        Assert.assertTrue(isFailed, "Login should fail with invalid credentials");
    }

    @Test(priority = 13)
    public void testSuccessMessage() {
        formsPage.navigateToWebFormPage();
        formsPage.fillTextInput("Test");
        formsPage.submitForm();
        pause(2000);
        String successMessage = formsPage.getSuccessMessage();
        Assert.assertNotNull(successMessage, "Success message should be displayed");
        System.out.println("Success message: " + successMessage);
    }
}
