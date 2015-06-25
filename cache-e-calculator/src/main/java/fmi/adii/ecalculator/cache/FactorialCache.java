package fmi.adii.ecalculator.cache;

import java.util.Map.Entry;
import java.util.TreeMap;

import org.apfloat.Apint;

public class FactorialCache {

	private TreeMap<Integer, Apint> cache = new TreeMap<Integer, Apint>();

	public FactorialCache() {
		cache.put(0, Apint.ONE);
	}

	public synchronized Entry<Integer, Apint> getHighestFactorial(int upperBound) {
		return cache.lowerEntry(upperBound);
	}

	public synchronized void addFactorial(int key, Apint value) {
		cache.put(key, value);
		int keyToDelete = key - 2;
		while (cache.remove(keyToDelete -= 2) != null);
	}

}
