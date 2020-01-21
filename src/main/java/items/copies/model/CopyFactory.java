package items.copies.model;


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
        ValidateBookId(bookId);
        switch(CopyType) {
           case BookType.PaperBook:
               ValidatePaper(pages);
              return new Copy(idManager.nextId(),bookService.get(bookId),new PaperBook(pages));
           case BookType.AudioBook:
               ValidateAudio(time);
               return new Copy(idManager.nextId(),bookService.get(bookId),new AudioBook(time));
           default:
               throw  new IllegalArgumentException("Copy type not recognized");
       }

}

    public Copy createCopyPaper (int bookId, int pages)
    {
        ValidateBookId(bookId);
        ValidatePaper(pages);
        return new Copy(idManager.nextId(), bookService.get(bookId), new PaperBook(pages));
    }

    public Copy createCopyAudio (int bookId, String time)
    {
        ValidateBookId(bookId);
        ValidateAudio(time);
        return new Copy(idManager.nextId(), bookService.get(bookId), new AudioBook(time));
    }

    private void ValidatePaper(int pages){
        if(pages<0) throw new IllegalArgumentException("Incorrect pages number");
    }

    private void ValidateAudio(String time){
        if(!time.matches("^(\\d\\d:\\d\\d:\\d\\d)")) throw new IllegalArgumentException("Incorrect date format");
    }

    private void ValidateBookId(int bookId){
        if(bookId <0)
            throw new IllegalArgumentException("Incorrect ID argument");

        if(!bookService.containsId(bookId))
            throw new IllegalArgumentException("Incorrect ID argument");
    }

}
