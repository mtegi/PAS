package books.controller;

import books.model.Author;
import books.model.Book;
import books.service.IBookService;
import books.utils.BookCompareByAuthor;
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

    private IBookService bookService;

    @Autowired
    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/books"})
    public String handleGETRequest (Model model) {
        ArrayList<Book> books = (ArrayList<Book>) bookService.getAll();
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
            if(!bookService.add(new Book(title,author)))
                model.addAttribute("errorHappened",true);
                model.addAttribute("errorMsg", "Book already exists");
            }
        return handleGETRequest(model);
    }


}
