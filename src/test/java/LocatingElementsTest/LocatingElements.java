package LocatingElementsTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class LocatingElements {
    WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        driver= new ChromeDriver();
    }
    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void elementFinding(){
        driver.get("https://www.saucedemo.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
        assertTrue(element.isDisplayed());

    }
    @Test
    public void typesOfLocators() {

        driver.get("https://www.saucedemo.com/");
        WebElement el;
        el = driver.findElement(By.id("user-name"));
         driver.findElement(By.tagName("input"));
         driver.findElement(By.className("bot_column"));
         driver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        // #password
        driver.findElement(By.cssSelector("#password"));

    }
    @Test
    public void typesOfLocators2() {

        driver.get("https://ultimateqa.com/simple-html-elements-for-automation/");
        WebElement el = driver.findElement(By.linkText("Click me using this link text!"));
        el.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(By.className("entry-title")));
        assertTrue(element.isDisplayed());

    }

    @Rule
    public TestWatcher watcher = new TestWatcher() {
        @Override
        protected void failed (Throwable e, Description description) {
            System.out.println(description.getMethodName()+ ": Sikertelen");
        }
        @Override
        protected void succeeded(Description description) {
            System.out.println((description.getMethodName()+": Siker! "));
        }
    };
}
