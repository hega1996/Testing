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

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class TestingOrder {
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
    public void orderTest() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();

        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();

        driver.findElement(By.id("first-name")).sendKeys("first name");
        driver.findElement(By.id("last-name")).sendKeys("last name ");
        driver.findElement(By.id("postal-code")).sendKeys("zipcode");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();


        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#checkout_complete_container > img")));
        Thread.sleep(3000);
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
