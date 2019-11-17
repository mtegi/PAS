package books.service;

import books.model.Book;
import books.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class BookService implements IBookService {
    private BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean add(Book book) {
        return repository.add(book);
    }

    @Override
    public Collection<Book> getAll() {
        return repository.getAll();
    }

    @Override
    public ArrayList<Book> getBooksByTitle(String filterStr) {
        return repository.getBooksByTitle(filterStr);
    }
}
