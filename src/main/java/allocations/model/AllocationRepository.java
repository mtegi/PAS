package allocations.model;

import model.IDataProvider;
import model.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

@Repository
public class AllocationRepository extends MapRepository<Allocation> {

    @Autowired
    public AllocationRepository(IDataProvider<Allocation> provider) {
        super(provider);
    }

    public ArrayList<Allocation> getUserAllocations(String username) {
        ArrayList<Allocation> ret = new ArrayList<>();
        Collection<Allocation> values = container.values();
        for (Allocation allocation :values
        ) {
            if(Pattern.compile(Pattern.quote(username)).matcher(allocation.getOwner().getUsername()).find()) ret.add(allocation);
        }
        return ret;
    }

    public ArrayList<Allocation> getFilteredAllocations(String filterStr) {
        ArrayList<Allocation> ret = new ArrayList<>();
        Collection<Allocation> values = container.values();
        if (filterStr.matches("-?(0|[1-9]\\d*)")) {
            for (Allocation allocation : values
            ) {
                if (Pattern.compile(Pattern.quote(filterStr), Pattern.CASE_INSENSITIVE).matcher(allocation.getItem().getTitle()).find() || (Integer.parseInt(filterStr) == allocation.getItem().getId()))
                    ret.add(allocation);
            }
        }
        else {
            for (Allocation allocation : values
            ) {
                if (Pattern.compile(Pattern.quote(filterStr), Pattern.CASE_INSENSITIVE).matcher(allocation.getItem().getTitle()).find())
                    ret.add(allocation);
            }
        }
        return ret;
    }

    @Override
    public boolean add(Allocation allocation){
        return super.add(allocation);
    }

}
