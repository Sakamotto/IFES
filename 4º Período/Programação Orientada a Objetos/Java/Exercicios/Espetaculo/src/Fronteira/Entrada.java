package Fronteira;

import java.util.Scanner;

/**
 * Created by 20142BSI0054 on 13/04/2016.
 */
public class Entrada {

    private Scanner entrada = new Scanner(System.in);

    public void imprimeOpcoes(){
        System.out.println("--------------- Opções ---------------\n");
        System.out.println("1 - Simples");
        System.out.println("2 - Especial");
        System.out.println("3 - Camarote");
        System.out.println("Digite a opção desejada: ");
        leOpcao();
    }

    private int leOpcao(){
        return entrada.nextInt();
    }




}
