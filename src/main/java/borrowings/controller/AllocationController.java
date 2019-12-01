package borrowings.controller;

import books.controller.BookController;
import borrowings.model.Borrowing;
import borrowings.service.BorrowingService;
import borrowings.utils.AllocationIdManager;
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
    private BorrowingService borrowingService;
    private IUserService userService;
    private ICopyService copyService;
    private BookController bookController;

    @Autowired
    public AllocationController(BorrowingService borrowingService, IUserService userService, ICopyService copyService, BookController bookController, AllocationIdManager idManager) {
        this.borrowingService = borrowingService;
        this.userService = userService;
        this.copyService = copyService;
        this.idManager = idManager;
        this.bookController = bookController;
    }

    @GetMapping({"/my-allocations"})
    public String getUserAllocations (Model model, Principal principal) {
        ArrayList<Borrowing> allocations = borrowingService.getUserAllocations(principal.getName());
        model.addAttribute("allocations", allocations);
        return "my-allocations";
    }

    @GetMapping({"/manager/all-allocations"})
    public String viewAllAllocations (Model model) {
        ArrayList<Borrowing> allocations = borrowingService.getAll();
        model.addAttribute("allocations", allocations);
        return "all-allocations";
    }

    @PostMapping({"/completeAllocation"})
    public String completeAllocation(@RequestParam("id") int id, Model model){
        borrowingService.completeBorrowing(borrowingService.get(id));
        return "redirect:/my-allocations";
    }

    @PostMapping({"/borrow"})
    public String borrowBook(@RequestParam("bookId") int bookId, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, Model model, Principal principal){
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
        Copy copy = copyService.getCopy(bookId);
        if(copy == null || borrowingService.isBorrowed(copy,startTime,endTime)){
            model.addAttribute("allocationError", true);
            model.addAttribute("allocationErrorMsg", "No copy available right now");
        }
        else if(startTime.isAfter(endTime) || endTime.isBefore(LocalDateTime.now())){
            model.addAttribute("allocationError", true);
            model.addAttribute("allocationErrorMsg", "Wrong date!");
        }
       else if(!borrowingService.add(new Borrowing(idManager.nextId(),copy,userService.findByUsername(principal.getName()),startTime,endTime))) {
            model.addAttribute("allocationError", true);
            model.addAttribute("allocationErrorMsg", "Loan failed");
        } else {
            model.addAttribute("allocationSuccess", true);
            model.addAttribute("allocationSuccessMsg", "Loan successful");
        }

        return bookController.viewAllBooks(model);
    }

}
