package tadchaininghash;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristian on 05/04/17.
 */

class MyHashEngineOA extends HashEngine{

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

public class TabHashOA implements Dictionary{

    private ItemTabHash content[];
    private int N;
    private HashEngine hashEngine;
    public static ItemTabHash FREE = new ItemTabHash(null, null);
    public static ItemTabHash NO_SUCH_KEY = new ItemTabHash(null, null);
    private int quant = 0;

    public TabHashOA(int size){
        // Fator de carga N permite que a probabilidade de ter colisões seja menor.
        // Estudos de caso médio sugerem que utilize um valor < 0.5 para o endereçamento aberto.
        N = (int)(size/0.8);
        this.content = new ItemTabHash[N];
        this.hashEngine = new MyHashEngineOA();
    }

    public TabHashOA(int size, HashEngine hashEngine){
        N = (int)(size/0.8);
        this.content = new ItemTabHash[N];
        this.hashEngine = hashEngine;
    }

    private void redimensiona(){
        int NN = 2*N;
        ItemTabHash contentRedimensiona[] = new ItemTabHash[NN];

        System.out.println("REDIMENSIONA FALANDO ... :)");

        for(int i = 0; i < content.length; i++){
            if(content[i] != null){
                int hash = content[i].getHash();
                int index = hash % NN;
                int currentPos = index;

                if(contentRedimensiona[index] == null){
                    contentRedimensiona[index] = content[i];
                }else{
                    index = (index + 1) % NN;
                    while((contentRedimensiona[index] != null)  && (currentPos != index)){
                        index = (index + 1) % NN;
                    }

                    if(index != currentPos){
                        contentRedimensiona[index] = content[i];
                    }
                }
            }
        }
        this.N = NN;
        this.content = contentRedimensiona;
    }

    @Override
    public void add(Object key, Object value) {
        int hash = this.hashEngine.hashFunction(key);
        int index = hash % N;
        int currentPos = index;

        if(this.content[index] == null){
            this.content[index] = new ItemTabHash((String)key, (Dado)value);
            this.content[index].setHash(hash);
            quant++;
        }else{
            // Se for igual ao valor que já existe, então ele é sobrescrito
            if(this.content[index].getKey() == key){
                this.content[index].setDado((Dado) value);
            }else{
                index = (index + 1) % N;
                while((this.content[index] != null) && (currentPos != index)){
                    index = (index + 1) % this.N;
                }

                if(index != currentPos){
                    this.content[index] = new ItemTabHash((String)key, (Dado)value);
                    this.content[index].setHash(hash);
                    quant++;
                }
            }
        }

        // DEPOIS QUE O ELEMENTO FOI ADICIONADO, VERIFICO SE A TABELA PRECISA SER REDIMENSIONADA
        if(((float)(quant / N)) >= 0.75){
            redimensiona();
        }

    }

    @Override
    public Object remove(Object key) {
        int index = this.hashEngine.hashFunction(key) % N;
        Object removed = null;

        if(this.content[index] != null){
            removed = this.content[index];
            this.content[index] = null;
            quant--;
        }

        return removed;
    }

    @Override
    public Object getElement(Object key) {
        int index = this.hashEngine.hashFunction(key) % N;
        String keyS = (String) key;
        int currentPos = index;

        if(this.content[index] == null){
            System.out.println("Entrei aqui ...");
            return null;
        }

        if(this.content[index].getKey().equals(keyS)){
            return content[index].getDado();
        }else{
            index = (index + 1) % N;

            while((this.content[index] == null) || (!this.content[index].getKey().equals(keyS)) && (currentPos != index)){
                index = (index + 1) % N;
                //System.out.println(index);
            }

            if(currentPos != index){
                return content[index].getDado();
            }
            return null;
        }
    }

    @Override
    public ArrayList<Object> getKeys() {
        ArrayList<Object> allKeys = new ArrayList<>();

        for(int i = 0; i < content.length; i++){
            if(content[i] != null){
                allKeys.add(content[i].getKey());
            }
        }

        return allKeys;
    }

    @Override
    public ArrayList<Object> getElements() {
        ArrayList<Object> allElements = new ArrayList<>();

        for(int i = 0; i < content.length; i++){
            if(content[i] != null) {
                allElements.add(content[i].getDado());
            }
        }
        return allElements;
    }

    @Override
    public int size() {
        return this.quant;
    }

    @Override
    public boolean isEmpty() {
        return this.quant == 0;
    }
}
