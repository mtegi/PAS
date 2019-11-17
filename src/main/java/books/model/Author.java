package books.model;

import org.springframework.web.bind.annotation.ModelAttribute;

public class Author implements Comparable<Author> {
    private String firstName;
    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @ModelAttribute("firstname")
    public String getFirstName() {
        return firstName;
    }
    @ModelAttribute("lastname")
    public String getLastName() {
        return lastName;
    }


    @Override
    public int compareTo(Author author) {
        if (lastName != author.getLastName()) {
            return lastName.compareToIgnoreCase(author.getLastName());
        }
        return firstName.compareToIgnoreCase(author.getFirstName());
    }
}
