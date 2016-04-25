package Controle;

import Entidade.Temporada;
import Fronteira.Entrada;

import java.util.Scanner;

/**
 * Created by 20142BSI0054 on 13/04/2016.
 */
public class Principal {

    public static void main(String args[]){

        Entrada e = new Entrada();
        Temporada temporada = new Temporada();
        Scanner entrada = new Scanner(System.in);
        int tipoIngresso = 1;

        System.out.println("Digite a opção desejada ou 0 para encerrar");

        e.imprimirLocal();
        int local = entrada.nextInt();

        while(tipoIngresso != 0){
            e.imprimeOpcoes(local);
            tipoIngresso = entrada.nextInt();

            temporada.venderIngresso(local, tipoIngresso);
            System.out.println("--------------\tDados da Temporada --------------\n");
            temporada.imprimirDadosDaTemporada();
        }
    }
}
