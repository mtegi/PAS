package items.filmCopies.model;

import model.AbstractCopyRepository;
import model.IDataProvider;
import org.springframework.stereotype.Repository;


    @Repository
    public class FilmCopyRepository extends AbstractCopyRepository<FilmCopy> {

        public FilmCopyRepository(IDataProvider<FilmCopy> provider) {
            super(provider);
        }
    }

