package borrowings.model;

import model.IDataProvider;
import model.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

@Repository
public class BorrowingRepository extends MapRepository<Borrowing> {

    @Autowired
    public BorrowingRepository(IDataProvider<Borrowing> provider) {
        super(provider);
    }

    public ArrayList<Borrowing> getUserAllocations(String username) {
        ArrayList<Borrowing> ret = new ArrayList<>();
        Collection<Borrowing> values = container.values();
        for (Borrowing borrowing:values
        ) {
            if(Pattern.compile(Pattern.quote(username)).matcher(borrowing.getOwner().getUsername()).find()) ret.add(borrowing);
        }
        return ret;
    }

    @Override
    public boolean add(Borrowing borrowing){
        borrowing.getItem().setBorrowed(true);
        return super.add(borrowing);
    }

}
