package model;

public interface ICopy<T extends IEntity & IMapable> {
    T getEntity();
    void setEntity(T entity);
}
