package tadhash;


import Utils.Serialization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cristian on 22/03/17.
 */

class MyHashEngine extends HashEngine {

    @Override
    public int hashFunction(Object key) {
        byte[] bytes = {};
        double f = 0;
        int base = 33;

        try {
            bytes = Serialization.convertToBytes(key);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < bytes.length; i++) {
            f = bytes[i] * Math.pow(base, i);
        }
        return (int) f;
    }
}

public class TabHash<K, V> {

    private LinkedList<ItemTabHash>[] content;
    private HashEngine hashEngine;
    private int quant = 0;
    private int N;

    public TabHash(int size) {
        this.hashEngine = new MyHashEngine();
        // Fator de carga N permite que a probabilidade de ter colisões seja menor.
        // Estudos de caso médio sugerem que utilize um valor < 0.9 para o encadeamento.
        this.N = (int) (size / 0.8);
        content = new LinkedList[N];
    }

    public TabHash(int size, HashEngine hashEngine) {
        this.N = (int) (size / 0.8);
        content = new LinkedList[this.N];
        this.hashEngine = hashEngine;
    }


    /**
     * @param key
     * @return retorna o index na LinkedList onde ele achou a KEY, ou -1 se não econtrar.
     */
    private int searchKey(K key) {
        //String keyS = (String) key;
        int pos = this.hashEngine.hashFunction(key) % N;
        int index = -1;

        if (content[pos] != null) {
            for (ItemTabHash item : content[pos]) {
                index++;
                if (key.equals(item.getKey())) {
                    return index;
                }
            }
        }
        return -1;
    }

    private void redimensiona() {
        int NN = 2 * N;
        LinkedList<ItemTabHash> contentRedimensiona[] = new LinkedList[NN];

        System.out.println("REDIMENSIONA FALANDO ... :)");

        for (int i = 0; i < content.length; i++) {
            if (content[i] != null) { // Acho que é uma verificação desnecessária ...
                for (int j = 0; j < content[i].size(); j++) {
                    int hash = content[i].get(j).getHash();
                    int index = hash % NN;

                    // para cada elemento, deve ser calculado a sua nova posição no novo vetor
                    if (contentRedimensiona[index] != null) { // NO_SUCH_KEY
                        contentRedimensiona[index].add(content[i].get(j));
                    } else {
                        System.out.println(">>>> Redimensiona: Else inesperado ... <<<<");
                        LinkedList<ItemTabHash> toAdd = new LinkedList<>();
                        toAdd.add(content[i].get(j));
                        contentRedimensiona[index] = toAdd;
                    }
                }
            }
        }
        this.N = NN;
        this.content = contentRedimensiona;
    }


    /**
     * Este método adiciona um objeto value em uma determinada chave. Esse Objeto value é um objeto do tipo Dado.
     *
     * @param key
     * @param value
     */

    public void add(K key, V value) {
        //String keyS = (String) key;
        int hash = this.hashEngine.hashFunction(key);
        int pos = hash % this.N;
        LinkedList<ItemTabHash> toAdd = new LinkedList<>();

        if (content[pos] == null) {
            toAdd.add(new ItemTabHash(key, value));
            content[pos] = toAdd;
            content[pos].get(0).setHash(hash);
            quant++;
        } else {
            // Se já existe o key passada como parâmetro, então o valor é sobrescrito
            int i = searchKey(key);
            if (i != -1) {
                content[pos].get(i).setDado(value);
            } else {
                content[pos].add(new ItemTabHash(key, value));
                content[pos].getLast().setHash(hash);
                quant++;
            }
        }

        // DEPOIS QUE O ELEMENTO FOI ADICIONADO, VERIFICO SE A TABELA PRECISA SER REDIMENSIONADA
        if(((float)(quant / N)) >= 0.75){
            redimensiona();
        }

    }

    public V remove(K key) {
        String keyS = (String) key;
        int index = this.hashEngine.hashFunction(key) % this.N;
        int indexLinkedList = searchKey(key);
        V removed = null;

        if (indexLinkedList != -1) {
            removed = (V) content[index].get(indexLinkedList).getDado();
            content[index].remove(indexLinkedList);
            quant--;
        }
        return removed;
    }

    /**
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
    private int asciiSum(String key) {
        double f = 0;

        for (int i = 0; i < key.length(); i++) {
            f += key.codePointAt(i);
        }

        return (int) (f % this.N);
    }

    /**
     * @param key
     * @return Este método retorna um objeto do tipo Dado a partir de uma determinada KEY procurada. Caso não encontre, retorna null.
     */
    public V getElement(K key) {
//        String keyS = (String) key;
        int index = this.hashEngine.hashFunction(key) % this.N;
        int indexLinkedList = searchKey(key);

        if (indexLinkedList != -1) {
            return (V) content[index].get(indexLinkedList).getDado();
        }
        return null;
    }

    /**
     * @param key
     * @return retorna um ItemTabHash com base na KEY fornecida.
     */
    public ItemTabHash get(K key) {
        int index = this.hashEngine.hashFunction(key) % this.N;
        int posLinkedList = this.searchKey(key);

        return content[index].get(posLinkedList);
    }

    /**
     * @return retorna um List<Object> com todas as chaves</>
     */
    public List<K> getKeys() {
        List<K> keyList = new ArrayList<>();

        for (int i = 0; i < this.N; i++) {
            if (content[i] != null) {
                for (int j = 0; j < content[i].size(); j++) {
                    keyList.add((K) content[i].get(j).getKey());
                }
            }
        }
        return keyList;
    }

    /**
     * @return retorna um List<Object></> com todos os valores
     */
    public List<V> getElements() {
        List<V> valueList = new ArrayList<>();

        for (int i = 0; i < this.N; i++) {
            if (content[i] != null) {
                for (int j = 0; j < content[i].size(); j++) {
                    valueList.add((V) content[i].get(j).getDado());
                }
            }
        }
        return valueList;
    }

    public int size() {
        return this.quant;
    }

    public boolean isEmpty() {
        return this.quant == 0;
    }

}
