package books.model;

import model.IAllocable;

import javax.validation.constraints.NotNull;

public class Book implements IAllocable {
    @NotNull
    private String title;
    @NotNull
    private Author author;

    public Book(){}

    public Book(String title, Author author){
        this.author = author;
        this.title = title;
    }

    public Book(String title, String firstName, String lastName){
        this.author = new Author(firstName,lastName);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Book)) return super.equals(other);
        else{
            if(this.author.equals(((Book) other).author) && this.title.contentEquals(((Book) other).title)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author=" + author +
                '}';
    }
}
