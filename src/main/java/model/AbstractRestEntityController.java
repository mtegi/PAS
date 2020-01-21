package model;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public abstract class AbstractRestEntityController<T extends IEntity & IMapable> implements ICrudController<T> {

    protected IIdManager idManager;
    protected EntityService<T> entityService;
    protected AbstractCopyService copyService;

    public AbstractRestEntityController(EntityService<T> entityService, AbstractCopyService copyService, IIdManager idManager) {
        super();
        this.entityService = entityService;
        this.idManager = idManager;
        this.copyService = copyService;
    }

    @Override
    @GetMapping({"/get"})
    public List<T> Get() {
        return entityService.getAll();
    }

    @Override
    @GetMapping({"/get/{id}"})
    public T GetSingle(@PathVariable("id") int id) {
        return entityService.get(id);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public String Delete(@PathVariable("id") int id) {
        if(entityService.remove(id)) {
            copyService.replaceEntityWithNull(id, entityService.getEmptyEntity());
            return "success";
        } else return "could not delete item";
    }

}
