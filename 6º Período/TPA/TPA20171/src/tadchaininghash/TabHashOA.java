package tadchaininghash;

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

    @Override
    public void add(Object key, Object value) {
        int index = this.hashEngine.hashFunction(key) % N;
        int currentPos = index;

        if(this.content[index] == null){
            this.content[index] = new ItemTabHash((String)key, (Dado)value);
        }else{
            // Se for igual ao valor que já existe, então ele é sobrescrito
            if(this.content[index].getKey() == key){
                this.content[index].setDado((Dado) value);
            }else{
                /**
                 TODO: Realizar o tratamento para quando a tabela estiver totalmente cheia, para não ficar em loop infinito.
                  */
                while((this.content[index] != null) && (currentPos <= index)){
                    index = (index + 1) % this.N;
                }
                this.content[index] = new ItemTabHash((String)key, (Dado)value);
            }
        }
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public Object getElement(Object key) {
        return null;
    }

    @Override
    public List<Object> getKeys() {
        return null;
    }

    @Override
    public List<Object> getElements() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
