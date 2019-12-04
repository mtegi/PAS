package allocations.service;

import allocations.model.Allocation;
import items.copies.model.Copy;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IAllocationService {
    ArrayList<Allocation> getUserAllocations(String username);
    ArrayList<Allocation> getFilteredAllocations(String title);
    boolean isBorrowed(Copy copy, LocalDateTime start, LocalDateTime end);
    boolean completeBorrowing(Allocation allocation);

}
