package copies.model;

import books.model.Book;
import model.IAllocable;
import model.IMapable;

public class Copy implements IAllocable, IMapable {
    private int copyID;
    private Book book;
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
    public int getId() {
        return copyID;
    }
}
