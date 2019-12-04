package films.utils;

import model.IIdManager;
import org.springframework.stereotype.Component;

@Component
public class FilmIdManager implements IIdManager {

    private int filmId;

    public FilmIdManager() {
        this.filmId = 0;
    }

    @Override
    public int nextId() {
        filmId++;
        return filmId;
    }
}