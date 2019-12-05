package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

public class EntityRepository<T extends IEntity & IMapable> extends MapRepository<T> {
    public EntityRepository(IDataProvider<T> provider) {
        super(provider);
    }

    public ArrayList<T> getEntitiesByTitle(String filterString){
        ArrayList<T> ret = new ArrayList<>();
        Collection<T> values = container.values();
        for (T item:values
        ) {
            if(Pattern.compile(Pattern.quote(filterString), Pattern.CASE_INSENSITIVE).matcher(item.getTitle()).find()) ret.add(item);
        }
        return ret;
    }


}
