package respostas.hash;

/**
 * Created by cristian on 23/04/17.
 */
public class Item {

    private Object key;
    private Object elemento;
    private int cacheHashCode;

    public Item(Object key, Object elemento){
        this.setKey(key);
        this.setElem(elemento);
    }

    public void Item(Object key, Object elemento, int hashCode){
        this.setKey(key);
        this.setElem(elemento);
        this.setCacheHashCode(hashCode);
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElem(Object elemento) {
        this.elemento = elemento;
    }

    public int getCacheHashCode() {
        return cacheHashCode;
    }

    public void setCacheHashCode(int cacheHashCode) {
        this.cacheHashCode = cacheHashCode;
    }
}
