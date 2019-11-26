package books.service;

import books.model.Book;
import books.model.BookRepository;
import model.AbstractService;
import model.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService extends AbstractService<Book>  implements IBookService {
    private BookRepository repository;

    @Autowired
    public BookService(MapRepository<Book> repository) {
        super(repository);
    }

    @Override
    public ArrayList<Book> getBooksByTitle(String filterStr) {
        return repository.getBooksByTitle(filterStr);
    }
}
