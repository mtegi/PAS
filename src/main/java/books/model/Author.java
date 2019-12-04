package books.model;

import javax.validation.constraints.NotNull;

public class Author implements Comparable<Author> {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }


    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Author)) return super.equals(other);
        else{
            if(this.firstName.contentEquals(((Author) other).firstName) && this.lastName.contentEquals(((Author) other).lastName)) return true;
        }
        return false;
    }

    @Override
    public int compareTo(Author author) {
        if (lastName != author.getLastName()) {
            return lastName.compareToIgnoreCase(author.getLastName());
        }
        return firstName.compareToIgnoreCase(author.getFirstName());
    }
}
