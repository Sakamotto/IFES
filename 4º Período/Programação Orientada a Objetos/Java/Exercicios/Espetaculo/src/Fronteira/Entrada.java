package Fronteira;

import java.util.Scanner;

/**
 * Created by 20142BSI0054 on 13/04/2016.
 */
public class Entrada {

    private Scanner entrada = new Scanner(System.in);
    private int tipoLocal;
    private int tipoIngresso;

    public void imprimeOpcoes(){
        System.out.println("--------------- Local ---------------\n");
        System.out.println("1 - Teatro");
        System.out.println("2 - Cinema");
        tipoLocal = entrada.nextInt();

        System.out.println("\n--------------- Opções ---------------\n");
        System.out.println("1 - Simples");
        System.out.println("2 - Especial");
        System.out.println("3 - Camarote");
        System.out.println("Digite a opção desejada: ");
        tipoIngresso = entrada.nextInt();
    }

    public int getTipoLocal(){
        return this.tipoLocal;
    }

    public int getTipoIngreso(){
        return this.tipoIngresso;
    }


}
