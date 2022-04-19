import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicSelenium {
    WebDriver driver;
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Veronika\\Desktop\\CVUT\\JAVA\\semka\\lab09\\chromedriver.exe");
        driver = new ChromeDriver();

    }
        @Test
     public void seleniumTest(){
         FlightForm flightForm = new FlightForm(driver);
         String firstName = "Ivan";
         String lastName = "Ivanov";
         String email = "myemail@email.com";
         String birthDay = "1132002";

         flightForm.setFirstName(firstName);
         flightForm.setLastName(lastName);
         flightForm.setEmail(email);
         flightForm.setBirthday(birthDay);

         flightForm.sendForm();

            WebElement fullName = driver.findElement(By.cssSelector("tr:nth-child(1) td"));
            Assertions.assertEquals(firstName + " " + lastName, fullName.getText());
     }


}
