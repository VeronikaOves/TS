import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumBAsic2 {
   WebDriver driver;
   @BeforeEach
   public void setUp() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Veronika\\Desktop\\CVUT\\JAVA\\semka\\lab09\\chromedriver.exe");
    driver = new ChromeDriver();
 }

 @Test
 public void flightFormTest() {
    driver.get("https://flight-order.herokuapp.com/");
     driver.findElement(By.id("flight_form_firstName")).sendKeys("Honza");
     Select selectDestination = new Select(driver.findElement(By.id("flight_form_destination")));
     selectDestination.selectByVisibleText("London");
     driver.findElement(By.cssSelector("body > div > div > form > div > button")).click();

 }
}

