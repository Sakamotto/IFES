package Entidade;


/**
 * Created by Cristian on 31/03/16.
 */

public class Equacao3G extends Equacao1G{

    private double a;
    private double b;


    public double[] calcularRaizes(double a, double  b, double c, double d){
        double raizes[] = new double[3];

        double q = ((3*c - Math.pow(b, 2))/9);
        double r = ((9*c*b - 27*d - 2*Math.pow(b, 3))/54);
        double s, t, p;

        double discrim = Math.pow(q, 3) + Math.pow(q, 2);

        if(discrim >= 0){

            s = Math.cbrt(r + Math.sqrt(Math.pow(q, 3) + Math.pow(r, 2)));
            t = Math.cbrt(r - Math.sqrt(Math.pow(q, 3) + Math.pow(r, 2)));

            raizes[0] = (s + t) - (b/3);
            raizes[1] = ((s+t)*(-1.0/2.0)) + (-1.0/3.0)*b + ((1.0/2.0)*Math.sqrt(3)*(s-t));
            raizes[2] = ((s+t)*(-1.0/2.0)) + (-1.0/3.0)*b - ((1.0/2.0)*Math.sqrt(3)*(s-t));
        }else{
            double i, j, k, l;
            p = (c/a) - (Math.pow(b, 2) / (3*Math.pow(a, 2)));
            q = (2*Math.pow(b, 3)) / (27*Math.pow(a, 3)) - ((b*c)/ (3*Math.pow(a, 2)))  + (d/a);

            i = -p/3;
            j = -q/2;
            k = -27/(Math.pow(p, 3));
            l = -b/3*a;

            raizes[0] = 2.0*Math.sqrt(i)*Math.cos((1.0/3.0)*Math.acos(j*Math.sqrt(k))) + l;

            raizes[1] = 2.0*Math.sqrt(i)*Math.cos((1.0/3.0)*Math.acos(j*Math.sqrt(k)) + ((2*Math.PI)/3.0)) + l;

            raizes[2] = 2.0*Math.sqrt(i)*Math.cos((1.0/3.0)*Math.acos(j*Math.sqrt(k)) + ((4*Math.PI)/3.0)) + l;
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
