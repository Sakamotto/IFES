package hashmelhorado;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by cristian on 23/04/17.
 */

class MyHashEngine extends HashEngine{

    @Override
    public int hashCode(Object key) {
        byte[] bytes = {};
        double f = 0;
        int base = 2;

        try {
            bytes = Serialization.convertToBytes(key);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < bytes.length; i++){
            f += (bytes[i] + base*i);
        }

        return Math.abs((int)f);
    }
}


public class TabHChain extends TADTabH{

    private LinkedList<Item>[] content;
    private HashEngine hashEngine;
    private int quant = 0;
    private int N;
    private int tamanhoInicial = 1024;
    private ArrayList<Integer[]> listColisoes = new ArrayList<>();

    public static Item NO_SUCH_KEY = new Item(null, null);

    public TabHChain(){
        this.hashEngine = new MyHashEngine();
        // Fator de carga N permite que a probabilidade de ter colisões seja menor.
        // Estudos de caso médio sugerem que utilize um valor < 0.9 para o encadeamento.
        this.N = (int)(this.tamanhoInicial/0.8);
        content = new LinkedList[N];
    }

    public TabHChain(HashEngine hE){
        this.hashEngine = hE;
        // Fator de carga N permite que a probabilidade de ter colisões seja menor.
        // Estudos de caso médio sugerem que utilize um valor < 0.9 para o encadeamento.
        this.N = (int)(this.tamanhoInicial/0.8);
        content = new LinkedList[N];
    }

    public TabHChain(int tam, HashEngine hE){
        this.N = (int)(tam/0.8);
        content = new LinkedList[this.N];
        this.hashEngine = hE;
    }

    private int searchKey(Object key){
        int pos = this.hashEngine.hashCode(key) % N;
        int index = -1;

        if(content[pos] != null){
            for (Item item: content[pos]) {
                index++;
                if(key.equals(item.getKey())){
                    return index;
                }
            }
        }
        return -1;
    }

    /**
     * Este método deve salvar um arquivo texto organizado da seguinte forma:
     dois valores inteiros separados por vírgula. O primeiro
     valor é a posição do vetor de conteúdos (vetor de buckets, para usar a nomenclatura do livro
     texto); o segundo valor é a quantidade de colisões ocorrida para esta posição
     */
    public void salvaColisoes(String nomeArq){
        BufferedWriter br;
        try{

            br = new BufferedWriter(new OutputStreamWriter((new FileOutputStream(nomeArq))));

            for(int i = 0; i < listColisoes.size(); i++){
                String salvar = listColisoes.get(i)[0] + "," + listColisoes.get(i)[1] + "\n";
                br.append(salvar);
            }
            br.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private int findInList(ArrayList<Integer[]> lista, int pos){
        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i)[0] == pos){
                return i;
            }
        }
        return -1;
    }

    private void addColisao(int pos){
        Integer array[];
        int index = findInList(listColisoes, pos);

        if(index == -1){
            array = new Integer[2];
            array[0] = pos;
            array[1] = 1;
            listColisoes.add(array);
        }else{
            listColisoes.get(index)[1] = listColisoes.get(index)[1] + 1;
        }
    }

    private void redimensiona(){
        int NN = 2*N;
        LinkedList<Item> contentRedimensiona[] = new LinkedList[NN];

        System.out.println("REDIMENSIONA FALANDO ... :)");

        for(int i = 0; i < content.length; i++){
            if(content[i] != null){
                for(int j = 0; j < content[i].size(); j++){
                    int hash = content[i].get(j).getCacheHashCode();
                    int index = hash % NN;

                    // para cada elemento, deve ser calculado a sua nova posição no novo vetor
                    if(contentRedimensiona[index] != null){ // NO_SUCH_KEY
                        contentRedimensiona[index].add(content[i].get(j));
                    }else{
                        LinkedList<Item> toAdd = new LinkedList<>();
                        toAdd.add(content[i].get(j));
                        contentRedimensiona[index] = toAdd;
                    }
                }
            }
        }
        this.N = NN;
        this.content = contentRedimensiona;
    }

    @Override
    public boolean insertItem(Object key, Object elem) {
        int hash = this.hashEngine.hashCode(key);
        int pos = hash % this.N;
        LinkedList<Item> toAdd = new LinkedList<>();

        if(content[pos] == null){// NO_SUCH_KEY ...
            toAdd.add(new Item(key, elem));
            content[pos] = toAdd;
            content[pos].get(0).setCacheHashCode(hash);
            quant++;
            addColisao(pos);
            return true;
        }else{
            int i = searchKey(key);
            if(i != -1){
                return false;// Achou a chave, então não insere e apenas retorna false;
            }else{
                addColisao(pos);
                content[pos].add(new Item(key, elem));
                content[pos].getLast().setCacheHashCode(hash);
                quant++;
            }
        }

        // DEPOIS QUE O ELEMENTO FOI ADICIONADO, VERIFICO SE A TABELA PRECISA SER REDIMENSIONADA
        if(((float)(quant / N)) >= 0.95){
            redimensiona();
        }
        return true;
    }

    @Override
    public Object removeElem(Object key) {
        int index = this.hashEngine.hashCode(key) % this.N;
        int indexLinkedList = searchKey(key);
        Object removed = NO_SUCH_KEY;

        if(indexLinkedList != -1){
            removed = content[index].get(indexLinkedList).getElemento();
            content[index].remove(indexLinkedList);
            quant--;
        }
        return removed;
    }

    /**
     *
     * @param key
     * @return Este método, retorna o Item dentro da tabela, não o Elemento
     */
    private Object get(Object key) {
        int index = this.hashEngine.hashCode(key) % this.N;
        int indexLinkedList = searchKey(key);

        if(indexLinkedList != -1){
            return content[index].get(indexLinkedList);
        }
        return null;
    }


    @Override
    public Object findElem(Object key) {
        int index = this.hashEngine.hashCode(key) % this.N;
        int indexLinkedList = searchKey(key);

        if(indexLinkedList != -1){
            return content[index].get(indexLinkedList).getElemento();
        }
        return NO_SUCH_KEY;
    }

    @Override
    public int size() {
        return this.quant;
    }

    @Override
    public boolean empty() {
        return this.quant == 0;
    }

    @Override
    public LinkedList<Object> keys() {
        LinkedList<Object> keyList = new LinkedList<>();

        for(int i = 0; i < this.N; i++){
            if(content[i] != null){
                for(int j = 0; j < content[i].size(); j++){
                    keyList.add(content[i].get(j).getKey());
                }
            }
        }
        return keyList;
    }

    @Override
    public LinkedList<Object> elements() {
        LinkedList<Object> valueList = new LinkedList<>();

        for(int i = 0; i < this.N; i++){
            if(content[i] != null){
                for(int j = 0; j < content[i].size(); j++){
                    valueList.add(content[i].get(j).getElemento());
                }
            }
        }
        return valueList;
    }

}
