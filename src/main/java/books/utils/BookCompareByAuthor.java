package books.utils;

import books.model.Book;

import java.util.Comparator;

public class BookCompareByAuthor implements Comparator<Book> {

    @Override
    public int compare(Book book1, Book book2) {
        if(book1.getAuthor() != book2.getAuthor()){
            return book1.getAuthor().compareTo(book2.getAuthor()) ;
        }
        return book1.getTitle().compareToIgnoreCase(book2.getTitle());
    }
}
