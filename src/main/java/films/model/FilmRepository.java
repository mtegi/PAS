package films.model;

import model.EntityRepository;
import model.IDataProvider;
import org.springframework.stereotype.Repository;

@Repository
public class FilmRepository extends EntityRepository<Film> {
    public FilmRepository(IDataProvider<Film> provider) {
        super(provider);
    }
}
