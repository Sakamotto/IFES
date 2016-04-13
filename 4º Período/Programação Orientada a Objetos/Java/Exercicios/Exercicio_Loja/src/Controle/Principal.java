package Controle;

import Entidade.Livro;
import Entidade.Mouse;

/**
 * Created by 20142bsi0054 on 12/04/2016.
 */
public class Principal {

    public static void main(String args[]){

        Mouse m = new Mouse("Tipo 1");

        System.out.println(m.getDescricao());


        Livro l = new Livro("A Ordem dos Arqueiros");

        System.out.println(l.getDescricao());
    }

}
