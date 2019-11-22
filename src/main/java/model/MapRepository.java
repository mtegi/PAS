package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

public abstract class MapRepository<T extends IMapable> {

protected IDataProvider provider;
protected Hashtable<Integer, T> container;

    public MapRepository(IDataProvider<T> provider) {
        this.container = new Hashtable<>();
        ArrayList<T> tmp = new ArrayList<>();
        this.provider = provider;
        provider.fill(tmp);
        for (T obj:tmp
        ) {
            container.put(obj.getId(), obj);
        }
    }

public T get(int id){
        return container.get(id);
        }

public boolean add(T obj) {
        boolean success = true;
        if(checkIfUniqueBook(obj)) {
        container.put(obj.getId(), obj);
        } else success = false;
        return success;
        }

public boolean delete(int id) {
        container.remove(id);
        return !container.containsKey(id);
        }

public void update(T oldObj, T newObj) {

        }

public ArrayList<T> getAll() {
        Collection<T> values = container.values();
        return new ArrayList<>(values);
        }

private boolean checkIfUniqueBook(IMapable obj) {
        if(container.values().stream().filter((IMapable b) -> b.equals(obj)).findFirst().orElse(null) != null)
            return false;
        return true;
        }

}
