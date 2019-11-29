package borrowings.service;

import borrowings.model.Borrowing;
import borrowings.model.BorrowingRepository;
import model.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
