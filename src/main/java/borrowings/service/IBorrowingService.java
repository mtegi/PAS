package borrowings.service;

import borrowings.model.Borrowing;

import java.util.ArrayList;

public interface IBorrowingService {
    ArrayList<Borrowing> getUserAllocations(String username);
}
