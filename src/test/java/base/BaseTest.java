package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;


/**
 * BaseTest - Setup and teardown for all test classes
 * All test classes should extend this class
 */
public class BaseTest {

    protected WebDriver driver;
    protected static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";


    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(BASE_URL);


//        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(20));


        // Maximize browser window
//        driver.manage().window().maximize();
//
//        // Delete all cookies
//        driver.manage().deleteAllCookies();
    }

    /**
     * Teardown method - runs after each test method
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    protected void navigateToBaseUrl() {
        driver.get(BASE_URL);
    }


    protected String getPageTitle() {
        return driver.getTitle();
    }


    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}