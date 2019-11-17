package copies;

import books.model.Book;
import model.IAllocable;

public class Copy implements IAllocable {
    private int copyID;
    private Book book;
    private boolean borrowed;

    public Copy(int copyID, Book book) {
        this.copyID = copyID;
        this.book = book;
        this.setBorrowed(false);
    }


    public int getCopyID() {
        return copyID;
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
}
