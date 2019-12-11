package films.model;

import books.model.Author;
import model.EntityRepository;
import model.IDataProvider;
import org.springframework.stereotype.Repository;

@Repository
public class FilmRepository extends EntityRepository<Film> {
    public FilmRepository(IDataProvider<Film> provider) {
        super(provider);
    }


    @Override
    public Film getEmptyEntity() {
     return new Film(-1,"No Title", new Author("-","-"),0);
    }

}
