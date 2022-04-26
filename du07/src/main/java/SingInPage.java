import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SingInPage {

    WebDriver driver;

    @FindBy(id = "login-box-email")
    WebElement emailLogin;
    @FindBy(id = "login-box-pw")
    WebElement pwdLogin;

    @FindBy(id = "first-name")
    WebElement firstName;
    @FindBy(id = "last-name")
    WebElement lastName;
    @FindBy(id = "email-address")
    WebElement emailRegistration;
    @FindBy(id = "password")
    WebElement pwdRegistration;
    @FindBy(id = "password-confirm")
    WebElement pwdRegistrationConfirm;


    public SingInPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://link.springer.com/signup-login/");
        PageFactory.initElements(driver, this);
    }

    public void submitCokies() {
        driver.findElement(By.cssSelector("button.cc-banner__button-accept")).click();
    }

    public void fillInEmail(String email) {
        emailLogin.sendKeys(email);
    }

    public void fillInPassword(String pwd) {
        pwdLogin.sendKeys(pwd);
    }

    public void submitLogin() {
        driver.findElement(By.cssSelector(".btn[title='Log in']")).click();
    }

    public void submitRegistration() {
        driver.findElement(By.cssSelector(".btn[title='Create account'")).click();
    }
}
