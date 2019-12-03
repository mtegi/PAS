package copies.service;

import copies.model.Copy;

import java.util.ArrayList;

public interface ICopyService {
    Copy getCopy(int bookId);
    Copy getCopy(int bookId, String type);
    ArrayList<Copy> getCopiesByBookTitle(String title);
}
