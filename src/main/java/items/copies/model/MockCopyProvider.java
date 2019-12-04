package items.copies.model;

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
        data.add(new Copy(idManager.nextId(), bookService.get(1), new PaperBook(280)));
        data.add(new Copy(idManager.nextId(), bookService.get(1), new AudioBook(4 * 60 * 60 * 1000)));
        data.add(new Copy(idManager.nextId(), bookService.get(2), new PaperBook(450)));
        data.add(new Copy(idManager.nextId(), bookService.get(2), new PaperBook(522)));
        data.add(new Copy(idManager.nextId(), bookService.get(3), new AudioBook(6 * 60 * 60 * 1000)));
        data.add(new Copy(idManager.nextId(), bookService.get(4), new PaperBook(436)));
        data.add(new Copy(idManager.nextId() ,bookService.get(5), new PaperBook(686)));
        data.add(new Copy(idManager.nextId(), bookService.get(5), new PaperBook(346)));
        data.add(new Copy(idManager.nextId(), bookService.get(5), new PaperBook(465)));
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
