package Entidade;


/**
 * Created by Cristian on 31/03/16.
 */

public class Equacao3G extends Equacao1G{

    private double a;
    private double b;


    public double[] calcularRaizes(double a, double  b, double c, double d){
        double raizes[] = new double[3];
        double e, r, t, a2 = b/a, a1 = c/a, a0 = d/a;

        double p = ((a1 - Math.pow(a2, 2))/3);
        double q = ((a0 - a2*a1)/3 + (2/27)*Math.pow(a2, 3));
        double discriminante = Math.pow(q, 2)/4 + Math.pow(p, 3)/27;
        System.out.println("Discriminante: " + discriminante);

        if(discriminante < 0){
            e = Math.sqrt(-(discriminante));

            r = Math.sqrt(Math.pow(q, 2)/4 + Math.pow(e, 2));
            t = Math.acos(-q/(2*r));


            System.out.println("A: " + a2);
            System.out.println("B: " + a1);
            System.out.println("Q: " + q);
            System.out.println("E: " + e);
            System.out.println("R: " + r);
            System.out.println("-q/2*r: " + -q/(2*r));
            System.out.println("T: " + t);

            raizes[0] = 2*Math.cbrt(r)*Math.cos(t/3) - a2/3;
            raizes[1] = 2*Math.cbrt(r)*Math.cos((t+2*Math.PI)/3) - a2/3;
            raizes[2] = 2*Math.cbrt(r)*Math.cos((t+4*Math.PI)/3) - a2/3;

        }else{
            System.out.print("Entrei\n");
            e = Math.sqrt(discriminante);
            double u3 = -q/2 + e;
            double v3 = -q/2 - e;
            double u = Math.cbrt(u3);
            double v = Math.cbrt(v3);

            raizes[0] = u + v - (a2/3);
        }

//        double p1 = ((c/a) - (Math.pow(b, 2)/(3*Math.pow(a, 2))));
//        double q1 = (2*Math.pow(b, 3)/(27*Math.pow(a, 3)) - (b*c)/(3*Math.pow(a, 2)) + (d/a));
//        double discriminante = Math.pow(q1,2) + ((4*Math.pow(p1, 3))/27);


//        double q = ((3*c - Math.pow(b, 2)) / 9);
//        double r = ((9*c*b - 27*d - 2*Math.pow(b, 3)) / 54);
//        double s = Math.cbrt(r + Math.sqrt(Math.pow(q, 3) + Math.pow(r, 2)));
//        double t = Math.cbrt(r - Math.sqrt(Math.pow(q, 3) + Math.pow(r, 2)));
//
//        double delta = Math.pow(q, 3) + Math.pow(r, 2);
//
//        raizes[0] = s + t - (1/3)*b;
//
//
//        if(delta == 0){
//            raizes[1] = ((s+t)*(-1/2)) - (b*(-1/3)) + (Math.sqrt(3)*(s-t))*(1/2);
//            raizes[2] = ((s+t)*(-1/2)) - (b*(-1/3)) + (Math.sqrt(3)*(s-t))*(1/2);
//        }



//        if(Math.pow(q, 3) + Math.pow(r, 2) > 0){
//            raizes[1] = (s+t)/2 - b/3 + (Math.pow(3, 1/3)*(s-t))/2;
//            raizes[2] = (s+t)/-2 - b/3 + (Math.pow(3, 1/3)*(s-t))/2;
//        }


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
