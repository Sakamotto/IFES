package Entidade;


/**
 * Created by Cristian on 31/03/16.
 */

public class Equacao3G extends Equacao1G{

    private double a;
    private double b;


    public double[] calcularRaizes(double a, double  b, double c, double d){
        double raizes[] = new double[3];

        double q = (3*c - Math.pow(b, 2))/9;
        double r = 9*c*b - 27*d - 2*Math.pow(b, 3);
        double s = Math.pow(r + Math.pow(Math.pow(q, 3) + Math.pow(r, 2),1/3), 1/3);
        double t = Math.pow(r - Math.pow(Math.pow(q, 3) + Math.pow(r, 2),1/3), 1/3);

        raizes[0] = s + t - (b/3);

        if(Math.pow(q, 3) + Math.pow(q, 2) <= 0){
            raizes[1] = (s+t)/2 - b/3 + (Math.pow(3, 1/3)*(s-t))/2;
            raizes[2] = (s+t)/-2 - b/3 + (Math.pow(3, 1/3)*(s-t))/2;
        }


        return raizes;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
}
