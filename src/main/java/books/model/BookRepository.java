package books.model;

import model.IDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.regex.Pattern;

@Repository //to co @component ale fajniejsza nazwa
//domyslny zasieg to jeden obiekt w aplikacji a tak chcemy wiec nie daje @Scope()
public class BookRepository {

    private IDataProvider provider;
    private Hashtable<Integer, Book> books;

    @Autowired
    public BookRepository(IDataProvider<Book> provider) {
        this.books = new Hashtable<>();
        ArrayList<Book> tmp = new ArrayList<>();
        this.provider = provider;
        provider.fill(tmp);
        for (Book book:tmp
             ) {
            books.put(book.getId(), book);
        }
    }

    public Book get(int bookId){
        return books.get(bookId);
    }

   // @Override
    public boolean add(Book book) {
        boolean success = true;
        if(checkIfUniqueBook(book)) {
            books.put(book.getId(), book);
        } else success = false;
        return success;
    }

   // @Override
    public boolean delete(int id) {
        books.remove(id);
        return !books.containsKey(id);
    }

    //@Override
    public void update(Book oldBook, Book newBook) {
        //TODO:implement
    }

    //@Override
    public ArrayList<Book> getAll() {
        Collection<Book> values = books.values();
        return new ArrayList<>(values);
    }

    private boolean checkIfUniqueBook(Book book) {
       if(books.values().stream().filter((Book b) -> b.equals(book)).findFirst().orElse(null) != null)
           return false;
        return true;
    }

    public ArrayList<Book> getBooksByTitle(String compareStr){
       ArrayList<Book> ret = new ArrayList<>();
        Collection<Book> values = books.values();
        for (Book book:values
             ) {
            if(Pattern.compile(Pattern.quote(compareStr), Pattern.CASE_INSENSITIVE).matcher(book.getTitle()).find()) ret.add(book);
        }
        return ret;
    }

}
