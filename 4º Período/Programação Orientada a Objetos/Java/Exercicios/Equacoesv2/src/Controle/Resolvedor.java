package Controle;

import Entidade.Equacao2G;
import Entidade.Equacao3G;
import Entidade.Equacao1G;
import Fronteira.Entrada;

/**
 * Created by cristian on 31/03/16.
 */
public class Resolvedor {


    public static void calcularEq3G(){
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

        double[] res = eq3.calcularRaizes(a, b, c, b); // 1, -6, 0, -9

        System.out.println("X1: " + res[0]);
        System.out.println("X2: " + res[1]);
        System.out.println("X3: " + res[2]);

    }

    public static void calcularEq2G(){
        Equacao2G eq2 = new Equacao2G();
        Entrada in = new Entrada();

        double b, c, d, delta;

        System.out.println("B: ");
        b = in.lerCoeficiente();
        eq2.setB(b);


        System.out.println("C: ");
        c = in.lerCoeficiente();
        eq2.setC(c);

        System.out.println("D: ");
        d = in.lerCoeficiente();
        eq2.setD(d);

        delta = eq2.calcularDelta(b, c, d);

        double[] res = eq2.calcularRaizes(delta, c, b); // 1, -6, 0, -9

        System.out.println("Delta: " + delta + "\n");
        System.out.println("X1: " + res[0]);
        System.out.println("X2: " + res[1]);

    }

    public static void calcularEq1G(){
        Equacao1G eq1 = new Equacao1G();
        Entrada in = new Entrada();

        double c, d;

        System.out.println("C: ");
        c = in.lerCoeficiente();
        eq1.setC(c);

        System.out.println("D: ");
        d = in.lerCoeficiente();
        eq1.setD(d);

        double res = eq1.calcularRaizes(c, d); // 1, -6, 0, -9

        System.out.println("X1: " + res);

    }


    public static void main(String[] args){

//        calcularEq3G();
//        calcularEq2G();
//        calcularEq1G();

    }

}
