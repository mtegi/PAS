package books.model;

import model.EntityRepository;
import model.IDataProvider;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository extends EntityRepository<Book> {

    public BookRepository(IDataProvider<Book> provider) {
        super(provider);
    }



}
