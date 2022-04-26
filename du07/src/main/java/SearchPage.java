import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchPage {

    WebDriver driver;

    @FindBy(className = "title")
    WebElement title;
    @FindBy(className = "facet-constraint-message")
    WebElement type;


    public SearchPage(WebDriver driver, String link) {
        this.driver = driver;
        driver.get(link);
        PageFactory.initElements(driver, this);
    }

    public void selectType(String type) {
        List<WebElement> links = driver.findElement(By.id("content-type-facet"))
        .findElements(By.className("facet-link"));

       for (WebElement link : links) {
           if (link.findElement(By.className("facet-title")).getText().equals(type)) {
               link.click();
               break;
           }
       }
    }

    public void submitCokies() {
        driver.findElement(By.cssSelector("button.cc-banner__button-accept")).click();
    }

    public void setPageNumber(Integer number) {
        WebElement pageNumber = driver.findElement(By.cssSelector("input[name='go-to-page']"));
        pageNumber.clear();
        pageNumber.sendKeys(number.toString());
        pageNumber.sendKeys(Keys.ENTER);
    }

    public ArrayList<String> getPublicationLinks(int number) {
        ArrayList<String> result = new ArrayList<>();
        List<WebElement> publications = driver.findElements(By.className("title"));

        for (int i = 0; i < number; i++) {
            result.add(publications.get(i).getAttribute("href"));
        }

        return result;
    }

}
