package model;

import java.util.Collection;

public interface IRepository<T> {
    boolean add(T obj);
    boolean delete(T obj);
    void update(T old, T updated);
   Collection<T> getAll();

}
