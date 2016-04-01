package Entidade;

/**
 * Created by cristian on 31/03/16.
 */
public class Equacao2G extends Equacao1G{

    private double b;

    public double calcularDelta(double a, double b, double c){ //b, c, d ...

        return (Math.pow(b, 2) - 4*a*c);
    }

    public double[] calcularRaizes(double delta, double b, double a){
        double valores[] = new double[2];

        valores[0] = ((-b + Math.sqrt(delta)) / 2*a);
        valores[1] = ((-b - Math.sqrt(delta)) / 2*a);

        return valores;
    }


    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }
}
