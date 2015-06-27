package fmi.adii.ecalculator.cache.output;

import org.apfloat.Apfloat;

public interface CalculatorOutput {

	public abstract void outputPrecision();

	public abstract void output(Apfloat e, long time);

	public abstract void outputThreadTime(long time);

}