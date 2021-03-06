package books.controller;

import books.model.Author;
import books.model.Book;
import books.service.BookService;
import books.utils.BookCompareByAuthor;
import books.utils.BookIdManager;
import items.copies.service.CopyService;
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
    private CopyService copyService;

    @Autowired
    public BookController(BookService bookService, CopyService copyService, BookIdManager idManager) {
        this.bookService = bookService;
        this.idManager = idManager;
        this.copyService = copyService;
    }

    @GetMapping({"/books"})
    public String viewAllBooks(Model model) {
        ArrayList<Book> books = bookService.getAll();
        books.sort(new BookCompareByAuthor());
        model.addAttribute("books",books);
        model.addAttribute("service", copyService);
        return "books";
    }

    @PostMapping({"/books"})
    public String filterBooks (@RequestParam(name = "filterStr") String filterStr, Model model) {
        if(filterStr.contentEquals("")) viewAllBooks(model);
        ArrayList<Book> books = bookService.getEntitiesByTitle(filterStr);
        books.sort(new BookCompareByAuthor());
        model.addAttribute("books",books);
        model.addAttribute("service", copyService);
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
        return viewAllBooks(model);
    }

    @PostMapping({"/manager/deletebook"})
    public  String deleteBook(@RequestParam("bookId") int bookId, Model model){
            model.addAttribute("errorHappened",false);

            if(!bookService.remove(bookId)) {
                model.addAttribute("errorHappened", true);
                model.addAttribute("errorMsg", "Book not found");
            }

            copyService.replaceEntityWithNull(bookId, bookService.getEmptyEntity());


        return viewAllBooks(model);
    }

    @GetMapping({"/manager/editbook"})
    public  String editBookPage(@RequestParam("bookId") int bookId, Model model) {
        Book book = bookService.get(bookId);

        if(book==null)
        {
            model.addAttribute("errorHappened",true);
            model.addAttribute("errorMsg", "The book you have chosen no longer exists");
            return viewAllBooks(model);
        }
        model.addAttribute("book", book);
        return "bookEdit";
    }


    @PostMapping({"/manager/editbook"})
    public  String editBook(@RequestParam("bookId") int bookId, @RequestParam("title") String title,
                            @RequestParam("firstName") String firstname, @RequestParam("lastName") String lastname,
                            Model model) {

        model.addAttribute("errorHappened",false);
        Book book = bookService.get(bookId);

        try {
            if (book == null)
                throw new NullPointerException("Edytowana ksiązka nie istnieje");

            if (title != "")
                book.setTitle(title);
            if (firstname != "")
                book.getAuthor().setFirstName(firstname);
            if (lastname != "")
                book.getAuthor().setLastName(lastname);
        }
        catch (NullPointerException e)
        {
            model.addAttribute("errorHappened",true);
            model.addAttribute("errorMsg", e.getMessage());
        }
        finally {
            return viewAllBooks(model);
        }

    }


}
