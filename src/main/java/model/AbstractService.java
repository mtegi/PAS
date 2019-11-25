package model;

import java.util.ArrayList;

public abstract class AbstractService<T extends IMapable> implements IService<T> {

    private MapRepository repository;

    public AbstractService(MapRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public boolean add(T obj)  {
        return repository.add(obj);
    }

    @Override
    public boolean remove(int id) {
        return repository.delete(id);
    }

    @Override
    public ArrayList<T> getAll() {
        return repository.getAll();
    }

    @Override
    public T get(int id) {
        return (T) repository.get(id);
    }
}
