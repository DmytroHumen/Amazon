import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AmazonMainPage;
import pages.AmazonSearchResultPage;
import pages.Book;

import java.util.List;

public class AmazonTest {
    private WebDriver driver;
    private AmazonMainPage homePage;
    private AmazonSearchResultPage searchResultPage;
    private String searchKeyWord;

    @Before
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\tools\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new AmazonMainPage(driver);
        searchResultPage = new AmazonSearchResultPage(driver);
        driver.manage().window().maximize();
        searchKeyWord = System.getProperty("searchParam");
    }

    @Test
    public void testBookSearch() {
        homePage.goToHomePage("https://www.amazon.com.ua/");
        homePage.acceptCookies();
        homePage.filterBooks();
        homePage.searchFor(searchKeyWord);
        List<Book> listOfBooks = searchResultPage.saveBooksInfoFromFirstPage();
        searchResultPage.clickBook();
        Book actualBook = searchResultPage.getBookInfoByName("Java von Kopf bis Fuß: Eine abwechslungsreiche Entdeckungsreise durch die objektorientierte Programmierung");
        Book expectedBook = homePage.getExpectedBookInfo();
        Assert.assertEquals(expectedBook, actualBook);

    }

    @After
    public void afterTest() {
        driver.quit();
    }
}

