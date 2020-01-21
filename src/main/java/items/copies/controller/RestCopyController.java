package items.copies.controller;


import allocations.service.AllocationService;
import books.service.BookService;
import com.fasterxml.jackson.databind.JsonNode;
import items.copies.model.AudioBook;
import items.copies.model.Copy;
import items.copies.model.CopyFactory;
import items.copies.model.PaperBook;
import items.copies.service.CopyService;
import model.AbstractRestItemController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static items.copies.model.BookType.AudioBook;
import static items.copies.model.BookType.PaperBook;

@RestController
@RequestMapping("/rest/copies/")
public class RestCopyController extends AbstractRestItemController<Copy> {
    private CopyFactory copyFactory;
    private BookService entityService;

    @Autowired
    public RestCopyController(CopyService copyService, CopyFactory copyFactory, BookService bookService, AllocationService allocationService) {
        super(copyService,bookService,allocationService);
        this.entityService = bookService;
        this.copyFactory = copyFactory;
    }

    @Override
    @PostMapping("/create")
    public String Create(@RequestBody JsonNode body) {
        int bookId = body.get("bookId").asInt();
        String type = body.get("copyType").asText();
        if (!entityService.containsId(bookId))
            throw new IllegalArgumentException("Incorrect ID argument");
        switch (type) {
            case AudioBook:
                if (copyService.add(copyFactory.createCopyAudio(bookId, body.get("time").asText()))) {
                    return "success";
                }
            case PaperBook:
                if (copyService.add(copyFactory.createCopyPaper(bookId, body.get("pages").asInt()))) {
                    return "success";
                }
            default:
                throw new IllegalArgumentException("Copy type not supported");
        }
    }

    @Override
    @PutMapping("/update/{id}")
    public void Update(@PathVariable("id") int copyId, @RequestBody JsonNode body) {
        Copy copy = copyService.get(copyId);
        if (copy == null)
            throw new IllegalArgumentException("The copy you have been editing no longer exists");
        if (body.hasNonNull("bookId")) {
            int bookId = body.get("bookId").asInt();
            if (entityService.containsId(bookId))
                copy.setEntity(entityService.get(bookId));
            else
                throw new IllegalArgumentException("Incorrect ID argument");
        }

        if (copy.getBookType() instanceof items.copies.model.PaperBook) {
            if (body.hasNonNull("pages")) {
                int pages = body.get("pages").asInt();
                if (pages > 0)
                    ((PaperBook) copy.getBookType()).setPages(pages);
                else
                    throw new IllegalArgumentException("Incorrect page number");
            }
        } else if (copy.getBookType() instanceof items.copies.model.AudioBook) {
            if (body.hasNonNull("length")) {
                String length = body.get("length").asText();
                if (length.matches("^(\\d\\d:\\d\\d:\\d\\d)"))
                    ((AudioBook) copy.getBookType()).setDuration(length);
                else
                    throw new IllegalArgumentException("Incorrect date format");
            }
        }
    }

}
