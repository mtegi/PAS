package model;

import java.util.ArrayList;

public interface IService<T extends IMapable> {


    boolean add(T obj);
    boolean remove(int id);
    ArrayList<T> getAll();
    T get(int id);

}
