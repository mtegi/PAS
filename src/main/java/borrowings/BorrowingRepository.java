package borrowings;

import model.IDataProvider;
import model.IRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class BorrowingRepository implements IRepository<Borrowing> {

    private IDataProvider provider;
    private ArrayList<Borrowing> borrowings;

    @Autowired
    public BorrowingRepository(IDataProvider<Borrowing> provider) {
        this.provider = provider;
        this.borrowings = new ArrayList<>();
        provider.fill(borrowings);
    }


    @Override
    public boolean add(Borrowing obj) {
        return borrowings.add(obj);
    }

    @Override
    public boolean delete(Borrowing obj) {
        return borrowings.remove(obj);
    }

    @Override
    public void update(Borrowing old, Borrowing updated) {
        //TODO: implement update
    }

    @Override
    public ArrayList<Borrowing> getAll() {
        return borrowings;
    }
}
