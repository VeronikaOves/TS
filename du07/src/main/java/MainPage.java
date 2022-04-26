import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    WebDriver driver;

    @FindBy(id="advanced-search-link")
    WebElement searchLink;
    @FindBy(className = "open-search-options")
    WebElement searchOptions;
    @FindBy(className="register-link")
    WebElement registerLink;
    @FindBy(id="query")
    WebElement searchQuery;

    public MainPage(WebDriver driver){
        this.driver = driver;
        driver.get("https://link.springer.com/");
        PageFactory.initElements(driver, this);
    }

    public void clickAdvancedSearch() {
        searchOptions.click();
        searchLink.click();
    }

    public void clickRegisterLink() {
        registerLink.click();
    }

    public void searchByValue(String value) {
        searchQuery.sendKeys(value);
        searchQuery.sendKeys(Keys.ENTER);
    }
}
