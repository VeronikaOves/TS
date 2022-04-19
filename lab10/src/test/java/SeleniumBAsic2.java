import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumBAsic2 {
   WebDriver driver;
   @BeforeEach
   public void setUp() {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Veronika\\Desktop\\CVUT\\JAVA\\semka\\lab09\\chromedriver.exe");
    driver = new ChromeDriver();
 }

 @Test
 public void flightFullTest() {
       String firstName = "Tom";
       String lastName = "Ivanov";
    driver.get("https://flight-order.herokuapp.com");
     driver.findElement(By.id("flight_form_firstName")).sendKeys("Tom");
     driver.findElement(By.id("flight_form_lastName")).sendKeys("Ivanov");
     driver.findElement(By.id("flight_form_email")).sendKeys("myemail@mail.com");
     driver.findElement(By.id("flight_form_birthDate")).sendKeys("113");

     driver.findElement(By.id("flight_form_birthDate")).sendKeys(Keys.TAB);
     driver.findElement(By.id("flight_form_birthDate")).sendKeys("2000");

     Select selectDestination = new Select(driver.findElement(By.id("flight_form_destination")));
     selectDestination.selectByVisibleText("London");

    WebElement btn = driver.findElement(By.id("flight_form_submit"));

    btn.click();

    WebElement fullName = driver.findElement(By.cssSelector("tr:nth-child(1) td"));
     Assertions.assertEquals(firstName + " " + lastName, fullName.getText());
 }
}

