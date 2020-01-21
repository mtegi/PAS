package items.filmCopies.controller;

import allocations.service.AllocationService;
import com.fasterxml.jackson.databind.JsonNode;
import films.service.FilmService;
import items.filmCopies.model.FilmCopy;
import items.filmCopies.service.FilmCopyService;
import items.utils.CopyIdManager;
import model.AbstractRestItemController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/film-copies/")
public class RestFilmCopyController extends AbstractRestItemController<FilmCopy> {
    private CopyIdManager idManager;
    private FilmService entityService;

    @Autowired
    public RestFilmCopyController(FilmCopyService copyService, FilmService filmService, CopyIdManager idManager, AllocationService allocationService) {
        super(copyService,filmService,allocationService);
        this.entityService = filmService;
        this.idManager = idManager;
    }

    @Override
    @PostMapping("/create")
    public String Create(@RequestBody JsonNode body) {
        int filmId = body.get("filmId").asInt();
        if(!entityService.containsId(filmId))
            throw new IllegalArgumentException("Incorrect ID argument");
        copyService.add(new FilmCopy(idManager.nextId(), entityService.get(filmId)));
        return "success";
    }

    @Override
    @PutMapping("/update/{id}")
    public void Update(@PathVariable("id") int copyId, @RequestBody JsonNode body) {
        FilmCopy copy = copyService.get(copyId);
        if(copy==null)
            throw new IllegalArgumentException("The copy you have been editing no longer exists");
        int filmId = body.get("filmId").asInt();
        if (entityService.containsId(filmId)) copy.setEntity(entityService.get(filmId));
        else throw new IllegalArgumentException("Incorrect ID argument");
    }
}
