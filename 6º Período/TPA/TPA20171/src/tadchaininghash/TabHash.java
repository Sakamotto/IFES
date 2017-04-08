package tadchaininghash;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cristian on 22/03/17.
 */

class MyHashEngine extends HashEngine{

    @Override
    public int hashFunction(Object key) {
        String keyS = (String) key;
        double f = 0;
        int base = 33;

        for(int i = 0; i < keyS.length(); i++){
            f = keyS.codePointAt(i) * Math.pow(base, i);
        }
        return (int)f;
    }
}

public class TabHash implements Dictionary{

    private LinkedList<ItemTabHash>[] content;
    private HashEngine hashEngine;
    private int quant = 0;
    private int N;

    public TabHash(int size){
        this.hashEngine = new MyHashEngine();
        // Fator de carga N permite que a probabilidade de ter colisões seja menor.
        // Estudos de caso médio sugerem que utilize um valor < 0.9 para o encadeamento.
        this.N = (int)(size/0.8);
        content = new LinkedList[this.N];
    }

    public TabHash(int size, HashEngine hashEngine){
        this.N = (int)(size/0.8);
        content = new LinkedList[this.N];
        this.hashEngine = hashEngine;
    }

    /**
     *
     * @param key
     * @return retorna o index na LinkedList onde ele achou a KEY, ou -1 se não econtrar.
     */

    private int searchKey(Object key){
        String keyS = (String) key;
        int pos = this.hashEngine.hashFunction(key) % N;
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

        int pos = this.hashEngine.hashFunction(key) % this.N;
        LinkedList<ItemTabHash> toAdd = new LinkedList<>();

        if(content[pos] == null){
            toAdd.add(new ItemTabHash((String)key, (Dado)value));
            content[pos] = toAdd;
            quant++;
        }else{
            // Se já existe o key passada como parâmetro, então o valor é sobrescrito
            int i = searchKey(key);
            if(i != -1){
                content[pos].get(i).setDado((Dado) value);
            }else{
                content[pos].add(new ItemTabHash(keyS, (Dado)value));
                quant++;
            }
        }
    }

    public Object remove(Object key){
        String keyS = (String)key;
        int index = this.hashEngine.hashFunction(key) % this.N;
        int indexLinkedList = searchKey(key);
        Object removed = null;

        if(indexLinkedList != -1){
            removed = content[index].get(indexLinkedList);
            content[index].remove(indexLinkedList);
            quant--;
        }
        return removed;
    }

    /**
     *
     * @param key
     * @return retorna o índice no vetor da chave KEY passada como parâmetro
     */
//    private int polynomialAcc(String key){
//        double f = 0;
//
//        for(int i = 0; i < key.length(); i++){
//            f = key.codePointAt(i) * Math.pow(base, i);
//        }
//        return (int)(f % this.N);
//    }

    private int asciiSum(String key){
        double f = 0;

        for(int i = 0; i < key.length(); i++){
            f += key.codePointAt(i);
        }

        return (int)(f % this.N);
    }

    /**
     *
     * @param key
     * @return Este método retorna um objeto do tipo Dado a partir de uma determinada KEY procurada. Caso não encontre, retorna null.
     *
     */
    public Object getElement(Object key){
        String keyS = (String) key;
        int index = this.hashEngine.hashFunction(key) % this.N;
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
        int index = this.hashEngine.hashFunction(key) % this.N;

        return content[index];
    }

    /**
     *
     * @return retorna um List<Object> com todas as chaves</>
     */
    public List<Object> getKeys(){
        List<Object> keyList = new ArrayList<>();

        for(int i = 0; i < this.N; i++){
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

        for(int i = 0; i < this.N; i++){
            for(int j = 0; j < content[i].size(); j++){
                valueList.add(content[i].get(j).getDado());
            }
        }
        return valueList;
    }

    public int size(){
        return this.quant;
    }
    public boolean isEmpty(){
        return this.quant == 0;
    }

}
