package borrowings.service;

import borrowings.model.Borrowing;
import borrowings.model.BorrowingRepository;
import copies.model.Copy;
import model.AbstractService;
import model.IAllocable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class BorrowingService extends AbstractService<Borrowing> implements IBorrowingService {
   private BorrowingRepository repository;
    @Autowired
    public BorrowingService(BorrowingRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public ArrayList<Borrowing> getUserAllocations(String username) {
        return repository.getUserAllocations(username);
    }

    @Override
    public boolean isBorrowed(Copy copy, LocalDateTime start, LocalDateTime end) {
        Borrowing b = this.getAll().stream().filter(borrowing -> (((borrowing.getEndTime().isAfter(start)) && (borrowing.getItem().getId() == copy.getId())) || (borrowing.getStartTime().isBefore(end) && (borrowing.getItem().getId() == copy.getId())))).findAny().orElse(null);
        return b != null;
    }

    @Override
    public boolean completeBorrowing(Borrowing borrowing) {
        IAllocable item = borrowing.getItem();
        if(repository.delete(borrowing.getId())){
            item.setBorrowed(false);
            return true;
        }
        return false;
    }

}
