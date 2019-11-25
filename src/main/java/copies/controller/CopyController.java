package copies.controller;

import copies.model.Copy;
import copies.service.CopyService;
import copies.utils.CopyIdManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String handleGETRequest (Model model) {
        ArrayList<Copy> copies = copyService.getAll();
        model.addAttribute("copies", copies);
        return "copies";
    }
}
