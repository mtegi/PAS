package model;

import java.util.ArrayList;

public class AbstractCopyService<T extends IMapable & IAllocable & ICopy> implements ICopyService<T> {
    private AbstractCopyRepository<T> repository;

    public AbstractCopyService(AbstractCopyRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public T getCopy(int entityId) {
        T ret = repository.getCopy(entityId);
        return ret;
    }

    public ArrayList<T> getAll(){return repository.getAll();}


    @Override
    public ArrayList<T> getCopiesByEntityTitle(String title) {
        return repository.getCopiesByEntityTitle(title);
    }

    public long count(IEntity entity){
        return repository.getAll().stream().filter(copy -> copy.getEntity().getId() == entity.getId()).count();
    }
}
