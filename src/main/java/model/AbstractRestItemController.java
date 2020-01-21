package model;

import allocations.service.AllocationService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public abstract class AbstractRestItemController<T extends IMapable & IAllocable & ICopy> implements ICrudController<T>{
    protected AbstractCopyService<T> copyService;
    protected EntityService entityService;
    protected AllocationService allocationService;

    public AbstractRestItemController(AbstractCopyService<T> copyService, EntityService entityService, AllocationService allocationService) {
        this.copyService = copyService;
        this.entityService = entityService;
        this.allocationService = allocationService;
    }

    @GetMapping({"/get"})
    public List<T> Get() {
        return copyService.getAll();
    }

    @GetMapping({"/get/{id}"})
    public T GetSingle(@PathVariable("id") int id) {
        return copyService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public String Delete(@PathVariable("id") int id) {
        if (copyService.delete(id)) {
            allocationService.replaceWithNull(id);
            return "success";
        } else return "could not delete item";
    }
}
