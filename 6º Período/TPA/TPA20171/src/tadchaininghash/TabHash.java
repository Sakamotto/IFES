package tadchaininghash;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cristian on 22/03/17.
 */

public class TabHash {

    private LinkedList<ItemTabHash>[] content;
    private final int base = 16;

    public TabHash(int size){
        content = new LinkedList[size];
    }

    /**
     *
     * @param key
     * @return retorna o index na LinkedList onde ele achou a KEY, ou -1 se não econtrar.
     */

    private int searchKey(Object key){
        String keyS = (String) key;
        int pos = polynomialAcc(keyS);
        int index = -1;

        for (ItemTabHash item: content[pos]) {
            index++;
            if(keyS.equals(item.getKey())){
                return index;
            }
        }

        return -1;
    }

    /**
     * Este método adiciona um objeto value em uma determinada chave. Esse Objeto value é um objeto do tipo Dado.
     * @param key
     * @param value
     *
     */

    public void add(Object key, Object value){
        String keyS = (String) key;

        int pos = polynomialAcc(keyS);
        LinkedList<ItemTabHash> toAdd = new LinkedList<>();

        if(content[pos] == null){
            toAdd.add(new ItemTabHash((String)key, (Dado)value));
            content[pos] = toAdd;
        }else{
            // Se já existe o key passada como parâmetro, então o valor é sobrescrito
            int i = searchKey(key);
            if(i != -1){
                content[pos].get(i).setDado((Dado) value);
            }else{
                content[pos].add(new ItemTabHash(keyS, (Dado)value));
            }
        }
    }

    public Object remove(Object key){
        String keyS = (String)key;
        int index = polynomialAcc(keyS);
        int indexLinkedList = searchKey(key);
        Object removed = null;

        if(indexLinkedList != -1){
            removed = content[index].get(indexLinkedList);
            content[index].remove(indexLinkedList);
        }
        return removed;
    }

    /**
     *
     * @param key
     * @return retorna o índice no vetor da chave KEY passada como parâmetro
     */
    private int polynomialAcc(String key){
        double f = 0;

        for(int i = 0; i < key.length(); i++){
            f = key.codePointAt(i) * Math.pow(base, i);
        }
        return (int)(f % content.length);
    }

    private int asciiSum(String key){
        double f = 0;

        for(int i = 0; i < key.length(); i++){
            f += key.codePointAt(i);
        }

        return (int)(f % content.length);
    }

    /**
     *
     * @param key
     * @return Este método retorna um objeto do tipo Dado a partir de uma determinada KEY procurada. Caso não encontre, retorna null.
     *
     */
    public Object getElement(Object key){
        String keyS = (String) key;
        int index = polynomialAcc(keyS);
        int indexLinkedList = searchKey(key);

        if(indexLinkedList != -1){
            return content[index].get(indexLinkedList).getDado();
        }
        return null;
    }

    /**
     *
     * @param key
     * @return retorna uma linked list com seus ItemTabHash's com base na KEY fornecida.
     */
    public LinkedList<ItemTabHash> get(Object key){
        String keyS = (String) key;
        int index = polynomialAcc(keyS);

        return content[index];
    }

    /**
     *
     * @return retorna um List<Object> com todas as chaves</>
     */
    public List<Object> getKeys(){
        List<Object> keyList = new ArrayList<>();

        for(int i = 0; i < content.length; i++){
            for(int j = 0; j < content[i].size(); j++){
                keyList.add(content[i].get(j).getKey());
            }
        }
        return keyList;
    }

    /**
     *
     * @return retorna um List<Object></> com todos os valores
     */
    public List<Object> getElements(){
        List<Object> valueList = new ArrayList<>();

        for(int i = 0; i < content.length; i++){
            for(int j = 0; j < content[i].size(); j++){
                valueList.add(content[i].get(j).getDado());
            }
        }
        return valueList;
    }

}
