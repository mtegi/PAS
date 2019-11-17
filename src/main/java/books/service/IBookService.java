package books.service;

import books.model.Book;

import java.util.ArrayList;
import java.util.Collection;

public interface IBookService {
    boolean add(Book book);
    Collection<Book> getAll();

    ArrayList<Book> getBooksByTitle(String filterStr);
}
