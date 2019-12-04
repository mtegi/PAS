package items.filmCopies.controller;

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

@Controller
public class FilmCopyController {
    private CopyIdManager idManager;
    private FilmCopyService copyService;

    @Autowired
    public FilmCopyController(FilmCopyService copyService, CopyIdManager idManager) {
        this.copyService = copyService;
        this.idManager = idManager;
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
}
