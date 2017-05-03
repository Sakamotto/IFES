package matrizmelhorada;

import Utils.StreamLoader;
import hashmelhorado.TabHChain;

import java.io.*;
import java.util.LinkedList;

/**
 * Created by cristian on 23/04/17.
 */
public class TADMatHChain extends TADMatH {

    private TabHChain matriz;
    private int linhas;
    private int colunas;
    private int quant = 0;

    public TADMatHChain(int linhas, int colunas) {
        super(linhas, colunas);
        this.linhas = linhas;
        this.colunas = colunas;
        matriz = new TabHChain();
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
        Object result = matriz.findElem(generateKey(i - 1, j - 1)); // diminui 1, por causa do arquivo MMF
        if((i >= 0 && i < getLinhas()) && (j >= 0 && j < getColunas())){
            if(result != TabHChain.NO_SUCH_KEY){
                return ((ElemMat) result).getElem(i, j);
            }
        }
        return 0;
    }

    private int generateKey(int i, int j){
        return ((i+1) * (j+2));
    }

    @Override
    public void setElem(int i, int j, float elem) {
        // IF que verifica se o valor procurado está dentro dos limites da
        // matriz ...
        if ((i >= 0 && i < getLinhas()) && (j >= 0 && j < getColunas())) {
            int k = generateKey(i, j);
            if(elem != 0f){
                if(matriz.findElem(k) != TabHChain.NO_SUCH_KEY){
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

    public static TADMatHChain carregaMMF(String nomeArq) {
        TADMatHChain carregada = null;
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
            carregada = new TADMatHChain(Integer.parseInt(linhaSplit[0]), Integer.parseInt(linhaSplit[1]));

            while(br.ready()){
                linha = br.readLine();
                linhaSplit = linha.split(" ");
                int l = Integer.parseInt(linhaSplit[0]) - 1;
                int c = Integer.parseInt(linhaSplit[1]) - 1;
                float e = Float.parseFloat(linhaSplit[2]);
//                System.out.println("i: " + l + " j: " + c + " E: " + e);

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
                int i = ((ElemMat)matriz.findElem(o)).getI() + 1;
                int j = ((ElemMat)matriz.findElem(o)).getJ() + 1;
                String salvar = i + " " + j + " " + ((ElemMat)matriz.findElem(o)).getElem(i, j) + "\n";
                bw.append(salvar);
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

    public void printKeys(){
        LinkedList<Object> listKeys = matriz.keys();

        for(Object o: listKeys){
            System.out.println(o);
        }
    }
}
