package tadhash;

import java.util.List;

/**
 * Created by cristian on 05/04/17.
 */
public interface Dictionary {
    void add(Object key, Object value);
    Object remove(Object key);
    Object getElement(Object key);
    List<Object> getKeys();
    List<Object> getElements();
    int size();
    boolean isEmpty();

}
