package books.controller;

import books.model.Book;
import books.utils.BookCompareByAuthor;
import books.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ShowBooksController {

    private IBookService bookService;

    @Autowired
    public ShowBooksController(IBookService bookService) {
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


}
