import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchPageTest {

    WebDriver driver;
    SearchPage searchPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Veronika\\Desktop\\CVUT\\TS\\Rep\\TS\\du07\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1600, 900));
        searchPage = new SearchPage(driver,"https://link.springer.com/search?query=Page+AND+Object+AND+Model+AND+%28Sellenium+OR+Testing%29&date-facet-mode=in&facet-start-year=2022&showAll=true");
        searchPage.submitCokies();

    }

    @Test
    public void checkType() {
        //ARRANGE
        String type = "Article";

        //ACT
        searchPage.selectType("Article");

        //ASSERT
        Assertions.assertTrue((driver.findElement(By.className("facet-constraint-message")).getText().contains(type)));

    }

    @Test
    public void checkPageNumber() {
        //ARRANGE
        Integer pageNumber = 4;

        //ACT
        searchPage.setPageNumber(pageNumber);

        //ASSERT
        Assertions.assertEquals(pageNumber.toString(), driver.findElement(By.cssSelector("input[name='go-to-page']")).getAttribute("value"));
    }

}
