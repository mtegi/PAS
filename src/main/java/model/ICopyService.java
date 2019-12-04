package model;

import java.util.ArrayList;

public interface ICopyService<T extends IAllocable & IMapable> {
    T getCopy(int bookId);
    ArrayList<T> getCopiesByEntityTitle(String title);
}
