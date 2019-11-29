package books.service;

import books.model.Book;
import books.model.BookRepository;
import model.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService extends AbstractService<Book>  implements IBookService {
    private BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public ArrayList<Book> getBooksByTitle(String filterStr) {
        return repository.getBooksByTitle(filterStr);
    }
}
