package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class AbstractCopyRepository<T extends IMapable & IAllocable & ICopy> extends MapRepository<T> {

    public AbstractCopyRepository(IDataProvider<T> provider) {
        super(provider);
    }

    public T findCopyByEntityId(int entityId){
        return container.values().stream().filter(copy -> copy.getEntity().getId() == entityId).findAny().orElse(null);
    }


    public ArrayList<T> getCopiesByEntityTitle(String title){
        ArrayList<T> ret = new ArrayList<>();
        Collection<T> values = container.values();
        for (T copy :values
        ) {
            if(Pattern.compile(Pattern.quote(title), Pattern.CASE_INSENSITIVE).matcher(copy.getTitle()).find()) ret.add(copy);
        }
        return ret;
    }

    public ArrayList<T> getCopiesByEntityId(int Id){
       return new ArrayList<>(container.values().stream().filter(copy -> copy.getEntity().getId() == Id).collect(Collectors.toList()));
    }
}
