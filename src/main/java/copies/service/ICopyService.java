package copies.service;

import copies.model.Copy;

import java.util.ArrayList;

public interface ICopyService {
    Copy getCopy(int bookId);
    ArrayList<Copy> getCopiesByBookTitle(String title);
}
