package items.filmCopies.model;

import films.model.Film;
import model.IAllocable;
import model.ICopy;
import model.IMapable;

import javax.validation.constraints.NotNull;

public class FilmCopy implements IAllocable, IMapable, ICopy<Film> {
    @NotNull
    private int copyID;
    @NotNull
    private Film film;


    public FilmCopy(@NotNull int copyID, @NotNull Film film) {
        this.setCopyID(copyID);
        this.setFilm(film);
    }

    @Override
    public int getId() {
        return copyID;
    }

    public void setCopyID(int copyID) {
        this.copyID = copyID;
    }

    public Film getEntity() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String getTitle() {
        return this.getEntity().getTitle();
    }
}

