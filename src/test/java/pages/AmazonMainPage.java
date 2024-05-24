package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonMainPage {

    private WebDriver driver;
    private final By acceptCookiesButton = By.xpath("//span[contains(text(), 'Accept')]/../input");
    private final By filterBooksLink = By.linkText("Books");
    private final By searchBox = By.id("twotabsearchtextbox");
    private final By searchButton = By.xpath("//input[@value='Go']");

    public AmazonMainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Go to home page")
    public void goToHomePage(String url) {
        driver.get(url);
    }

    @Step("Accept cookies")
    public void acceptCookies() {
        driver.findElement(acceptCookiesButton).click();
    }

    @Step("Filter books")
    public void filterBooks() {
        driver.findElement(filterBooksLink).click();
    }

    @Step("Search for the keyword")
    public void searchFor(String keyword) {
        driver.findElement(searchBox).sendKeys(keyword);
        driver.findElement(searchButton).click();
    }

    @Step("Get expected book info")
    public Book getExpectedBookInfo() {
        String bookName = driver.findElement(By.id("productTitle")).getText();
        String author = driver.findElement(By.id("byLineInfo")).getText();
        String price = driver.findElement(By.xpath("//span[contains(text(), \"49\")]")).getText();
        boolean isBestseller = driver.findElements(By.cssSelector(".a-badge.bestseller")).isEmpty();
        return  new Book(bookName, author, price, isBestseller);
    }

}
