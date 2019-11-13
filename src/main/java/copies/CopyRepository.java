package copies;

import model.IDataProvider;
import model.IRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class CopyRepository implements IRepository<Copy> {

    private IDataProvider provider;
    private ArrayList<Copy> copies;

    @Autowired
    public CopyRepository(IDataProvider<Copy> provider) {
        this.provider = provider;
        this.copies = new ArrayList<>();
        provider.fill(copies);
    }

    @Override
    public boolean add(Copy obj) {
        return copies.add(obj);
    }

    @Override
    public boolean delete(Copy obj) {
        if(obj.isBorrowed()) return false;
        return copies.remove(obj);
    }

    @Override
    public void update(Copy old, Copy updated) {
        //TODO: implement update
    }

    @Override
    public ArrayList<Copy> getAll() {
        return copies;
    }

    public Copy get(int copyID){
        return copies.stream().filter(copy -> copyID == copy.getCopyID()).findAny().orElse(null);
    }
}
