package respostas.hash;

import java.util.LinkedList;

/**
 * Created by cristian on 23/04/17.
 */
public abstract class TADTabH {

    private int tamanho;

    TADTabH(){
    }

    TADTabH(HashEngine hE){
    }

    TADTabH(int tam, HashEngine hE){
    }

    public abstract boolean insertItem(Object key, Object elem);

    public abstract Object removeElem(Object key);

    public abstract Object findElem(Object key);

    public abstract int size();

    public abstract boolean empty();

    public abstract LinkedList<Object> keys();

    public abstract LinkedList<Object> elements();


}
