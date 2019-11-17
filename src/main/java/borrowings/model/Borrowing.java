package borrowings.model;

import login.model.UserModel;
import model.IAllocable;

import java.time.LocalDateTime;

public class Borrowing {
    private IAllocable item;
    private UserModel owner;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Borrowing(IAllocable item, UserModel owner, LocalDateTime startTime, LocalDateTime endTime) {
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
}
