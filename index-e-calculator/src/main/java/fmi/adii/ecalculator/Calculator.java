package fmi.adii.ecalculator;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.apfloat.Apint;
import org.apfloat.ApintMath;

public class Calculator {

	private static final int PRECISION = 100;

	public static void main(String[] args) {

		Apfloat e = calculate(2000);
		System.out.println("e ~ " + e.toString());

	}

	private static Apfloat calculate(int k) {

		long start = System.currentTimeMillis();

		Apfloat sum = new Apfloat(0, PRECISION);

		for (int i = 0; i <= k; i++) {
			sum = sum.add(calucalateOne(i));
		}

		long end = System.currentTimeMillis();

		System.out.println(end - start);
		return sum;
	}

	private static Apfloat calucalateOne(int i) {

		Apfloat apfloatI = new Apfloat(i);

		Apfloat nominator = new Apfloat(3).subtract(ApfloatMath
				.pow(apfloatI, 2).multiply(new Apfloat(4)));

		Apint factorial = ApintMath.factorial(2 * i + 1);

		return nominator
				.divide(new Apfloat(factorial.toBigInteger(), PRECISION));
	}
}