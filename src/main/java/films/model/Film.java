package films.model;

import books.model.Author;
import model.IAllocable;
import model.IEntity;
import model.IMapable;

public class Film implements IMapable, IAllocable, IEntity {

    private int id;
    private String title;
    private Author director;
    private int releaseDate;

    public Film(int id, String title, Author director, int releaseDate) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
    }

    @Override
    public String getTitle() {
      return this.title;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getDirector() {
        return director;
    }

    public void setDirector(Author director) {
        this.director = director;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }
}
