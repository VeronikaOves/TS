import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ProcessTest {
    //email: kraboday@yandex.ru
    //password: Mypassword11

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Veronika\\Desktop\\CVUT\\TS\\Rep\\TS\\du07\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1600, 900));
    }

    @AfterEach
    public void setOut() {
        driver.quit();
    }

    @Test
    public void processTestByScenario() {
        //Find articles
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAdvancedSearch();

        AdvanceSearchPage advanceSearchPage = new AdvanceSearchPage(driver);
        String allWords = "Page Object Model";
        String atLeastOneWord = "Sellenium Testing";
        String year = "2022";
        String expectedResult = "Page AND Object AND Model AND (Sellenium OR Testing)";

        try {
            advanceSearchPage.submitCokies();
        } catch (Exception jopa) {
        }

        advanceSearchPage.fillInAllWords(allWords);
        advanceSearchPage.fillInAtLeastOneWord(atLeastOneWord);
        advanceSearchPage.selectInDate();
        advanceSearchPage.fillInYear(year);
        advanceSearchPage.submit();

        Assertions.assertTrue(driver.findElement(By.id("number-of-search-results-and-search-terms")).getText().contains(expectedResult));

        //Read and save information about first four articles
        SearchPage searchPage = new SearchPage(driver, driver.getCurrentUrl());
        searchPage.selectType("Article");

        int number = 4;

        ArrayList<String> links = searchPage.getPublicationLinks(number);

        //Prepare information to save in it CSV

        StringBuilder dataForCSV = new StringBuilder();
        for (String link : links) {
            ArticlePage page = new ArticlePage(driver, link);
            dataForCSV.append(page.getName()).append(", ");
            dataForCSV.append(page.getDOI()).append(", ");
            dataForCSV.append(page.getPublishedDate()).append("\n");
        }

        //Write it in CSV
        try {
            File file = new File("src/test/resources/data.csv");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter("src/test/resources/data.csv");
            fileWriter.write(dataForCSV.toString());
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void logInAndSearchByArticleName_CheckIfInformationEquals(String name, String DOI, String date) {
        SingInPage singInPage = new SingInPage(driver);
        String email = "kraboday@yandex.ru";
        String pwd = "MyPassword11";
        String userName = "Veronika Ovsyannikova";

        singInPage.submitCokies();
        singInPage.fillInEmail(email);
        singInPage.fillInPassword(pwd);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        singInPage.submitLogin();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Assertions.assertEquals(userName, driver.findElement(By.id("user")).getText());

        MainPage mainPage = new MainPage(driver);
        mainPage.searchByValue(name);

        SearchPage searchPage = new SearchPage(driver, driver.getCurrentUrl());
        ArticlePage articlePage = new ArticlePage(driver, searchPage.getPublicationLinks(1).get(0));
        Assertions.assertEquals(DOI, articlePage.getDOI());
        Assertions.assertEquals(date, articlePage.getPublishedDate());

    }



}
