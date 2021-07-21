import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloSelenium2Test {
    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @Test
    public void smarterTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.ncore.pro");
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void anotherTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.youtube.com");
        Thread.sleep(3000);
        driver.quit();
    }


}
