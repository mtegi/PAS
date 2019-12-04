package films.service;

import model.EntityRepository;
import model.EntityService;
import films.model.Film;
import org.springframework.stereotype.Service;

@Service
public class FilmService extends EntityService<Film> {
    public FilmService(EntityRepository<Film> repository) {
        super(repository);
    }
}
