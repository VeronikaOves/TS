import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Veronika\\Desktop\\CVUT\\TS\\Rep\\TS\\du07\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1600, 900));
    }

    @org.junit.jupiter.api.Test
    public void test() {
        MoodleLogIn moodleLogIn = new MoodleLogIn(driver);
        moodleLogIn.pressLogInButton();
        driver.findElement(By.id("username")).sendKeys("ovsyaver");
        driver.findElement(By.id("password")).sendKeys("Walkundermoon11");
        driver.findElement(By.className("btn")).click();
        driver.get("https://moodle.fel.cvut.cz/mod/quiz/view.php?id=233484");
        driver.findElement(By.className("btn-primary")).click();
        driver.findElement(By.id("id_submitbutton")).click();
        driver.findElement(By.className("answer")).sendKeys("Veronika Ovsyannikova paralelka 102");

    }
}
