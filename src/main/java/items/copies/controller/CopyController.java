package items.copies.controller;

import items.copies.model.Copy;
import items.copies.model.CopyFactory;
import items.copies.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class CopyController {
    private CopyService copyService;
    private CopyFactory copyFactory;


    @Autowired
    public CopyController(CopyService copyService, CopyFactory copyFactory) {
        this.copyService = copyService;
        this.copyFactory = copyFactory;
    }

    @GetMapping({"/manager/copies"})
    public String viewAll(Model model) {
        ArrayList<Copy> copies = copyService.getAll();
        model.addAttribute("copies", copies);
        return "copies";
    }

    @PostMapping({"/manager/copies"})
    public String filterCopies (@RequestParam(name = "filterStr") String title, Model model) {
        if(title.contentEquals("")) viewAll(model);
        ArrayList<Copy> copies = copyService.getCopiesByEntityTitle(title);
        model.addAttribute("copies", copies);
        return "copies";
    }



    @PostMapping({"/manager/deleteCopy"})
    public String deleteCopy (@RequestParam(name = "id") int id, Model model)
    {
        model.addAttribute("deleteError",false);
        if(! copyService.deleteCopy(id)) {
            model.addAttribute("deleteError", true);
            model.addAttribute("deleteErrorMsg", "Error while deleting");
        }
        return viewAll(model);
    }

    @PostMapping({"/manager/addCopy"})
    public ModelAndView addCopy (@RequestParam(name = "bookId") int bookId, @RequestParam(name = "type") String type, Model model)
    {
        copyService.addCopy(copyFactory.createCopy(bookId,type,"0"));

       return new ModelAndView("redirect:/books");
    }
}
