import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightForm {
    WebDriver driver;

    @FindBy(id = "flight_form_firstName")
    WebElement firstName;
    @FindBy(id = "flight_form_lastName")
    WebElement lastName;
    @FindBy(id = "flight_form_email")
    WebElement email;
    @FindBy(id = "flight_form_birthDate")
    WebElement birthday;
    @FindBy(id = "flight_form_submit")
    WebElement submit;

    public FlightForm(WebDriver driver){
        this.driver = driver;
        driver.get("https://flight-order.herokuapp.com");
        PageFactory.initElements(driver, this);
    }

    public void setFirstName(String firstNameStr) {
        firstName.sendKeys(firstNameStr);
    }

    public void setLastName(String lastNameStr) {
        lastName.sendKeys(lastNameStr);
    }

    public  void setEmail(String emailStr) {
        email.sendKeys(emailStr);
    }

    public void setBirthday(String birthDayStr) {
        birthday.sendKeys(birthDayStr.substring(0,3));
        birthday.sendKeys(Keys.TAB);
        birthday.sendKeys(birthDayStr.substring(2,6));
    }

    public void sendForm() {
        submit.click();
    }
}
