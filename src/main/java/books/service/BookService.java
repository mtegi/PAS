package books.service;

import books.model.Book;
import books.model.BookRepository;
import model.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService extends EntityService<Book> {

    @Autowired
    public BookService(BookRepository repository) {
        super(repository);
    }

    public Book getEmptyBook()
    {
      return this.getEmptyEntity();
    }

}
