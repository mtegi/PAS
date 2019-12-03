package allocations.controller;

import allocations.model.Allocation;
import allocations.service.AllocationService;
import allocations.utils.AllocationIdManager;
import books.controller.BookController;
import copies.model.Copy;
import copies.service.ICopyService;
import login.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

@Controller
public class AllocationController {
    private AllocationIdManager idManager;
    private AllocationService allocationService;
    private IUserService userService;
    private ICopyService copyService;
    private BookController bookController;

    @Autowired
    public AllocationController(AllocationService allocationService, IUserService userService, ICopyService copyService, BookController bookController, AllocationIdManager idManager) {
        this.allocationService = allocationService;
        this.userService = userService;
        this.copyService = copyService;
        this.idManager = idManager;
        this.bookController = bookController;
    }

    @GetMapping({"/my-allocations"})
    public String getUserAllocations (Model model, Principal principal) {
        ArrayList<Allocation> allocations = allocationService.getUserAllocations(principal.getName());
        model.addAttribute("allocations", allocations);
        return "my-allocations";
    }

    @PostMapping({"/manager/all-allocations"})
    public String filterAllocations (@RequestParam(name = "filterStr") String filterStr, Model model) {
        if(filterStr.contentEquals("")) viewAllAllocations(model);
        ArrayList<Allocation>  allocations = allocationService.getFilteredAllocations(filterStr);
        if(allocations != null) model.addAttribute("allocations", allocations);
        return "all-allocations";
    }

    @GetMapping({"/manager/all-allocations"})
    public String viewAllAllocations (Model model) {
        ArrayList<Allocation> allocations = allocationService.getAll();
        model.addAttribute("allocations", allocations);
        return "all-allocations";
    }

    @PostMapping({"/completeAllocation"})
    public String completeAllocation(@RequestParam("id") int id, Model model){
        allocationService.completeBorrowing(allocationService.get(id));
        return "redirect:/my-allocations";
    }

    @PostMapping({"/borrow"})
    public String borrowBook(
            @RequestParam("bookId") int bookId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("type") String type,
            Model model, Principal principal){

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        model.addAttribute("allocationError", false);
        model.addAttribute("allocationSuccess", false);

        LocalDateTime startTime = null, endTime = null;
        try {
            startTime = formatter.parse(startDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            endTime =  formatter.parse(endDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        } catch (ParseException e){
            model.addAttribute("allocationError", true);
            model.addAttribute("allocationErrorMsg", "Parse exception:" + e.getMessage());
            return bookController.viewAllBooks(model);
        }

        Copy copy = copyService.getCopy(bookId, type);
        if(copy == null || allocationService.isBorrowed(copy,startTime,endTime)){
            model.addAttribute("allocationError", true);
            model.addAttribute("allocationErrorMsg", "No copy available right now");
        }
        else if(startTime.isAfter(endTime) || endTime.isBefore(LocalDateTime.now())){
            model.addAttribute("allocationError", true);
            model.addAttribute("allocationErrorMsg", "Wrong date!");
        }
       else if(!allocationService.add(new Allocation(idManager.nextId(),copy,userService.findByUsername(principal.getName()),startTime,endTime))) {
            model.addAttribute("allocationError", true);
            model.addAttribute("allocationErrorMsg", "Loan failed");
        } else {
            model.addAttribute("allocationSuccess", true);
            model.addAttribute("allocationSuccessMsg", "Loan successful");
        }

        return bookController.viewAllBooks(model);
    }

}
