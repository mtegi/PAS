package copies.utils;

import model.IIdManager;
import org.springframework.stereotype.Component;

@Component
public class CopyIdManager implements IIdManager {

    private int bookId;

    public CopyIdManager() {
        this.bookId = 0;
    }

    @Override
    public int nextId() {
        bookId++;
        return bookId;
    }
}