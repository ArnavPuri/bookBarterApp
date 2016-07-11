package in.teachcoder.bookbarter.model;

/**
 * Created by Arnav on 09-Jul-16.
 */
public class BookItem {
    String title, author, genre, owner, mode;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public BookItem() {
    }

    public BookItem(String title, String author, String genre, String owner, String mode) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.owner = owner;

        this.mode = mode;
    }
}
