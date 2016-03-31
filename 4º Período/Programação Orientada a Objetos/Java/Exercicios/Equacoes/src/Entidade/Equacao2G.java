package Entidade;

/**
 * Created by cristian on 31/03/16.
 */
public class Equacao2G extends Equacao3G{


    public double calcularDelta(double a, double b, double c){

        return (Math.pow(b, 2) - 4*a*c);
    }

    public double[] calcularRaizes(double delta, double b, double a){
        double valores[] = new double[2];

        valores[0] = ((-b + Math.sqrt(delta)) / 2*a);
        valores[1] = ((-b - Math.sqrt(delta)) / 2*a);

        return valores;
    }



}
