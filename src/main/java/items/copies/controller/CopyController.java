package items.copies.controller;

import allocations.service.AllocationService;
import books.service.BookService;
import items.copies.model.AudioBook;
import items.copies.model.Copy;
import items.copies.model.CopyFactory;
import items.copies.model.PaperBook;
import items.copies.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CopyController {
    private CopyService copyService;
    private CopyFactory copyFactory;
    private BookService bookService;
    private AllocationService allocationService;


    @Autowired
    public CopyController(CopyService copyService, CopyFactory copyFactory, BookService bookService,AllocationService allocationService) {
        this.copyService = copyService;
        this.copyFactory = copyFactory;
        this.bookService = bookService;
        this.allocationService = allocationService;

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
        model.addAttribute("copyError",false);
        allocationService.replaceWithNull(id);

        if(! copyService.delete(id)) {
            model.addAttribute("copyError", true);
            model.addAttribute("copyErrorMsg", "Error while deleting");
        }
        return viewAll(model);
    }

    @PostMapping({"/manager/addCopy"})
    public String addCopy (@RequestParam(name = "bookId") int bookId,
                           @RequestParam(name = "type") String type,
                           @RequestParam(name = "lenght-pages") int pages,
                           @RequestParam(name = "lenght-time") String time,
                           Model model)
    {
       model.addAttribute("copyError",false);
        try {
            if(!bookService.containsId(bookId))
                throw new IllegalArgumentException("Incorrect ID argument");
            copyService.add(copyFactory.createCopy(bookId, type, pages, time));
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

    @GetMapping({"/manager/editcopy"})
    public  String editCopyPage(@RequestParam("Id") int copyId, Model model) {


        Copy copy = copyService.get(copyId);

        model.addAttribute("copy",copy);
        return "copyEdit";
    }


    @PostMapping({"/manager/editcopy"})
    public  String editCopy(@RequestParam("copyId") int copyId, @RequestParam("bookId") Optional<Integer> bookId,
                            @RequestParam("pages") Optional<Integer> pages, @RequestParam("lenght") Optional<String> lenght,
                            Model model) {

        Copy copy = copyService.get(copyId);
        model.addAttribute("copyError",false);

        try {
            if(bookId.isPresent()) {
                if (bookService.containsId(bookId.get()))
                    copy.setEntity(bookService.get(bookId.get()));
                else
                    throw new IllegalArgumentException("Incorrect ID argument");
            }


          if(copy.getBookType() instanceof PaperBook) {
              if (pages.isPresent())
                  if (pages.get() > 0)
                      ((PaperBook) copy.getBookType()).setPages(pages.get());
                  else
                      throw new IllegalArgumentException("Incorrect page number");
          }

            if(copy.getBookType() instanceof AudioBook) {
                if (lenght.get() != "")
                    if (lenght.get().matches("^(\\d\\d:\\d\\d:\\d\\d)"))
                        ((AudioBook) copy.getBookType()).setDuration(lenght.get());
                    else
                        throw new IllegalArgumentException("Incorrect date format");
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
