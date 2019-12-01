package copies.model;

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
        return container.values().stream().filter(copy -> copy.getBook().getId() == bookId && !copy.isBorrowed()).findAny().orElse(null);
    }
}
