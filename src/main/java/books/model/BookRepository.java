package books.model;

import model.EntityRepository;
import model.IDataProvider;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository extends EntityRepository<Book> {

    private Book emptyBook;
    public BookRepository(IDataProvider<Book> provider) {
        super(provider);
        this.emptyBook = new Book(-1, "No Title", new Author("-","-"));
    }

    @Override
    public Book getEmptyEntity() {
        return emptyBook;
    }

}
