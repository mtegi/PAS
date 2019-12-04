package items.copies.controller;

import items.copies.model.Copy;
import items.copies.service.CopyService;
import items.utils.CopyIdManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class CopyController {
    private CopyIdManager idManager;
    private CopyService copyService;

    @Autowired
    public CopyController(CopyService copyService, CopyIdManager idManager) {
        this.copyService = copyService;
        this.idManager = idManager;
    }

    @GetMapping({"/manager/copies"})
    public String viewAll(Model model) {
        ArrayList<Copy> copies = copyService.getAll();
        model.addAttribute("copies", copies);
        return "copies";
    }

    @PostMapping({"/manager/copies"})
    public String filterBooks (@RequestParam(name = "filterStr") String title, Model model) {
        if(title.contentEquals("")) viewAll(model);
        ArrayList<Copy> copies = copyService.getCopiesByEntityTitle(title);
        model.addAttribute("copies", copies);
        return "copies";
    }
}
