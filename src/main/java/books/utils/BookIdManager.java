package books.utils;

import model.IIdManager;
import org.springframework.stereotype.Component;

@Component
public class BookIdManager implements IIdManager {

    private int bookId;

    public BookIdManager() {
        this.bookId = 0;
    }

    @Override
    public int nextId() {
        bookId++;
        return bookId;
    }
}
