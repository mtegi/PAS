package borrowings.model;

import login.model.UserModel;
import model.IAllocable;
import model.IMapable;

import java.time.LocalDateTime;

public class Borrowing implements IMapable {
    private int id;
    private IAllocable item;
    private UserModel owner;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Borrowing(int id, IAllocable item, UserModel owner, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.item = item;
        this.owner = owner;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public IAllocable getItem() {
        return item;
    }

    public UserModel getOwner() {
        return owner;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public int getId() {
        return id;
    }

}
