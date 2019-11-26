package books.model;

import model.IMapable;

import javax.validation.constraints.NotNull;

public class Book implements IMapable {

    @NotNull
    private String title;
    @NotNull
    private Author author;
    @NotNull
    private int Id;
    public Book(){}

    public Book(int id, String title, Author author){
        this.Id = id;
        this.author = author;
        this.title = title;
    }

    public int getId() {
        return Id;
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
