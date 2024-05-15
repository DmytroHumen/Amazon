import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AmazonPage;
import pages.AmazonSearchResultPage;
import pages.Book;

public class AmazonTest {
    private WebDriver driver;
    private AmazonPage homePage;
    private AmazonSearchResultPage searchResultPage;

    @Before
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "D:\\tools\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new AmazonPage(driver);
        searchResultPage = new AmazonSearchResultPage(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void testBookSearch() {

        homePage.goToHomePage("https://www.amazon.com.ua/");
        homePage.acceptCookies();
        homePage.filterBooks();
        homePage.searchFor("Java");

        Book expectedBook = homePage.getExpectedBookInfo();
        searchResultPage.acceptCookies();
        Book actualBook = searchResultPage.getBookInfoByName("Java von Kopf bis Fuß: Eine abwechslungsreiche Entdeckungsreise durch die objektorientierte Programmierung");

        Assert.assertEquals(expectedBook, actualBook);
    }

    @After
    public void afterTest() {
        driver.quit();
    }
}
