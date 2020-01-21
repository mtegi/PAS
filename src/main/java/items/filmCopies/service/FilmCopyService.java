package items.filmCopies.service;

import items.filmCopies.model.FilmCopy;
import model.AbstractCopyRepository;
import model.AbstractCopyService;
import org.springframework.stereotype.Service;

@Service
public class FilmCopyService extends AbstractCopyService<FilmCopy> {
    public FilmCopyService(AbstractCopyRepository<FilmCopy> repository) {
        super(repository);
    }
}
