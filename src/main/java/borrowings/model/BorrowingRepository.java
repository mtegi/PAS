package borrowings.model;

import model.IDataProvider;
import model.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BorrowingRepository extends MapRepository<Borrowing> {

    @Autowired
    public BorrowingRepository(IDataProvider<Borrowing> provider) {
        super(provider);
    }

}
