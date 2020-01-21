package films.controller;

import books.model.Author;
import com.fasterxml.jackson.databind.JsonNode;
import films.model.Film;
import films.service.FilmService;
import films.utils.FilmIdManager;
import items.filmCopies.service.FilmCopyService;
import model.AbstractRestEntityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestFilmController extends AbstractRestEntityController<Film> {

    @Autowired
    public RestFilmController(FilmService filmService, FilmCopyService copyService, FilmIdManager idManager){
        super(filmService, copyService, idManager);
    }

    @Override
    @PostMapping("/create")
    public String Create(@RequestBody JsonNode body) {
        String title = body.get("title").asText();
        int releaseDate = body.get("releaseDate").asInt();
        JsonNode author = body.get("author");
        if(entityService.add(new Film(idManager.nextId(),title, new Author(author.get("firstname").asText(), author.get("lastname").asText()), releaseDate))){
            return "success";
        }
        return "failed to add film";
    }

    @Override
    @PutMapping("/update/{id}")
    public void Update(@PathVariable("id") int filmId, @RequestBody JsonNode body) {

    }

}
