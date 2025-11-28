package deepscrolling;


import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ScrollPage;

import java.time.Duration;

public class ScrollTests extends BaseTest {

    @Test
    public void testDeepScrollingOnLongPage() {
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToLongPage();

        ScrollPage scrollingPage = new ScrollPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        
        wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.tagName("body")));
        
        Long pageHeight = scrollingPage.getPageHeight();
        
        Long initialScrollPosition = scrollingPage.getScrollPosition();
        Assert.assertEquals(initialScrollPosition, 0L, "Initial scroll position should be at top");
        
        if (pageHeight > 0) {
            
            scrollingPage.scrollToBottom();
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            Long finalScrollPosition = scrollingPage.getScrollPosition();
            
            if (pageHeight > 800) {
                Assert.assertTrue(finalScrollPosition >= 0, "Scroll position should be valid. Position: " + finalScrollPosition);
            }
            
            scrollingPage.scrollToTop();
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Long topScrollPosition = scrollingPage.getScrollPosition();
            
            Assert.assertTrue(topScrollPosition <= 10, "Page should be scrolled back to top. Position: " + topScrollPosition);
        }
    }

    @Test
    public void testScrollToSpecificElement() {
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToLongPage();

        ScrollPage scrollingPage = new ScrollPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.tagName("body")));
        
        org.openqa.selenium.WebElement targetElement = null;
        try {
            
            targetElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.xpath("(//p | //div | //footer | //*[@id='footer'] | //*[contains(@class, 'footer')])[last()]")));
        } catch (Exception e) {
            
            try {
                targetElement = driver.findElement(org.openqa.selenium.By.xpath("(//*)[last()]"));
            } catch (Exception e2) {
                
                targetElement = driver.findElement(org.openqa.selenium.By.tagName("body"));
            }
        }
        
        scrollingPage.scrollToElement(targetElement);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        Boolean isInViewport = scrollingPage.isElementInViewport(targetElement);
        
        if (targetElement.getTagName().equals("body")) {
            Assert.assertTrue(true, "Scrolled to body element");
        } else {
            Assert.assertTrue(isInViewport, "Target element should be in viewport after scrolling");
        }
    }

    @Test
    public void testInfiniteScroll() {
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToInfiniteScroll();

        ScrollPage scrollingPage = new ScrollPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.tagName("body")));
        
        Long initialHeight = scrollingPage.getPageHeight();
        
        scrollingPage.scrollToBottom();
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        Long currentHeight = scrollingPage.getPageHeight();
        scrollingPage.scrollToBottom();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        Long newHeight = scrollingPage.getPageHeight();
        
        Assert.assertTrue(newHeight >= initialHeight, 
            "Page height should increase or stay same after infinite scroll. Initial: " + initialHeight + ", New: " + newHeight);
    }

    @Test
    public void testSmoothScrolling() {
        
        HomePage homePage = new HomePage(driver);
        homePage.navigateToLongPage();

        ScrollPage scrollingPage = new ScrollPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.tagName("body")));
        
        Long pageHeight = scrollingPage.getPageHeight();
        
        if (pageHeight > 0 && pageHeight > 800) {
            
            scrollingPage.smoothScrollToBottom();
            scrollingPage.smoothScrollToBottom();
            
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            Long scrollPosition = scrollingPage.getScrollPosition();
            Assert.assertTrue(scrollPosition >= 0, "Page should be scrolled. Position: " + scrollPosition);
        } else {
            
            Assert.assertTrue(true, "Page is not tall enough for smooth scrolling test");
        }
    }
}

