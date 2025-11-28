package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class FormsPage extends BasePage {

    // Locators for Web Form
    private By webFormLink = By.linkText("Web form");
    private By textInput = By.id("my-text-id");
    private By passwordInput = By.name("my-password");
    private By textArea = By.name("my-textarea");
    private By disabledInput = By.name("my-disabled");
    private By readonlyInput = By.name("my-readonly");
    private By datePicker = By.name("my-date");
    private By dropdown = By.name("my-select");
    private By fileInput = By.name("my-file");
    private By checkbox1 = By.id("my-check-1");
    private By checkbox2 = By.id("my-check-2");
    private By radio1 = By.id("my-radio-1");
    private By radio2 = By.id("my-radio-2");
    private By colorPicker = By.name("my-colors");
    private By rangeSlider = By.name("my-range");
    private By submitButton = By.cssSelector("button[type='submit']");
    private By successMessage = By.id("success");

    // Login Form Locators
    private By loginFormLink = By.linkText("Login form");
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By successLoginMessage = By.id("success");
    private By invalidLoginMessage = By.id("invalid");

    public FormsPage(WebDriver driver) {
        super(driver);
    }


    public void navigateToWebFormPage() {
        navigateToHomePage();
        clickElement(webFormLink);
    }


    public void navigateToLoginFormPage() {
        navigateToHomePage();
        clickElement(loginFormLink);
    }


    public void fillTextInput(String text) {
        typeText(textInput, text);
    }


    public void fillPasswordInput(String password) {
        typeText(passwordInput, password);
    }


    public void fillTextArea(String text) {
        typeText(textArea, text);
    }


    public void selectDate(String date) {
        typeText(datePicker, date);
    }


    public void selectDropdownOption(String option) {
        selectDropdownByText(dropdown, option);
    }


    public String getSelectedDropdownOption() {
        return getSelectedDropdownText(dropdown);
    }


    public void checkCheckbox1() {
        if (!isElementSelected(checkbox1)) {
            clickElement(checkbox1);
        }
    }


    public void checkCheckbox2() {
        if (!isElementSelected(checkbox2)) {
            clickElement(checkbox2);
        }
    }


    public void selectRadio1() {
        clickElement(radio1);
    }


    public void selectRadio2() {
        clickElement(radio2);
    }

    public void uploadFile(String filePath) {
        uploadFile(fileInput, filePath);
    }


    public void setColorPicker(String colorHex) {
        typeText(colorPicker, colorHex);
    }


    public void setRangeSlider(String value) {
        js.executeScript("arguments[0].value = arguments[1];",
                driver.findElement(rangeSlider), value);
    }


    public boolean isDisabledInputDisabled() {
        return !isElementEnabled(disabledInput);
    }


    public boolean isReadonlyInputReadonly() {
        return getAttribute(readonlyInput, "readonly") != null;
    }

    public void submitForm() {
        clickElement(submitButton);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }

    public void fillAndSubmitCompleteForm(String text, String password,
                                          String textAreaContent, String date) {
        fillTextInput(text);
        fillPasswordInput(password);
        fillTextArea(textAreaContent);
        selectDate(date);
        selectDropdownOption("One");
        checkCheckbox1();
        selectRadio1();
        submitForm();
    }

    public void login(String username, String password) {
        typeText(usernameField, username);
        typeText(passwordField, password);
        clickElement(loginButton);
    }


    public String getSuccessLoginMessage() {
        return getText(successLoginMessage);
    }


    public String getInvalidLoginMessage() {
        return getText(invalidLoginMessage);
    }


    public boolean isLoginSuccessful() {
        return isElementDisplayed(successLoginMessage);
    }


    public boolean isLoginFailed() {
        return isElementDisplayed(invalidLoginMessage);
    }
}
