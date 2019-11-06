import books.Author;
import books.Book;
import books.BookRepository;
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

    }

