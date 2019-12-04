package model;

import java.util.ArrayList;

public interface IEntityService<T extends IEntity> {
    ArrayList<T> getEntitiesByTitle(String filterStr);
}
