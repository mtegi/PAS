package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Repository //to co @component ale fajniejsza nazwa
//domyslny zasieg to jeden obiekt w aplikacji a tak chcemy wiec nie daje @Scope()
public class DataRepository  {

    private IDataProvider provider;
    private DataContext data;

    @Autowired //znajdz automatycznie providera do wstrzykniecia
    public DataRepository(IDataProvider provider) {
        this.data = new DataContext();
        this.provider = provider;
        provider.fill(data);
    }

    public void addBook(Book book) {
        this.getBooks().add(book);
    }

    @ModelAttribute("books")
    public List<Book> getBooks() {
        return data.books;
    }

}
