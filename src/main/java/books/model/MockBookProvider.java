package books.model;

import books.utils.BookIdManager;
import model.IDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component //bedziemy to gdzies wstrzykiwac
public class MockBookProvider implements IDataProvider<Book> {

    @Autowired
    public void setIdManager(BookIdManager idManager) {
        this.idManager = idManager;
    }

    private BookIdManager idManager;

    @Override
    public void fill(Collection<Book> books) {
        Author sapek1 = new Author("Andrzej","Sapkowski");
        Author sapek2 = new Author("Andrzej","Sapkowski");
        Author dzordz1 = new Author("Goerge R. R.","Martin");
        Author dzordz2 = new Author("Goerge R. R.","Martin");
        Author dzordz3 = new Author("Goerge R. R.","Martin");

        books.add(new Book(idManager.nextId(),"Miecz Przeznaczenia", sapek1));
        books.add(new Book(idManager.nextId(),"Pani Jeziora", sapek2));
        books.add(new Book(idManager.nextId(),"Starcie królów",dzordz1));
        books.add(new Book(idManager.nextId(),"Nawałnica Mieczy",dzordz2));
        books.add(new Book(idManager.nextId(),"Gra o tron",dzordz3));
    }
}
