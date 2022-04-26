import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdvanceSearchPage {

    WebDriver driver;

    @FindBy(id="all-words")
    WebElement allWords;
    @FindBy(id="least-words")
    WebElement leastWords;
    @FindBy(id="facet-start-year")
    WebElement startYear;
    @FindBy(id="submit-advanced-search")
    WebElement submit;

    public AdvanceSearchPage(WebDriver driver){
        this.driver = driver;
        driver.get("https://link.springer.com/advanced-search");
        PageFactory.initElements(driver, this);
    }

    public void submitCokies() {
        driver.findElement(By.cssSelector("button.cc-banner__button-accept")).click();
    }

    public void selectInDate() {
        Select dateOption = new Select(driver.findElement(By.id("date-facet-mode")));
        dateOption.selectByVisibleText("in");
    }

    public void fillInAllWords(String words){
        allWords.sendKeys(words);
    }

    public void fillInAtLeastOneWord(String words){
        leastWords.sendKeys(words);
    }

    public void fillInYear(String year){
        startYear.sendKeys(year);
    }

    public void submit() {
        submit.click();
    }
}
