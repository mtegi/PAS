package copies.model;

import books.model.Book;
import model.IAllocable;
import model.IMapable;

import javax.validation.constraints.NotNull;

public class Copy implements IAllocable, IMapable {
    @NotNull
    private int copyID;
    @NotNull
    private Book book;
    @NotNull
    private boolean borrowed;

    public Copy(int copyID, Book book) {
        this.copyID = copyID;
        this.book = book;
        this.setBorrowed(false);
    }

    public Book getBook() {
        return book;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    @Override
    public String getTitle() {
        return this.book.getTitle();
    }

    @Override
    public int getId() {
        return copyID;
    }
}
