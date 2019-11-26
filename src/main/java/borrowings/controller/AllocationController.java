package borrowings.controller;

import borrowings.model.Borrowing;
import borrowings.service.BorrowingService;
import borrowings.utils.AllocationIdManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class AllocationController {
    private AllocationIdManager idManager;
    private BorrowingService service;

    @Autowired
    public AllocationController(BorrowingService service, AllocationIdManager idManager) {
        this.service = service;
        this.idManager = idManager;
    }

    @GetMapping({"/my-allocations"})
    public String getUserAllocations (Model model) {
        ArrayList<Borrowing> borrowings = service.getAll();
        model.addAttribute("borrowings", borrowings);
        return "my-allocations";
    }

    @GetMapping({"/manager/all-allocations"})
    public String viewAllAllocations (Model model) {
        ArrayList<Borrowing> allocations = service.getAll();
        model.addAttribute("allocations", allocations);
        System.out.println(allocations.get(0).getItem().getTitle());
        return "all-allocations";
    }
}
