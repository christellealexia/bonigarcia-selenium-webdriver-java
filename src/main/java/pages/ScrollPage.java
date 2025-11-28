package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class ScrollPage {
    private WebDriver driver;
    private JavascriptExecutor js;

    public ScrollPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    public Long getScrollPosition() {
        return (Long) js.executeScript("return window.pageYOffset;");
    }

    public Long getPageHeight() {
        return (Long) js.executeScript(
            "return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);");
    }

    public void scrollToBottom() {
        Long pageHeight = getPageHeight();
        js.executeScript("window.scrollTo(0, " + pageHeight + ");");
    }

    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0);");
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        scrollToElement(element);
    }

    public void smoothScrollToBottom() {
        Long pageHeight = getPageHeight();
        js.executeScript("window.scrollTo({top: " + pageHeight + ", behavior: 'smooth'});");
    }

    public Boolean isElementInViewport(WebElement element) {
        return (Boolean) js.executeScript(
            "var rect = arguments[0].getBoundingClientRect();" +
            "var windowHeight = window.innerHeight || document.documentElement.clientHeight;" +
            "return (rect.top < windowHeight && rect.bottom > 0);",
            element);
    }

    
    public Boolean isElementInViewport(By locator) {
        WebElement element = driver.findElement(locator);
        return isElementInViewport(element);
    }
}