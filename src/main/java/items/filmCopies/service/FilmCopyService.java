package items.filmCopies.service;

import films.model.Film;
import items.filmCopies.model.FilmCopy;
import model.AbstractCopyRepository;
import model.AbstractCopyService;
import org.springframework.stereotype.Service;

@Service
public class FilmCopyService extends AbstractCopyService<FilmCopy> {
    public FilmCopyService(AbstractCopyRepository<FilmCopy> repository) {
        super(repository);
    }
    public void replaceFilmWithNull ( int filmId, Film nullFilm)
    {
        repository.getCopiesByEntityId(filmId).forEach(copy -> copy.setEntity(nullFilm));
    }
}
