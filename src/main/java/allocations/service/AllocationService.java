package allocations.service;

import allocations.model.Allocation;
import allocations.model.AllocationRepository;
import items.copies.model.Copy;
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
    public ArrayList<Allocation> getCopyAllocations(int copyId) {
        return repository.getCopyAllocations(copyId);
    }

    @Override
    public ArrayList<Allocation> getFilteredAllocations(String title) {
        return repository.getFilteredAllocations(title);
    }

    @Override
    public boolean isBorrowed(IAllocable copy, LocalDateTime start, LocalDateTime end) {
        Allocation b = this.getAll().stream().filter(borrowing -> (((borrowing.getEndTime().isAfter(start)) && (borrowing.getItem().getId() == copy.getId())) || (borrowing.getStartTime().isBefore(end) && (borrowing.getItem().getId() == copy.getId())))).findAny().orElse(null);
        return b != null;
    }

    @Override
    public boolean completeBorrowing(Allocation allocation) {
       allocation.setReturnTime(LocalDateTime.now());
       allocation.finish();
       return allocation.isFinished();
    }




}
