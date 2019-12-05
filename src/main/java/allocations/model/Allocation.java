package allocations.model;

import login.model.UserModel;
import model.IAllocable;
import model.IMapable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Allocation implements IMapable {
    private int id;
    private IAllocable item;
    private UserModel owner;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime returnTime;
    boolean finished;

    public Allocation(int id, IAllocable item, UserModel owner, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.item = item;
        this.owner = owner;
        this.startTime = startTime;
        this.endTime = endTime;
        this.finished = false;
        this.returnTime = null;
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

    public void setEndTime(LocalDateTime date)
    {
        this.endTime = date;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime date)
    {
        this.returnTime = date;
    }


    public boolean isFinished() {
        return finished;
    }

    public void finish ()
    {
        finished = true;
    }

    public String getStartDateString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return startTime.format(formatter);
    }

    public String getEndDateString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return endTime.format(formatter);
    }
    public String getReturnDateString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return returnTime.format(formatter);
    }

    @Override
    public int getId() {
        return id;
    }

}
