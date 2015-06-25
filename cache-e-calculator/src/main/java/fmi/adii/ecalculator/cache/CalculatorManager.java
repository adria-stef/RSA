package fmi.adii.ecalculator.cache;

import java.util.concurrent.atomic.AtomicInteger;

public class CalculatorManager {

	private AtomicInteger nextIndex = new AtomicInteger(-1);

	public int getNextIndex() {
		return nextIndex.incrementAndGet();
	}

}
