package items.filmCopies.controller;

import films.service.FilmService;
import items.filmCopies.model.FilmCopy;
import items.filmCopies.service.FilmCopyService;
import items.utils.CopyIdManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class FilmCopyController {
    private CopyIdManager idManager;
    private FilmCopyService copyService;
    private FilmService filmService;

    @Autowired
    public FilmCopyController(FilmCopyService copyService, FilmService filmService, CopyIdManager idManager) {
        this.copyService = copyService;
        this.idManager = idManager;
        this.filmService=filmService;
    }

    @GetMapping({"/manager/film-copies"})
    public String viewAll(Model model) {
        ArrayList<FilmCopy> copies = copyService.getAll();
        model.addAttribute("copies", copies);
        return "filmCopies";
    }

    @PostMapping({"/manager/film-copies"})
    public String filterBooks (@RequestParam(name = "filterStr") String title, Model model) {
        if(title.contentEquals("")) viewAll(model);
        ArrayList<FilmCopy> copies = copyService.getCopiesByEntityTitle(title);
        model.addAttribute("copies", copies);
        return "filmCopies";
    }

    @PostMapping({"/manager/addFilmCopy"})
    public String addCopy (@RequestParam(name = "filmId") int filmId, Model model)
    {
        model.addAttribute("copyError",false);
        try {
            if(!filmService.containsId(filmId))
                throw new IllegalArgumentException("Incorrect ID argument");
            copyService.add(new FilmCopy(idManager.nextId(),filmService.get(filmId)));
        }
        catch( IllegalArgumentException e)
        {
            model.addAttribute("copyError", true);
            model.addAttribute("copyErrorMsg", e.getMessage());
        }
        finally {
            return viewAll(model);
        }
    }

    @PostMapping({"/manager/deleteFilmCopy"})
    public String deleteCopy (@RequestParam(name = "id") int id, Model model)
    {
        model.addAttribute("copyError",false);
        if(! copyService.delete(id)) {
            model.addAttribute("copyError", true);
            model.addAttribute("copyErrorMsg", "Error while deleting");
        }
        return viewAll(model);
    }

    @GetMapping({"/manager/editFilmCopy"})
    public String editCopyPage(@RequestParam("id") int id, Model model) {
        FilmCopy copy = copyService.get(id);
        model.addAttribute("copy",copy);
        return "filmCopyEdit";
    }

    @PostMapping({"/manager/editFilmCopy"})
    public  String editCopy(@RequestParam("copyId") int copyId, @RequestParam("filmId") Optional<Integer> filmId, Model model){
        FilmCopy copy = copyService.get(copyId);
        model.addAttribute("copyError",false);

        try {
            if(copy==null)
                throw new IllegalArgumentException("The copy you have beed editing no longer exists");

            if (filmId.isPresent()) {
                if (filmService.containsId(filmId.get()))
                    copy.setEntity(filmService.get(filmId.get()));
                else
                    throw new IllegalArgumentException("Incorrect ID argument");
            }
        }
       catch( IllegalArgumentException e)
            {
                model.addAttribute("copyError", true);
                model.addAttribute("copyErrorMsg", e.getMessage());
            }
        finally {
                return viewAll(model);
            }
    }

}
