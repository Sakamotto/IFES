package tadchaininghash;

/**
 * Created by cristian on 22/03/17.
 */

public class ItemTabHash{

    private String key;
    private Dado dado;

    public ItemTabHash(String key, Dado dado){
        this.setKey(key);
        this.setDado(dado);
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