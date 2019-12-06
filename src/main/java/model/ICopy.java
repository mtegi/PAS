package model;

public interface ICopy<T extends IEntity & IMapable> {
    public T getEntity();
    public void setEntity(T entity);
}
