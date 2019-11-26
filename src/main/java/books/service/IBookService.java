package books.service;

import books.model.Book;

import java.util.ArrayList;

public interface IBookService {
    ArrayList<Book> getBooksByTitle(String filterStr);
}
