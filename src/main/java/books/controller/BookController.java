package books.controller;

import books.model.Author;
import books.model.Book;
import books.service.BookService;
import books.utils.BookCompareByAuthor;
import books.utils.BookIdManager;
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
public class BookController {

    private BookIdManager idManager;
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService, BookIdManager idManager) {
        this.bookService = bookService;
        this.idManager = idManager;
    }

    @GetMapping({"/books"})
    public String handleGETRequest (Model model) {
        ArrayList<Book> books = bookService.getAll();
        books.sort(new BookCompareByAuthor());
        model.addAttribute("books",books);
        return "books";
    }

    @PostMapping({"/books"})
    public String filterBooks (@RequestParam(name = "filterStr") String filterStr, Model model) {
        if(filterStr.contentEquals("")) handleGETRequest(model);
        ArrayList<Book> books = bookService.getBooksByTitle(filterStr);
        books.sort(new BookCompareByAuthor());
        model.addAttribute("books",books);
        return "books";
    }

    @PostMapping({"/manager/addbook"})
    public  String addBook(@RequestParam("title") String title, @Valid @ModelAttribute(name = "author") Author author, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("errorHappened",true);
            model.addAttribute("errorMsg", "null field");
        }else{
            model.addAttribute("errorHappened",false);
            if(!bookService.add(new Book(idManager.nextId(),title,author)))
                model.addAttribute("errorHappened",true);
                model.addAttribute("errorMsg", "Book already exists");
            }
        return handleGETRequest(model);
    }

    @PostMapping({"/manager/deletebook"})
    public  String deleteBook(@RequestParam("bookId") int bookId, Model model){
            model.addAttribute("deleteError",false);
            if(!bookService.remove(bookId)) {
                model.addAttribute("deleteError", true);
                model.addAttribute("deleteErrorMsg", "Book not found");
            }
        return handleGETRequest(model);
    }


}
