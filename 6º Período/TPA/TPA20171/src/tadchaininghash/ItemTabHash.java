package tadchaininghash;

/**
 * Created by cristian on 22/03/17.
 */

// Seria bom utilizar Generics para chave e valor ...
public class ItemTabHash<K, V>{

    private K key;
    private V dado;

    public ItemTabHash(K key, V dado){
        this.setKey(key);
        this.setDado(dado);
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getDado() {
        return dado;
    }

    public void setDado(V dado) {
        this.dado = dado;
    }
}