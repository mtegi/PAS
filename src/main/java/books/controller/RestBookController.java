package books.controller;

import books.model.Author;
import books.model.Book;
import books.service.BookService;
import books.utils.BookIdManager;
import com.fasterxml.jackson.databind.JsonNode;
import items.copies.service.CopyService;
import model.AbstractRestEntityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/books/")
public class RestBookController extends AbstractRestEntityController<Book> {

    @Autowired
    public RestBookController(BookService bookService, CopyService copyService, BookIdManager idManager){
        super(bookService, copyService, idManager);
    }

    @Override
    @PostMapping("/create")
    public String Create(@RequestBody JsonNode body) {
        String title = body.get("title").asText();
        JsonNode author = body.get("author");
        if(entityService.add(new Book(idManager.nextId(),title, new Author(author.get("firstname").asText(), author.get("lastname").asText())))){
            return "success";
        }
        return "failed to add book";
    }

    @Override
    @PutMapping("/update/{id}")
    public void Update(@PathVariable("id") int bookId, @RequestBody JsonNode body) {
        Book book = entityService.get(bookId);
        if (book == null)
            throw new NullPointerException("Edytowana ksiązka nie istnieje");
        if (body.hasNonNull("title")){
            String title = body.get("title").asText();
            if(!title.equals("")) book.setTitle(title);
        }
        if(body.has("author")){
            JsonNode author = body.get("author");
            if(author.hasNonNull("firstname")){
                String firstname = author.get("firstname").asText();
                if (!firstname.equals("")) book.getAuthor().setFirstName(firstname);
            }
            if(author.hasNonNull("lastname")){
                String lastname = author.get("lastname").asText();
                if (!lastname.equals("")) book.getAuthor().setLastName(lastname);
            }
        }
    }

}
