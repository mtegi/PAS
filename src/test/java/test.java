import books.model.Author;
import books.model.Book;
import books.model.BookRepository;
import books.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class JUnitTestCase {

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
    @DisplayName("deleteBook")
    void deleteBook() {
        BookRepository repository = new BookRepository(new TestBookProvider());
        BookService service = new BookService(repository);
        assertEquals(5, service.getAll().size());
        Book b = new Book(5,"Gra o tron", new Author("Goerge R. R.","Martin"));
        assertEquals("Gra o tron", repository.get(5).getTitle());
        assertTrue(b.equals(repository.get(5)));
        assertTrue(repository.delete(b.getId()));
        assertTrue(repository.add(b));
        assertTrue(service.remove(b.getId()));
    }

    @Test
    @DisplayName("Book equals()")
    void bookEquals() {
        Author a1 = new Author("a","a");
        Author a2 = new Author("a","a");
        Book b1 = new Book(10,"a",a1);
        Book b2 = new Book(11,"a", a2);
        assertTrue(a1.equals(a2));
        assertFalse(b1.equals(a2));
        assertFalse(a1.equals(b1));
        assertTrue(b1.equals(b1));
        assertTrue(b1.equals(b2));
    }

    @Test
    @DisplayName("Repo test")
    void repo() {
        TestMapRepository repo = new TestMapRepository(data -> {});
        assertEquals(0, repo.getAll().size());
        repo.add(new TestMappable(1));
        assertEquals(1, repo.getAll().get(0).getId());
        assertEquals(1, repo.get(1).getId());
        ArrayList<TestMappable> arrayList = repo.getAll();
        assertEquals(arrayList.size(), repo.getAll().size());
        assertEquals(arrayList.get(0).getId(), repo.get(1).getId());

    }


    }

