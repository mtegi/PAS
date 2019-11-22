package books.model;

import model.IDataProvider;
import model.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.regex.Pattern;

@Repository //to co @component ale fajniejsza nazwa
//domyslny zasieg to jeden obiekt w aplikacji a tak chcemy wiec nie daje @Scope()
public class BookRepository extends MapRepository<Book> {

    @Autowired
    public BookRepository(IDataProvider<Book> provider) {
    super(provider);
    }

    public ArrayList<Book> getBooksByTitle(String compareStr){
       ArrayList<Book> ret = new ArrayList<>();
        Collection<Book> values = container.values();
        for (Book book:values
             ) {
            if(Pattern.compile(Pattern.quote(compareStr), Pattern.CASE_INSENSITIVE).matcher(book.getTitle()).find()) ret.add(book);
        }
        return ret;
    }

}
