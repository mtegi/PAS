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


  public Copy createCopy (int bookId, String CopyType, String lenght)
    {
       switch(CopyType) {
           case "PAPERBOOK":
               return new Copy(idManager.nextId(),bookService.get(bookId),new PaperBook(Integer.parseInt(lenght)));
           case "AUDIOBOOK":
               return new Copy(idManager.nextId(),bookService.get(bookId),new AudioBook(Integer.parseInt(lenght)));
           default:
               throw  new IllegalArgumentException("Niepoprawny typ kopii");
       }

}


}
