package copies.model;

import books.service.BookService;
import model.IDataProvider;
import model.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

@Repository
public class CopyRepository extends MapRepository<Copy> {
    private BookService bookService;

    @Autowired
    public CopyRepository(IDataProvider<Copy> provider, BookService bookService) {
        super(provider);
        this.bookService = bookService;
    }

    public Copy getCopy(int bookId){
        return container.values().stream().filter(copy -> copy.getBook().getId() == bookId && !copy.isBorrowed()).findAny().orElse(null);
    }

    public ArrayList<Copy> getCopiesByBookTitle(String title){
        ArrayList<Copy> ret = new ArrayList<>();
        Collection<Copy> values = container.values();
        for (Copy copy:values
        ) {
            if(Pattern.compile(Pattern.quote(title), Pattern.CASE_INSENSITIVE).matcher(copy.getTitle()).find()) ret.add(copy);
        }
        return ret;
    }
}
