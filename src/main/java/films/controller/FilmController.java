package films.controller;

import books.model.Author;
import books.utils.BookIdManager;
import films.model.Film;
import films.service.FilmService;
import items.filmCopies.service.FilmCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class FilmController {

    private BookIdManager idManager;
    private FilmService filmService;
    private FilmCopyService copyService;

    @Autowired
    public FilmController(FilmService filmService, FilmCopyService copyService, BookIdManager idManager) {
        this.filmService = filmService;
        this.idManager = idManager;
        this.copyService = copyService;
    }

    @GetMapping({"/films"})
    public String viewAllFilms(Model model) {
        ArrayList<Film> films = filmService.getAll();
        model.addAttribute("films",films);
        model.addAttribute("service", copyService);
        return "films";
    }

    @PostMapping({"/films"})
    public String filterBooks (@RequestParam(name = "filterStr") String filterStr, Model model) {
        if(filterStr.contentEquals("")) viewAllFilms(model);
        ArrayList<Film> films = filmService.getEntitiesByTitle(filterStr);
        model.addAttribute("films", films);
        model.addAttribute("service", copyService);
        return "films";
    }

    @PostMapping({"/manager/addfilm"})
    public  String addBook(@RequestParam("title") String title, @RequestParam("release") int releaseDate, @Valid @ModelAttribute(name = "author") Author author, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("errorHappened",true);
            model.addAttribute("errorMsg", "null field");
        }else{
            model.addAttribute("errorHappened",false);
            if(!filmService.add(new Film(idManager.nextId(),title,author,releaseDate)))
                model.addAttribute("errorHappened",true);
            model.addAttribute("errorMsg", "Film already exists");
        }
        return viewAllFilms(model);
    }

    @PostMapping({"/manager/deletefilm"})
    public  String deleteBook(@RequestParam("filmId") int filmId, Model model) {
        model.addAttribute("deleteError", false);
        if (!filmService.remove(filmId)) {
            model.addAttribute("deleteError", true);
            model.addAttribute("deleteErrorMsg", "Film not found");
        }
        return viewAllFilms(model);
    }
}
