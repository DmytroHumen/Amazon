import io.qameta.allure.Description;
import io.qameta.allure.Step;
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
    @Description
    public void testBookSearch() {
        goToHomePage();
        acceptCookies();
        filterBooks();
        searchForKeyWord();
        List<Book> listOfBooks = saveBooksInfoFromFirstPage();
        verifyBookInList(listOfBooks);


//        searchResultPage.clickBook();
//        Book expectedBook = homePage.getExpectedBookInfo();
//        Assert.assertTrue("Book not found", listOfBooks.contains(expectedBook));
    }

    @Step
    public void goToHomePage() {
        homePage.goToHomePage("https://www.amazon.com.ua/");
    }

    @Step
    public void acceptCookies() {
        homePage.acceptCookies();
    }

    @Step
    public void filterBooks() {
        homePage.filterBooks();
    }

    @Step
    public void searchForKeyWord() {
        homePage.searchFor(searchKeyWord);
    }

    @Step
    public List<Book> saveBooksInfoFromFirstPage() {
        return searchResultPage.saveBooksInfoFromFirstPage();
    }

    @Step
    public void verifyBookInList(List<Book> listOfBook) {
        searchResultPage.clickBook();
        Book expectedBook = homePage.getExpectedBookInfo();
        Assert.assertTrue("Book not found", listOfBook.contains(expectedBook));
    }


    @After
    public void afterTest() {
        driver.quit();
    }

}