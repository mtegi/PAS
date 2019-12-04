package model;

public interface ICopy<T extends IEntity & IMapable> {
    public T getEntity();
}
