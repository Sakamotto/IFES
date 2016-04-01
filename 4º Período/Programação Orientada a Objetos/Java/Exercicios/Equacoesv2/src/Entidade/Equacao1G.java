package Entidade;

/**
 * Created by cristian on 31/03/16.
 */
public class Equacao1G{

    private double c;
    private double d;

    public double calcularRaizes(double c, double d) {

        return -d / c;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }
}
