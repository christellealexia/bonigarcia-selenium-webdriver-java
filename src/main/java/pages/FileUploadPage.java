package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.File;

/**
 * FileUploadPage - Handles file upload functionality
 */
public class FileUploadPage extends BasePage {

    // Locators
    private By webFormLink = By.linkText("Web form");
    private By fileInput = By.name("my-file");
    private By submitButton = By.cssSelector("button[type='submit']");
    private By uploadedFileName = By.id("my-file");

    public FileUploadPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to web form page for file upload
     */
    public void navigateToPage() {
        navigateToHomePage();
        clickElement(webFormLink);
    }

    /**
     * Upload file using absolute path
     */
    public void uploadFile(String absoluteFilePath) {
        uploadFile(fileInput, absoluteFilePath);
    }

    /**
     * Upload file from resources folder
     */
    public void uploadFileFromResources(String fileName) {
        String resourcePath = System.getProperty("user.dir") +
                File.separator + "src" +
                File.separator + "test" +
                File.separator + "resources" +
                File.separator + fileName;
        uploadFile(fileInput, resourcePath);
    }

    /**
     * Upload file and submit form
     */
    public void uploadFileAndSubmit(String filePath) {
        uploadFile(fileInput, filePath);
        clickElement(submitButton);
    }

    /**
     * Get uploaded file name from input
     */
    public String getUploadedFileName() {
        return getAttribute(fileInput, "value");
    }

    /**
     * Check if file input has file
     */
    public boolean hasFileUploaded() {
        String value = getAttribute(fileInput, "value");
        return value != null && !value.isEmpty();
    }

    /**
     * Clear file input (using JavaScript)
     */
    public void clearFileInput() {
        js.executeScript("arguments[0].value = '';", driver.findElement(fileInput));
    }

    /**
     * Upload multiple files (if supported)
     */
    public void uploadMultipleFiles(String... filePaths) {
        String allPaths = String.join("\n", filePaths);
        uploadFile(fileInput, allPaths);
    }

    /**
     * Verify file input accepts specific file types
     */
    public String getAcceptedFileTypes() {
        return getAttribute(fileInput, "accept");
    }

    /**
     * Check if file input is enabled
     */
    public boolean isFileInputEnabled() {
        return isElementEnabled(fileInput);
    }

    /**
     * Create a test file and upload it
     */
    public void createAndUploadTestFile(String fileName, String content) {
        try {
            String filePath = System.getProperty("user.dir") +
                    File.separator + "target" +
                    File.separator + fileName;

            File testFile = new File(filePath);
            java.io.FileWriter writer = new java.io.FileWriter(testFile);
            writer.write(content);
            writer.close();

            uploadFile(fileInput, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
