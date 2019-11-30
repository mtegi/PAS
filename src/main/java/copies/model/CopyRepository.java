package copies.model;

import books.model.Book;
import books.service.BookService;
import model.IDataProvider;
import model.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CopyRepository extends MapRepository<Copy> {
    private BookService bookService;

    @Autowired
    public CopyRepository(IDataProvider<Copy> provider, BookService bookService) {
        super(provider);
        this.bookService = bookService;
    }

    public Copy getCopy(int bookId){
        Book book = bookService.get(bookId);
        return container.values().stream().filter(copy -> copy.getBook().getId() == bookId).findAny().orElse(null);
    }
}
