package Controle;

import Entidade.Equacao1G;
import Entidade.Equacao2G;
import Entidade.Equacao3G;
import Fronteira.Entrada;

/**
 * Created by cristian on 31/03/16.
 */
public class Resolvedor {


    public static void main(String[] args){

        Equacao3G eq3 = new Equacao3G();
        Entrada in = new Entrada();

        double a, b, c, d;

        System.out.println("A: ");
        a = in.lerCoeficiente();
        eq3.setA(a);

        System.out.println("B: ");
        b = in.lerCoeficiente();
        eq3.setB(b);

        System.out.println("C: ");
        c = in.lerCoeficiente();
        eq3.setC(c);

        System.out.println("D: ");
        d = in.lerCoeficiente();
        eq3.setD(d);


        double[] res = eq3.calcularRaizes(a, b, c, d); // 1, -6, 0, -9

        System.out.println("X1: " + res[0]);
        System.out.println("X2: " + res[1]);
        System.out.println("X3: " + res[2]);

    }

}
