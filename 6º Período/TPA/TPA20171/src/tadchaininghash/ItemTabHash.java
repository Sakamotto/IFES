package tadchaininghash;

/**
 * Created by cristian on 22/03/17.
 */

public class ItemTabHash{

    private String key;
    private Dado dado;
    private int hash;

    public ItemTabHash(String key, Dado dado){
        this.setKey(key);
        this.setDado(dado);
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Dado getDado() {
        return dado;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }
}