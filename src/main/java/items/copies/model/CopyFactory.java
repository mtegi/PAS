package items.copies.model;


import books.model.BookRepository;
import books.service.BookService;
import items.utils.CopyIdManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CopyFactory {

    @Autowired
   CopyIdManager idManager;

    @Autowired
   BookService bookService;


  public Copy createCopy (int bookId, String CopyType, int pages, String time)
    {
       switch(CopyType) {
           case "PAPERBOOK":
               return new Copy(idManager.nextId(),bookService.get(bookId),new PaperBook(pages));
           case "AUDIOBOOK":
               return new Copy(idManager.nextId(),bookService.get(bookId),new AudioBook(time));
           default:
               throw  new IllegalArgumentException("Niepoprawny typ kopii");
       }

}


}
