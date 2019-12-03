package allocations.utils;

import model.IIdManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("AllocationIdManager")
@Component
public class AllocationIdManager implements IIdManager {
    private int id;

    public AllocationIdManager() {
        this.id = 0;
    }

    @Override
    public int nextId() {
        id++;
        return id;
    }
}
