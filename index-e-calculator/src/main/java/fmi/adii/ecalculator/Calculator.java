package fmi.adii.ecalculator;

import org.apfloat.Apfloat;
import org.apfloat.Apint;

public class Calculator {

    private static final int PRECISION = 100;

    public static void main(String[] args) {

        Apfloat e = calculate(1000);
        System.out.println("e ~ " + e.toString());
    }

    private static Apfloat calculate(int k) {

        long start = System.currentTimeMillis();

        Apfloat sum = new Apfloat(3, PRECISION);
        Apint factorial = new Apint(1);
        for (int i = 1; i <= k; i++) {
            Apfloat nominator = new Apfloat(3 - (4 * i * i));
            factorial = factorial.multiply(new Apint(2 * i * (2 * i + 1)));
            sum = sum.add(nominator.divide(new Apfloat(factorial.toBigInteger(), PRECISION)));
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);

        return sum;
    }

}