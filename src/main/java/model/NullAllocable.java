package model;

import org.springframework.stereotype.Component;

@Component
public class NullAllocable implements  IAllocable {

    private static  NullAllocable instance = null;
    private  String title;
    private int id;

    public NullAllocable() {
        title = "No Title";
        id = -1;
    }
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getId() {
        return id;
    }


}
