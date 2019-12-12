package allocations.controller;

import allocations.model.Allocation;
import allocations.service.AllocationService;
import allocations.utils.AllocationIdManager;
import books.controller.BookController;
import films.controller.FilmController;
import items.copies.model.Copy;
import items.copies.service.CopyService;
import items.filmCopies.model.FilmCopy;
import items.filmCopies.service.FilmCopyService;
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
    private CopyService copyService;
    private FilmCopyService filmCopyService;
    private BookController bookController;
    private FilmController filmController;

    @Autowired
    public AllocationController(AllocationService allocationService, IUserService userService, CopyService copyService, FilmCopyService filmCopyService, BookController bookController, AllocationIdManager idManager, FilmController filmController) {
        this.allocationService = allocationService;
        this.userService = userService;
        this.copyService = copyService;
        this.idManager = idManager;
        this.bookController = bookController;
        this.filmCopyService = filmCopyService;
        this.filmController = filmController;
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

    @PostMapping(value = "/completeAllocationAll", params = {"id"})
    public String completeAllocationAll(@RequestParam("id") int id, Model model){
        allocationService.completeBorrowing(allocationService.get(id));
        return viewAllAllocations(model);
    }

    @PostMapping(value = "/completeAllocation", params = {"id"})
    public String completeAllocation(@RequestParam("id") int id, Model model){
        allocationService.completeBorrowing(allocationService.get(id));
        return "redirect:/my-allocations";
    }

    @PostMapping(value = "/completeAllocation", params = {"id", "username"})
    public String completeAllocation(@RequestParam("id") int id, @RequestParam("username") String username, Model model){
        allocationService.completeBorrowing(allocationService.get(id));
        return showUserAllocations(username,model);
    }

    @PostMapping(value = "/completeAllocation", params = {"id", "copyId"})
    public String completeAllocation(@RequestParam("id") int id, @RequestParam("copyId") int copyId, Model model){
        allocationService.completeBorrowing(allocationService.get(id));
        return showCopyAllocations(copyId,model);
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
       else if(!allocationService.add(new Allocation(idManager.nextId(), copy,userService.findByUsername(principal.getName()),startTime,endTime))) {
            model.addAttribute("allocationError", true);
            model.addAttribute("allocationErrorMsg", "Loan failed");
        } else {
            model.addAttribute("allocationSuccess", true);
            model.addAttribute("allocationSuccessMsg", "Loan successful");
        }

        return bookController.viewAllBooks(model);
    }

    @PostMapping({"/borrowFilm"})
    public String borrowFilm(
            @RequestParam("filmId") int filmId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
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

        FilmCopy copy = filmCopyService.findCopyByEntityId(filmId);
        if(copy == null || allocationService.isBorrowed(copy,startTime,endTime)){
            model.addAttribute("allocationError", true);
            model.addAttribute("allocationErrorMsg", "No copy available right now");
        }
        else if(startTime.isAfter(endTime) || endTime.isBefore(LocalDateTime.now())){
            model.addAttribute("allocationError", true);
            model.addAttribute("allocationErrorMsg", "Wrong date!");
        }
        else if(!allocationService.add(new Allocation(idManager.nextId(), copy,userService.findByUsername(principal.getName()),startTime,endTime))) {
            model.addAttribute("allocationError", true);
            model.addAttribute("allocationErrorMsg", "Loan failed");
        } else {
            model.addAttribute("allocationSuccess", true);
            model.addAttribute("allocationSuccessMsg", "Loan successful");
        }
        return filmController.viewAllFilms(model);
    }

    @GetMapping({"/admin/userallocations"})
    public String showUserAllocations(@RequestParam("username") String username, Model model)
    {
        ArrayList<Allocation> allocations = allocationService.getUserAllocations(username);
        model.addAttribute("allocations", allocations);
        model.addAttribute("username",username);
        return "user-allocations";
    }

    @GetMapping({"/manager/copyallocations"})
    public String showCopyAllocations(@RequestParam("copyId") int id, Model model)
    {
        ArrayList<Allocation> allocations = allocationService.getCopyAllocations(id);
        model.addAttribute("allocations", allocations);
        model.addAttribute("copyId",id);
        return "copy-allocations";
    }

}
