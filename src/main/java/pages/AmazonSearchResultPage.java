package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class AmazonSearchResultPage {

    private final WebDriver driver;
    private final By clickOnBook = By.xpath("//*[contains(text(), \"Java von Kopf bis Fuß: Eine abwechslungsreiche Entdeckungsreise durch die objektorientierte Programmierung\")]");


    public AmazonSearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickBook() {
        driver.findElement(clickOnBook).click();
    }

    public List<Book> saveBooksInfoFromFirstPage() {
        List<Book> listOfBooks = new ArrayList<>();
        List<WebElement> results = driver.findElements(By.cssSelector("div[data-component-type='s-search-result']"));
        for (WebElement result : results) {
            String bookName = result.findElement(By.className("a-size-medium")).getText();
            String author = result.findElement(By.className("a-size-base")).getText();
            String price = result.findElement(By.className("a-price")).getText();
            boolean isBestseller = !result.findElements(By.cssSelector(".a-badge.bestseller")).isEmpty();

            Book book = new Book(bookName, author, price, isBestseller);
            listOfBooks.add(book);
        }

        return listOfBooks;
    }

    public Book getBookInfoByName(String bookName) {
        for (Book book : saveBooksInfoFromFirstPage()) {
            if (book.getBookName().equals(bookName));
            return book;
        }
        return null;
    }
}
