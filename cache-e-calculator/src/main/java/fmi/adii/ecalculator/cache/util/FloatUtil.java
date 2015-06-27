package fmi.adii.ecalculator.cache.util;

import org.apfloat.Apfloat;

public class FloatUtil {

	private static final FloatUtil INSTANCE = new FloatUtil();

	private FloatUtil() {
	}

	public static FloatUtil getInstance() {
		return INSTANCE;
	}

	public Apfloat round(Apfloat e, int precision) {
		char[] value = e.toString().toCharArray();

		if (isRounded(precision, value)) {
			return e;
		}

		roundValue(precision, value);

		return new Apfloat(new String(value));
	}

	public String getPrecise(Apfloat e, int precision) {
		StringBuilder sb = new StringBuilder(e.toString());

		if (precision == 1) {
			return sb.toString();
		}

		while (sb.length() < precision + 1) {
			sb.append('0');
		}

		sb.setLength(precision + 1);
		return sb.toString();
	}

	private void roundValue(int precision, char[] value) {
		for (int i = precision + 1; i < value.length; i++) {
			value[i] = '0';
		}

		int currentIdex = precision;
		while (value[currentIdex] == '9' || value[currentIdex] == '.') {

			if (value[currentIdex] == '9') {
				value[currentIdex] = '0';
			}

			currentIdex--;
		}

		value[currentIdex]++;
	}

	private boolean isRounded(int precision, char[] value) {
		return isRoundedWithLessPrecision(precision, value) || isRoundedToPrecision(precision, value);
	}

	private boolean isRoundedWithLessPrecision(int precision, char[] value) {
		return value.length < precision + 2;
	}

	private boolean isRoundedToPrecision(int precision, char[] value) {
		return value[precision + 1] < '5';
	}

}
