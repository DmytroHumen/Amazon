package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AmazonSearchResultPage {

    private WebDriver driver;
    private final By acceptCookiesButton = By.xpath("//span[contains(text(), 'Accept')]/../input");
    private final By bookLinks = By.cssSelector("div[data-component-type='s-search-result'] a.a-link-normal");

    public AmazonSearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptCookies() {
        driver.findElement(acceptCookiesButton).click();
    }

    public Book getBookInfoByName(String bookName) {
        List<WebElement> results = driver.findElements(By.cssSelector("div[data-component-type='s-search-result']"));
        for (WebElement result : results) {
            String resultBookName = result.findElement(By.className("a-size-medium")).getText();
            if (resultBookName.contains(bookName)) {
                String author = result.findElement(By.className("a-size-base")).getText();
                String price = result.findElement(By.className("a-price")).getText();
                boolean isBestseller = !result.findElements(By.cssSelector(".a-badge.bestseller")).isEmpty();
                return new Book(resultBookName, author, price, isBestseller);
            }
        }
        return null;
    }

}
