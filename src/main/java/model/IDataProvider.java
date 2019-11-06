package model;


import java.util.Collection;

public interface IDataProvider<T> {
    void fill(Collection<T> data);
}
