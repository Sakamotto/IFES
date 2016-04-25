package Entidade;

/**
 * Created by 20142BSI0054 on 13/04/2016.
 */
public class Temporada {

    //public static int MAX_INGRESSOS;
    public Ingresso ing[];
    public int qtdIngressosVendidos = 0;
    private int tipoLocal;// 1 - Teatro, 2 - Cinema
    private int tipoIngresso;// 1 - Teatro, 2 - Cinema

    /*
    * Ingresso tipo 1: Simples
    * Ingresso tipo 2: Especial
    * Ingresso tipo 3: Camarote
    */

    public void venderIngresso(int tipoLocal, int tipoIngresso){
        this.tipoLocal = tipoLocal;
        this.tipoIngresso = tipoIngresso;

        if(tipoLocal == 1){
            Teatro t = new Teatro();
            if(tipoIngresso == 1 && t.getNiSimples() > 0){
                Teatro.numCadeirasSimples--;
            }else if(tipoIngresso == 2 && t.getNiEspecial() > 0){
                Teatro.numCadeirasEspeciais--;
            }else if(tipoIngresso == 3 && t.getNiCamarote() > 0){
                Teatro.numCadeirasCamarote--;
            }
        }else{
            Cinema c = new Cinema();

            if(tipoIngresso == 1 && c.getNiSimples() > 0){
                Cinema.numCadeirasSimples--;
            }else if(tipoIngresso == 2 && c.getNiEspecial() > 0){
                Cinema.numCadeirasEspeciais--;
            }
        }
    }

    public void definirTemporada(){

    }

    public void imprimirDadosDaTemporada(){
        Simples s = new Simples();
        Especial e = new Especial();
        Camarote c = new Camarote();

        if(tipoLocal == 1){

            System.out.println("Teatro");

            System.out.println("Ingressos Simples: " + Teatro.numCadeirasSimples);
            System.out.println("Preço: \n" + s.getPreco());
            System.out.println("Fileira: \n" + s.getFileira());

            System.out.println("Ingressos Especiais: " + Teatro.numCadeirasEspeciais);
            System.out.println("Preço: \n" + e.getPreco());
            System.out.println("Brinde: " + e.getBrinde());
            System.out.println("Cadeira: " + e.getCadeira());


            System.out.println("Ingressos Camarote: " + Teatro.numCadeirasCamarote);
            System.out.println("Preço: \n" + c.getPreco());
            System.out.println("Jantar: " + c.getJantar());
            System.out.println("Bebida: " + c.getBebida());


        }else{
            System.out.println("Cinema");
            System.out.println("Ingressos Simples: " + Teatro.numCadeirasSimples);
            System.out.println("Preço: \n" + s.getPreco());
            System.out.println("Fileira: \n" + s.getFileira());

            System.out.println("Ingressos Especiais: " + Teatro.numCadeirasEspeciais);
            System.out.println("Preço: \n" + e.getPreco());
            System.out.println("Brinde: " + e.getBrinde());
            System.out.println("Cadeira: " + e.getCadeira());
        }
    }

    public int getQtdIngressos() {
        return qtdIngressosVendidos;
    }

    public void setQtdIngressos(int qtdIngressos) {
        this.qtdIngressosVendidos = qtdIngressos;
    }
}
