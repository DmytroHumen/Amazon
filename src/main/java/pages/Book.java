package pages;

import java.util.Objects;

public class Book {

    private String bookName;
    private String author;
    private String price;
    private boolean isBestseller;



    public Book(String bookName, String author, String price, boolean isBestseller) {
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.isBestseller = isBestseller;
    }

    public String getBookName() {
        return bookName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isBestseller == book.isBestseller && Objects.equals(bookName, book.bookName) && Objects.equals(author, book.author) && Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookName, author, price, isBestseller);
    }


}