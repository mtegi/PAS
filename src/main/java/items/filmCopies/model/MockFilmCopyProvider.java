package items.filmCopies.model;

import films.service.FilmService;
import model.IDataProvider;
import model.IIdManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;

    @Component
    public class MockFilmCopyProvider implements IDataProvider<FilmCopy> {

        private IIdManager idManager;
        private FilmService filmService;
        @Override
        public void fill(Collection<FilmCopy> data) {
            data.add(new FilmCopy(idManager.nextId(), filmService.get(1)));
            data.add(new FilmCopy(idManager.nextId(), filmService.get(1)));
            data.add(new FilmCopy(idManager.nextId(), filmService.get(2)));
            data.add(new FilmCopy(idManager.nextId(), filmService.get(2)));
            data.add(new FilmCopy(idManager.nextId(), filmService.get(3)));
            data.add(new FilmCopy(idManager.nextId(), filmService.get(3)));
            data.add(new FilmCopy(idManager.nextId(), filmService.get(4)));
            data.add(new FilmCopy(idManager.nextId(), filmService.get(5)));

        }

        @Autowired
        public void setFilmService(FilmService filmService) {
            this.filmService = filmService;
        }

        @Qualifier("copyIdManager")
        @Autowired
        public void setIdManager(IIdManager idManager) {
            this.idManager = idManager;
        }
    }

