import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.opencart.com/");
        driver.manage().window().maximize();
        BasePage.setDriver(driver);
    }

    @AfterMethod
    public void quite() {
        BasePage.getDriver().quit();
    }

}
