package items.utils;

import model.IIdManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("copyIdManager")
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