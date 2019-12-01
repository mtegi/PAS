package borrowings.service;

import borrowings.model.Borrowing;
import copies.model.Copy;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IBorrowingService {
    ArrayList<Borrowing> getUserAllocations(String username);
    boolean isBorrowed(Copy copy, LocalDateTime start, LocalDateTime end);
    boolean completeBorrowing(Borrowing borrowing);
}
