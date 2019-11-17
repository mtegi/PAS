import books.model.Author;
import books.model.Book;
import model.IDataProvider;

import java.util.Collection;

public class TestBookProvider implements IDataProvider<Book> {

        @Override
        public void fill(Collection<Book> books) {
            Author sapek = new Author("Andrzej","Sapkowski");
            Author dzordz = new Author("Goerge R. R.","Martin");
            books.add(new Book("Miecz Przeznaczenia", sapek));
            books.add(new Book("Pani Jeziora", sapek));
            books.add(new Book("Starcie królów",dzordz));
            books.add(new Book("Nawałnica Mieczy",dzordz));
            books.add(new Book("Gra o tron",dzordz));
        }
    }
