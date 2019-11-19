package books.model;

import model.IDataProvider;
import model.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.regex.Pattern;

@Repository //to co @component ale fajniejsza nazwa
//domyslny zasieg to jeden obiekt w aplikacji a tak chcemy wiec nie daje @Scope()
public class BookRepository implements IRepository<Book> {

    private IDataProvider provider;
    private ArrayList<Book> books;

    @Autowired
    public BookRepository(IDataProvider<Book> provider) {
        this.books = new ArrayList<>();
        this.provider = provider;
        provider.fill(books);
    }

    @Override
    public boolean add(Book book) {
        if(checkIfUniqueBook(book))
            return books.add(book);
        return false;
    }

    private boolean checkIfUniqueBook(Book book) {
        boolean unique = true;
        for(Book b:books) {
            if(book.equals(b)){
                unique = false;
                break;
            }
        }
        return unique;
    }

    @Override
    public boolean delete(Book book) {
        return books.remove(book);
    }

    @Override
    public void update(Book oldBook, Book newBook) {
        //TODO:implement
    }

    @Override
    public ArrayList<Book> getAll() {
        return books;
    }

    public ArrayList<Book> getBooksByTitle(String compareStr){
       ArrayList<Book> ret = new ArrayList<>();
        for (Book book:this.books
             ) {
            if(Pattern.compile(Pattern.quote(compareStr), Pattern.CASE_INSENSITIVE).matcher(book.getTitle()).find()) ret.add(book);
        }
        return ret;
    }

}
