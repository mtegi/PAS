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
        if(bookId <0)
            throw new IllegalArgumentException("Incorrect ID argument");

        if(!bookService.containsId(bookId))
            throw new IllegalArgumentException("Incorrect ID argument");


        switch(CopyType) {
           case "PAPERBOOK":
              return new Copy(idManager.nextId(),bookService.get(bookId),new PaperBook(pages));
           case "AUDIOBOOK":
               if(time.matches("^(\\d\\d:\\d\\d:\\d\\d)"))
               return new Copy(idManager.nextId(),bookService.get(bookId),new AudioBook(time));
               else
                   throw new IllegalArgumentException("Incorrect date format");
           default:
               throw  new IllegalArgumentException("Copy type not recognized");
       }

}


}
