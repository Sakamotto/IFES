package tadhash;

/**
 * Created by cristian on 22/03/17.
 */

public class ItemTabHash{

    private Object key;
    private Object dado;
    private int hash;

    public ItemTabHash(Object key, Object dado){
        this.setKey(key);
        this.setDado(dado);
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getDado() {
        return dado;
    }

    public void setDado(Object dado) {
        this.dado = dado;
    }
}