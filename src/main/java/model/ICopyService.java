package model;

import java.util.ArrayList;

public interface ICopyService<T extends IAllocable & IMapable> {
    T findCopyByEntityId(int bookId);
    boolean add(T copy);
    T get(int copyId);
    boolean delete(int copyId);
    ArrayList<T> getCopiesByEntityTitle(String title);
    long count(IEntity entity);
}
