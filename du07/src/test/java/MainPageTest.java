import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPageTest {
    WebDriver driver;


    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Veronika\\Desktop\\CVUT\\TS\\Rep\\TS\\du07\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1600, 900));

    }

    @Test
    public void redirectFromMainPageToLogInPage() {
        //ARRANGE
        MainPage mainPage = new MainPage(driver);
        String expectedLink = "https://link.springer.com/signup-login?previousUrl=https%3A%2F%2Flink.springer.com%2F";

        //ACT
        mainPage.clickRegisterLink();

        //ASSERT
        Assertions.assertEquals(expectedLink, driver.getCurrentUrl());
    }

    @Test
    public void redirectFromMainPageToAdvanceSearchPage() {
        //ARRANGE
        MainPage mainPage = new MainPage(driver);
        String expectedLink = "https://link.springer.com/advanced-search";

        //ACT
        mainPage.clickAdvancedSearch();

        //ASSERT
        Assertions.assertEquals(expectedLink, driver.getCurrentUrl());
    }
}
