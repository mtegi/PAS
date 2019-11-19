import books.model.Author;
import books.model.Book;
import books.model.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class JUnitTestSuite {

        @Test
        @DisplayName("Filter books by title")
        void filterBookTest() {
            BookRepository repository = new BookRepository(new TestBookProvider());
            ArrayList<Book> ret = new ArrayList<>(repository.getBooksByTitle("miecz"));
            assertEquals(2,ret.size());
        }

    @Test
    @DisplayName("Author compare()")
    void authorCompare() {
        Author a1 = new Author("a","b");
        Author a2 = new Author("a","a");
        assertEquals(1,a1.compareTo(a2));
    }

    @Test
    @DisplayName("Book equals()")
    void bookEquals() {
        Author a1 = new Author("a","a");
        Author a2 = new Author("a","a");
        Book b1 = new Book("a",a1);
        Book b2 = new Book("a", a2);
        assertEquals(true, a1.equals(a2));
        assertEquals(false, b1.equals(a2));
        assertEquals(false, a1.equals(b1));
        assertEquals(true, b1.equals(b1));
        assertEquals(true, b1.equals(b2));
    }

    }

