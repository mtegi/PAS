package books.controller;

import books.model.Book;
import books.service.BookService;
import books.utils.BookIdManager;
import com.fasterxml.jackson.databind.JsonNode;
import items.copies.service.CopyService;
import model.AbstractRestEntityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestBookController extends AbstractRestEntityController<Book> {

    @Autowired
    public RestBookController(BookService bookService, CopyService copyService, BookIdManager idManager){
        super(bookService, copyService, idManager);
    }

    @Override
    @PostMapping("/create")
    public String Create(@RequestBody JsonNode body) {
        return null;
    }

    @Override
    @PutMapping("/update/{id}")
    public void Update(@PathVariable("id") int bookId, @RequestBody JsonNode body) {

    }

}
