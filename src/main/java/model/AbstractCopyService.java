package model;

import java.util.ArrayList;

public abstract class AbstractCopyService<T extends IMapable & IAllocable & ICopy> implements ICopyService<T> {
    protected AbstractCopyRepository<T> repository;

    public AbstractCopyService(AbstractCopyRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public T findCopyByEntityId(int entityId) {
        T ret = repository.findCopyByEntityId(entityId);
        return ret;
    }

    @Override
    public boolean add(T copy) {
        return repository.add(copy);
    }

    @Override
    public T get(int copyId) {
        return repository.get(copyId);
    }

    @Override
    public boolean delete(int copyId) {
        return repository.delete(copyId);
    }

    public ArrayList<T> getAll(){return repository.getAll();}

    @Override
    public ArrayList<T> getCopiesByEntityTitle(String title) {
        return repository.getCopiesByEntityTitle(title);
    }

    @Override
    public long count(IEntity entity){
        return repository.getAll().stream().filter(copy -> copy.getEntity().getId() == entity.getId()).count();
    }

    @Override
    public void replaceEntityWithNull (int entityId, IEntity nullEntity)
    {
        repository.getCopiesByEntityId(entityId).forEach(copy -> copy.setEntity(nullEntity));
    }
}
