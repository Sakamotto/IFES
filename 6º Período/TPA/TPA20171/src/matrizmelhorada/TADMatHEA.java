package matrizmelhorada;

import Utils.StreamLoader;
import hashmelhorado.TabHEA;

import java.io.*;
import java.util.LinkedList;

/**
 * Created by cristian on 23/04/17.
 */
public class TADMatHEA extends TADMatH {

    private TabHEA matriz;
    private int linhas;
    private int colunas;
    private int quant = 0;

    TADMatHEA(int linhas, int colunas) {
        super(linhas, colunas);
        this.linhas = linhas;
        this.colunas = colunas;
        matriz = new TabHEA();
    }

    @Override
    public int getLinhas() {
        return this.linhas;
    }

    @Override
    public int getColunas() {
        return this.colunas;
    }

    @Override
    public float getElem(int i, int j) {
        int k = generateKey(i - 1, j - 1); // diminui 1, por causa do arquivo MMF
        Object result = matriz.findElem(k);
        if((i >= 0 && i < getLinhas()) && (j >= 0 && j < getColunas())){
            if(result != TabHEA.NO_SUCH_KEY){
                return ((ElemMat) result).getElem(i, j);
            }
        }
        return 0;
    }

    private int generateKey(int i, int j){
        return ((i + 1) * (j + 2));
    }

    @Override
    public void setElem(int i, int j, float elem) {
        // IF que verifica se o valor procurado está dentro dos limites da
        // matriz ...
        if ((i >= 0 && i < getLinhas()) && (j >= 0 && j < getColunas())) {
            int k = generateKey(i, j);
            if(elem != 0f){
                if(matriz.findElem(k) != TabHEA.NO_SUCH_KEY){
                    matriz.removeElem(k);
                    matriz.insertItem(k, new ElemMat(i, j, elem));
                }else{
                    matriz.insertItem(k, new ElemMat(i, j, elem));
                    quant++;
                }
            }else{
                if(this.getElem(i, j) != 0){
                    matriz.removeElem(k);
                    quant--;
                }
            }

        }else{
            System.out.println("!!!");
            System.out.println(getLinhas() + " | " + getColunas() + " / "+ this.linhas + " | " + this.colunas);
            System.out.println(i + " | " + j);
        }
    }

    @Override
    public TADMatH multi(TADMatH mat) {
        TADMatH result = new TADMatHChain(this.getLinhas(), mat.getColunas());
        float soma;

        if(this.getColunas() != mat.getLinhas()){
            System.out.println("Matrizes incompatíveis!");
            return null;
        }

        for(int i = 0; i < this.getLinhas(); i++){
            for(int j = 0; j < mat.getColunas(); j++){
                soma = 0;
                for(int k = 0; k < this.getColunas(); k++){
                    soma += (this.getElem(i, k) * mat.getElem(k, j));
                    result.setElem(i, j, soma);
                }
            }
        }

        return result;
    }

    public static TADMatHEA carregaMMF(String nomeArq) {
        TADMatHEA carregada = null;
        StreamLoader streamLoader = new StreamLoader();
        BufferedReader br = streamLoader.readArq(nomeArq);
        String linha;

        try {
            linha = br.readLine();
            String linhaSplit[];
            while(linha.contains("%")){
                linha = br.readLine();
            }
            linhaSplit = linha.split(" ");
            carregada = new TADMatHEA(Integer.parseInt(linhaSplit[0]), Integer.parseInt(linhaSplit[1]));

            while(br.ready()){
                linha = br.readLine();
                linhaSplit = linha.split(" ");
                int l = Integer.parseInt(linhaSplit[0]) - 1;
                int c = Integer.parseInt(linhaSplit[1]) - 1;
                float e = Float.parseFloat(linhaSplit[2]);

                carregada.setElem(l, c, e);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return carregada;
    }

    public void salvaColisoes(String nomeArq){
        matriz.salvaColisoes(nomeArq);
    }

    public void salvaMMF(String nomeArq) {
        BufferedWriter bw;

        try {
            bw = new BufferedWriter(new OutputStreamWriter((new FileOutputStream(nomeArq))));
            LinkedList<Object> listKeys = matriz.keys();
            String header = this.getLinhas() + " " + this.getColunas() + " " + this.quant + "\n";
            bw.append(header);
            for(Object o: listKeys){
                if(matriz.findElem(o) != TabHEA.NO_SUCH_KEY){
                    int i = ((ElemMat)matriz.findElem(o)).getI() + 1;
                    int j = ((ElemMat)matriz.findElem(o)).getJ() + 1;
                    String salvar = i + " " + j + " " + ((ElemMat)matriz.findElem(o)).getElem(i, j) + "\n";
                    bw.append(salvar);
                }
            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean equals(TADMatH pMat) {
        if(this.getColunas() == pMat.getColunas() && this.getLinhas() == pMat.getLinhas()){
            for(int i = 0; i < pMat.getLinhas(); i++){
                for(int j = 0; j < pMat.getColunas(); j++){
                    if(this.getElem(i, j) != pMat.getElem(i, j)){
                        return false;
                    }
                }
            }
        }else{
            return false;
        }

        return true;
    }

}
