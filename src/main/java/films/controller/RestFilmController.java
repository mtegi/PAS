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
@RequestMapping("/rest/films/")
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
        JsonNode author = body.get("director");
        if(entityService.add(new Film(idManager.nextId(),title, new Author(author.get("firstname").asText(), author.get("lastname").asText()), releaseDate))){
            return "success";
        }
        return "failed to add film";
    }

    @Override
    @PutMapping("/update/{id}")
    public void Update(@PathVariable("id") int filmId, @RequestBody JsonNode body) {
        Film film = entityService.get(filmId);
        if (film == null)
            throw new NullPointerException("Edited film doesn't exist");
        if (body.hasNonNull("title")){
            String title = body.get("title").asText();
            if(!title.equals("")) film.setTitle(title);
        }
        if(body.has("author")){
            JsonNode author = body.get("director");
            if(author.hasNonNull("firstname")){
                String firstname = author.get("firstname").asText();
                if (!firstname.equals("")) film.getDirector().setFirstName(firstname);
            }
            if(author.hasNonNull("lastname")){
                String lastname = author.get("lastname").asText();
                if (!lastname.equals("")) film.getDirector().setLastName(lastname);
            }
        }
        if(body.hasNonNull("releaseDate")){
            int releaseDate = body.get("releaseDate").asInt();
            film.setReleaseDate(releaseDate);
        }
    }

}
