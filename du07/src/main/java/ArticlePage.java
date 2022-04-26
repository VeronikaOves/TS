import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ArticlePage {

    WebDriver driver;

    public ArticlePage(WebDriver driver, String link) {
        this.driver = driver;
        driver.get(link);
        PageFactory.initElements(driver, this);
    }

    public String getName() {
        return driver.findElement(By.className("c-article-title")).getText();
    }

    public String getDOI() {
        return driver.findElement(By.cssSelector
                (".c-bibliographic-information__list-item--doi .c-bibliographic-information__value"))
                .getText();
    }

    public String getPublishedDate() {
        WebElement publishedDate = driver.findElement(By.cssSelector("[data-track-action = 'publication date'] time"));

        return publishedDate.getText();
    }
    public void submitCokies() {
        driver.findElement(By.cssSelector("button.cc-banner__button-accept")).click();
    }
}
