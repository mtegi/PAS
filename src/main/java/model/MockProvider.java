package model;

import org.springframework.stereotype.Component;

@Component //bedziemy to gdzies wstrzykiwac
public class MockProvider implements IDataProvider {

    @Override
    public void fill(DataContext data) {
        Author sapek = new Author("Andrzej","Sapkowski");
        Author dzordz = new Author("Goerge R. R.","Martin");
        data.books.add(new Book("Miecz Przeznaczenia", sapek));
        data.books.add(new Book("Pani Jeziora", sapek));
        data.books.add(new Book("Starcie królów",dzordz));
        data.books.add(new Book("Nawałnica Mieczy",dzordz));
        data.books.add(new Book("Gra o tron",dzordz));
    }
}
