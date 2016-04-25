package system;

import java.io.DataInputStream;
import java.util.*;
import java.text.NumberFormat;


public class InputHandler {
    public InputHandler() {
    }

    public static int readInt() {
        int i;
        Integer num;

        DataInputStream in = new DataInputStream(System.in);

        try {
            num = new Integer(in.readLine());
            i = num.intValue(); // pega o valor do Integer
            return i;
        } catch (Exception e) {
            return -1;
        }

    }

    public static double readDouble() {
        double i;
        Double num;

        DataInputStream in = new DataInputStream(System.in);

        try {
            num = new Double(in.readLine());
            i = num.floatValue(); // pega o valor do Integer
            return i;
        } catch (Exception e) {
            return -1;
        }

    }

    public static String readString() {
        int i;
        String str = null;
        DataInputStream in = new DataInputStream(System.in);
        try {
            str = in.readLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }

    public static Calendar readDate() {
        int i;
        String str = null;
        Calendar date = null;
        DataInputStream in = new DataInputStream(System.in);
        try {
            str = in.readLine();
            date = new GregorianCalendar();
            date.setTime(new Date(str));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return date;
    }

    public static Calendar readDate(String str) {
        int i;
        Calendar date = null;

        try {
            date = new GregorianCalendar();
            date.setTime(new Date(str));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return date;
    }

    public static String CurrencyConverter(double value) {
        Locale currentLocale = Locale.getDefault();
        Double currency = new Double(value);
        NumberFormat currencyFormatter;
        String currencyOut;
        currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
        currencyOut = currencyFormatter.format(currency);
        return currencyOut;
    }

    public static String CurrencyConverter(Double value) {
        Locale currentLocale = Locale.getDefault();
        NumberFormat currencyFormatter;
        String currencyOut;
        currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
        currencyOut = currencyFormatter.format(value);
        return currencyOut;
    }

}
