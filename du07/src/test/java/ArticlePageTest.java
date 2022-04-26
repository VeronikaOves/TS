import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ArticlePageTest {
    WebDriver driver;
    ArticlePage articlePage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Veronika\\Desktop\\CVUT\\TS\\Rep\\TS\\du07\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1600, 900));
        articlePage = new ArticlePage(driver, "https://link.springer.com/article/10.1007/s41095-021-0253-5");
        articlePage.submitCokies();
    }


    @Test
    public void getName_returnsName() {
        //ARRANGE
        String expectedResult = "Rendering discrete participating media using geometrical optics approximation";

        //ACT
        String result = articlePage.getName();

        //ASSERT
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void getDOI_returnsDOI() {
        //ARRANGE
        String expectedResult = "https://doi.org/10.1007/s41095-021-0253-5";

        //ACT
        String result = articlePage.getDOI();

        //ASSERT
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void getPublishedDate_returnsPublishedDate() {
        //ARRANGE
        String expectedResult = "01 April 2022";

        //ACT
        String result = articlePage.getPublishedDate();

        //ASSERT
        Assertions.assertEquals(expectedResult, result);
    }
}
