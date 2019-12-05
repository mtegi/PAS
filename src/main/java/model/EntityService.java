package model;

import java.util.ArrayList;

public abstract class EntityService<T extends IEntity & IMapable> extends AbstractService<T> implements IEntityService<T> {

    private EntityRepository<T> repository;
    public EntityService(EntityRepository<T> repository) {
        super(repository);
        this.repository = repository;
    }

    public ArrayList<T> getEntitiesByTitle(String filterStr) {
        return repository.getEntitiesByTitle(filterStr);
    }

    public boolean containsId(int id)
    {
        return repository.containtsId(id);
    }

    public T getEmptyEntity()
    {
        return  repository.getEmptyEntity();
    }



}
