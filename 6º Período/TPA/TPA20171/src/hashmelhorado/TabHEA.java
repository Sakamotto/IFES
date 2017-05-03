package hashmelhorado;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by cristian on 23/04/17.
 */
public class TabHEA extends TADTabH {

    private Item content[];
    private int N;
    private HashEngine hashEngine;
    public static final Item NO_SUCH_KEY = new Item(null, null);
    private int quant = 0;
    private int tamanhoInicial = 1024;
    private ArrayList<Integer[]> listColisoes = new ArrayList<>();

    public TabHEA(){
        this.hashEngine = new HashEngineDefault();
        // Fator de carga N permite que a probabilidade de ter colisões seja menor.
        // Estudos de caso médio sugerem que utilize um valor < 0.9 para o encadeamento.
        N = (int)(this.tamanhoInicial/0.8);
        this.content = new Item[N];
    }

    public TabHEA(HashEngine hE){
        this.hashEngine = hE;
        // Fator de carga N permite que a probabilidade de ter colisões seja menor.
        // Estudos de caso médio sugerem que utilize um valor < 0.9 para o encadeamento.
        this.N = (int)(this.tamanhoInicial/0.8);
        content = new Item[N];
    }

    public TabHEA(int tam, HashEngine hE){
        this.N = (int)(tam/0.8);
        content = new Item[this.N];
        this.hashEngine = hE;
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
        Item contentRedimensiona[] = new Item[NN];

        System.out.println("REDIMENSIONA FALANDO ... :)");

        for(int i = 0; i < content.length; i++){
            if(content[i] != null){
                int hash = content[i].getCacheHashCode();
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
    public boolean insertItem(Object key, Object elem) {
        int hash = this.hashEngine.hashCode(key);
        int index = hash % N;
        int currentPos = index;

        if(this.content[index] == null){
            this.content[index] = new Item(key, elem);
            this.content[index].setCacheHashCode(hash);
            quant++;
        }else{
            // Se for igual ao valor que já existe, então não é inserido e retorna falso
            if(this.content[index].getKey() == key){
                return false;
            }else{
                addColisao(index);
                index = (index + 1) % N;
                while((this.content[index] != null) && (currentPos != index)){
                    index = (index + 1) % this.N;
                }

                if(index != currentPos){
                    this.content[index] = new Item(key, elem);
                    this.content[index].setCacheHashCode(hash);
                    quant++;
                }
            }
        }
//        System.out.println(index);

        // DEPOIS QUE O ELEMENTO FOI ADICIONADO, VERIFICO SE A TABELA PRECISA SER REDIMENSIONADA
        if(((float)(quant / N)) >= 0.95){
            redimensiona();
        }

        return true;
    }

    @Override
    public Object removeElem(Object key) {
        int index = this.findItem(key) % N;
        Object removed = NO_SUCH_KEY;

        if(index < 0){
            return removed;
        }

        removed = this.content[index];
        this.content[index] = null;

        return removed;
    }

    private int findItem(Object key){

        int index = this.hashEngine.hashCode(key) % N;
        int currentPos = index;
        do{
            if(this.empty()) return -1;
            if(this.content[index] == null) index = (index + 1) % N;
            else if(this.content[index].getKey().equals(key))
                return index;
            else
                index = (index + 1) % N;

        }while(index != currentPos);
        return -1;
    }

    @Override
    public Object findElem(Object key) {
        int index = this.findItem(key) % N;

        if(index < 0){
            return NO_SUCH_KEY;
        }
        return this.content[index].getElemento();

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
        LinkedList<Object> allKeys = new LinkedList<>();

        for(int i = 0; i < content.length; i++){
            if(content[i] != null){
                allKeys.add(content[i].getKey());
            }
        }

        return allKeys;
    }

    @Override
    public LinkedList<Object> elements() {
        LinkedList<Object> allElements = new LinkedList<>();

        for(int i = 0; i < content.length; i++){
            if(content[i] != null) {
                allElements.add(content[i].getElemento());
            }
        }
        return allElements;
    }

}
