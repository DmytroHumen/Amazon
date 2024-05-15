package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonPage {
    private WebDriver driver;
    private final By acceptCookiesButton = By.xpath("//span[contains(text(), 'Accept')]/../input");
    private final By filterBooksLink = By.linkText("Books");
    private final By searchBox = By.id("twotabsearchtextbox");
    private final By searchButton = By.xpath("//input[@value='Go']");

    public AmazonPage(WebDriver driver) {
        this.driver = driver;
    }

    public Book getExpectedBookInfo() {
        String bookName = driver.findElement(By.id("productTitle")).getText();
        String author = driver.findElement(By.id("byLineInfo")).getText();
        String price = driver.findElement(By.xpath("//span[contains(text(), \"49\")]")).getText();
        boolean isBestseller = driver.findElements(By.cssSelector(".a-badge.bestseller")).isEmpty();
        return  new Book(bookName, author, price, isBestseller);
    }

    public void goToHomePage(String url) {
        driver.get(url);
    }

    public void acceptCookies() {
        driver.findElement(acceptCookiesButton).click();
    }

    public void filterBooks() {
        driver.findElement(filterBooksLink).click();
    }

    public void searchFor(String keyword) {
        driver.findElement(searchBox).sendKeys(keyword);
        driver.findElement(searchButton).click();
    }

}
