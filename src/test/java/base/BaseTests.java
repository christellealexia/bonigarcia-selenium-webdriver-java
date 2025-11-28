package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BaseTests {
    private WebDriver driver; //create a driver object

    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

    };

    public static void main(String args[]){
        BaseTests test = new BaseTests();
        test.setUp();

    }


}

