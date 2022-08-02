import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

class OrderCardTest {

    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @Test
    void OrderCardTest() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id=\"name\"] input")).sendKeys("Павлов Сергей");
        driver.findElement(By.cssSelector("span[data-test-id=\"phone\"] input")).sendKeys("+79614365879");
        driver.findElement(By.cssSelector("label[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("[type=\"button\"]")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=\"order-success\"]")).getText();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @AfterEach
    void teardown() {
        driver.quit();
        driver = null;
    }
}