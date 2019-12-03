package allocations.service;

import allocations.model.Allocation;
import allocations.model.AllocationRepository;
import copies.model.Copy;
import model.AbstractService;
import model.IAllocable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class AllocationService extends AbstractService<Allocation> implements IAllocationService {
   private AllocationRepository repository;
    @Autowired
    public AllocationService(AllocationRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public ArrayList<Allocation> getUserAllocations(String username) {
        return repository.getUserAllocations(username);
    }

    @Override
    public ArrayList<Allocation> getFilteredAllocations(String title) {
        return repository.getFilteredAllocations(title);
    }

    @Override
    public boolean isBorrowed(Copy copy, LocalDateTime start, LocalDateTime end) {
        Allocation b = this.getAll().stream().filter(borrowing -> (((borrowing.getEndTime().isAfter(start)) && (borrowing.getItem().getId() == copy.getId())) || (borrowing.getStartTime().isBefore(end) && (borrowing.getItem().getId() == copy.getId())))).findAny().orElse(null);
        return b != null;
    }

    @Override
    public boolean completeBorrowing(Allocation allocation) {
        IAllocable item = allocation.getItem();
        if(repository.delete(allocation.getId())){
            item.setBorrowed(false);
            return true;
        }
        return false;
    }

}
