package borrowings.service;

import borrowings.model.Borrowing;
import model.AbstractService;
import model.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowingService extends AbstractService<Borrowing> implements IBorrowingService {

    @Autowired
    public BorrowingService(MapRepository<Borrowing> repository) {
        super(repository);
    }
}
