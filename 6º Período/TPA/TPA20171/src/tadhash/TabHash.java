package tadhash;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by cristian on 22/03/17.
 */
public class TabHash {

    ArrayList<LinkedList<ItemTabHash>> content;
    private final int base = 16;

    public TabHash(){
        content = new ArrayList<>();
    }

    /**
     *
     * @param key
     * @return retorna o index na LinkedList, onde ele achou a KEY, ou -1 se não econtrar.
     */

    private int searchKey(Object key){
        String keyS = (String) key;
        int pos = hashFunction(keyS);
        int index = 0;

        for (ItemTabHash item: content.get(pos)) {
            index++;
            if(keyS.equals(item.getKey())){
                return index;
            }
        }
        return -1;
    }

    public void insert(Object key, Object value){
        String keyS = (String) key;

        int pos = hashFunction(keyS);
        LinkedList<ItemTabHash> toAdd = new LinkedList<>();

        if(content.get(pos) == null){
            toAdd.add(new ItemTabHash((String)key, (Dado)value));
            content.add(pos, toAdd);
        }else{
            // Se já existe a key passada como parâmetro, então o valor é sobrescrito
            int i = searchKey(keyS);
            if(i != -1){
                content.get(pos).get(i).setDado((Dado) value);
            }else{
                content.get(pos).add(new ItemTabHash(keyS, (Dado)value));
            }
        }

    }

    /**
     *
     * @param key
     * @return retorna o índice no vetor da chave KEY passada como parâmetro
     */
    public int hashFunction(String key){
        double f = 0;

        for(int i = 0; i < key.length(); i++){
            f = key.codePointAt(i) * Math.pow(base, i);
        }
        return (int)(f % content.size());
    }


}
