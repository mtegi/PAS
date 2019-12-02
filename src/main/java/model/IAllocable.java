package model;

public interface IAllocable {
    boolean isBorrowed();
    void setBorrowed(boolean borrowed);
    String getTitle();
    int getId();
}
