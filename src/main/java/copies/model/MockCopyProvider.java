package copies.model;

import books.service.BookService;
import model.IDataProvider;
import model.IIdManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MockCopyProvider implements IDataProvider<Copy> {

    private IIdManager idManager;
    private BookService bookService;
    @Override
    public void fill(Collection<Copy> data) {
        data.add(new Copy(idManager.nextId(), bookService.get(1)));
        data.add(new Copy(idManager.nextId(), bookService.get(1)));
        data.add(new Copy(idManager.nextId(), bookService.get(2)));
        data.add(new Copy(idManager.nextId(), bookService.get(2)));
        data.add(new Copy(idManager.nextId(), bookService.get(3)));
        data.add(new Copy(idManager.nextId(), bookService.get(4)));
        data.add(new Copy(idManager.nextId() ,bookService.get(5)));
        data.add(new Copy(idManager.nextId(), bookService.get(5)));
        data.add(new Copy(idManager.nextId(), bookService.get(5)));
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Qualifier("copyIdManager")
    @Autowired
    public void setIdManager(IIdManager idManager) {
        this.idManager = idManager;
    }
}
