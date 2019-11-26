package model;

public interface IAllocable {
    boolean isBorrowed();
    void setBorrowed(boolean borrowed);
    String getType();
    String getTitle();
    int getId();
}
