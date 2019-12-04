package films.model;

import books.model.Author;
import films.utils.FilmIdManager;
import model.IDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MockFilmProvider implements IDataProvider<Film> {
    @Autowired
    public void setIdManager(FilmIdManager idManager) {
        this.idManager = idManager;
    }

    private FilmIdManager idManager;

    @Override
    public void fill(Collection<Film> films) {
        Author scorsese = new Author("Martin","Scorsese");
        Author nolan = new Author("Christopher","Nolan");
        films.add(new Film(idManager.nextId(),"The Dark Knight", nolan,2008));
        films.add(new Film(idManager.nextId(),"Dunkirk", nolan,2017));
        films.add(new Film(idManager.nextId(),"Inception",nolan,2010));
        films.add(new Film(idManager.nextId(),"Cape Fear",scorsese,1991));
        films.add(new Film(idManager.nextId(),"Casino",scorsese,1995));
    }
}
