import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebdriverMethods {
    WebDriver driver;
    WebElement el;
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
    public void dropDownTest() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        driver.findElement(By.id("dropdown")).click();
        driver.findElement(By.cssSelector("#dropdown > option:nth-child(2)")).click();
        assertTrue(driver.findElement(By.cssSelector("#dropdown > option:nth-child(2)")).isSelected());
        Assert.assertFalse(driver.findElement(By.cssSelector("#dropdown > option:nth-child(3)")).isSelected());
    }
    @Test
    public void hoverTest() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        el = driver.findElement(By.cssSelector("#content > div > div:nth-child(3) > img"));
        Actions actions = new Actions(driver);
        actions.moveToElement(el).perform();
        el=driver.findElement(By.xpath("//*[contains(text(),'name: user1')]"));
        assertTrue("user1 should appear if we hover over the first picture",el.isDisplayed());
    }
    @Test
    public void rightClickTest() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/context_menu");
        el = driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(driver);
        actions.contextClick(el).perform();
        driver.switchTo().alert().accept();
    }

    @Test
    public void pressingKeys() {
        driver.get("https://the-internet.herokuapp.com/key_presses");
        el = driver.findElement(By.id("target"));
        el.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_RIGHT).pause(1000).perform();
        el= driver.findElement(By.id("result"));
        assertEquals("Pressed right arrow key","You entered: RIGHT", el.getText());
    }

    @Test
    public void linkTest() {
        driver.get("https://ultimateqa.com/simple-html-elements-for-automation/");
        el= driver.findElement(By.linkText("Clickable Icon"));
        String link = el.getAttribute("href");
        assertEquals("https://ultimateqa.com/link-success/",link);
        assertEquals("padding-box", el.getCssValue("background-origin"));
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
