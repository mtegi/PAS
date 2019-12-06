package items.copies.model;

import books.model.Book;
import model.IAllocable;
import model.ICopy;
import model.IMapable;

import javax.validation.constraints.NotNull;

public class Copy implements IAllocable, IMapable, ICopy<Book> {
    @NotNull
    private int copyID;
    @NotNull
    private Book book;

    private BookType bookType;

    public Copy(int copyID, Book book, BookType bookType) {
        this.copyID = copyID;
        this.book = book;
        this.setBookType(bookType);
    }

    @Override
    public Book getEntity() {
        return book;
    }

    @Override
    public String getTitle() {
        return this.book.getTitle();
    }

    @Override
    public int getId() {
        return copyID;
    }

    public BookType getBookType() {
        return bookType;
    }


    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    @Override
    public void setEntity(Book book){
        this.book=book;
    }


}
