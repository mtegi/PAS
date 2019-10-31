package model;

import org.springframework.stereotype.Component;

@Component //bedziemy to gdzies wstrzykiwac
public class MockProvider implements IDataProvider {

    @Override
    public void fill(DataContext data) {
        Author sapek = new Author("Andrzej","Sapkowski");
        data.books.add(new Book("Miecz Przeznaczenia", sapek));
        data.books.add(new Book("Pani Jeziora", sapek));
    }
}
