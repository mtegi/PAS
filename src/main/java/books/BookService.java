package books;

import model.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private IRepository<Book> repository;

    @Autowired
    public BookService(IRepository<Book> repository) {
        this.repository = repository;
    }
}
