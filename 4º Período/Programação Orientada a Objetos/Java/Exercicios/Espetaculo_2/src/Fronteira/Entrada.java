package Fronteira;

import java.util.Scanner;

/**
 * Created by 20142BSI0054 on 13/04/2016.
 */
public class Entrada {

    private Scanner entrada = new Scanner(System.in);


    public void imprimirLocal(){
        System.out.println("--------------- Local do Evento ---------------");
        System.out.println("1 - Teatro");
        System.out.println("2 - Cinema");
    }

    public void imprimeOpcoes(int local){
        System.out.println("\n--------------- Opções ---------------\n");
        System.out.println("1 - Simples");
        System.out.println("2 - Especial");

        if(local == 1){
            System.out.println("3 - Camarote");
        }
        System.out.println("Digite a opção desejada: ");
    }

}
