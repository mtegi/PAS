package controller;

import books.Book;
import books.BookCompareByAuthor;
import books.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ShowBooksController {

    private BookRepository bookRepository;

    @Autowired
    public ShowBooksController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping({"/books"})
    public String handleGETRequest (Model model) {
        ArrayList<Book> books = bookRepository.getAll();
        books.sort(new BookCompareByAuthor());
        model.addAttribute("books",books);
        return "books";
    }

    @PostMapping({"/books"})
    public String filterBooks (@RequestParam(name = "filterStr") String filterStr, Model model) {
        if(filterStr.contentEquals("")) handleGETRequest(model);
        ArrayList<Book> books = bookRepository.getBooksByTitle(filterStr);
        books.sort(new BookCompareByAuthor());
        model.addAttribute("books",books);
        return "books";
    }


}
