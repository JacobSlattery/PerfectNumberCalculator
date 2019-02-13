package model;

import java.util.ArrayList;

public class PerfectNumber {

	private static Thread[] threads;
	private static Object key = new Object();
	
	
	public static ArrayList<Long> getPerfectNumbers(int threadCount, long maxValue) {
		ArrayList<Long> perfectNumbers = new ArrayList<Long>();
		ArrayList<Long> potentials = potentialPerfectNumbers(maxValue);
		threads = new Thread[threadCount];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = createThread(perfectNumbers, potentials);
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
		
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return perfectNumbers;
	}

	private static Thread createThread(ArrayList<Long> perfectNumbers, ArrayList<Long> potentials) {
		Runnable runnable = () -> {
			Long checkNumber = getNextPotential(potentials);
			while (checkNumber != null) {
				long sum = 0;
				for (long i = 1; i <= checkNumber; i++) {
					if (checkNumber % i == 0)
						sum = sum + i;
				}

				if (checkNumber * 2 == sum) {
					synchronized (key) {
						perfectNumbers.add(checkNumber);
					}
					sum = 0;
				}
				checkNumber = getNextPotential(potentials);
				
			}
			
		};
		
		return new Thread(runnable);
	}
	
	private static ArrayList<Long> potentialPerfectNumbers(long maxValue) {
		int n = 2;
		long value = 0L;
		ArrayList<Long> values = new ArrayList<Long>();
		while (value <= maxValue) {
			value = (long) (Math.pow(2.0, n-1.0) * (Math.pow(2.0, n)-1.0));
			if (value <= maxValue) {
				values.add(value);
				n++;
			}
		}
		
		return values;
	}
	
	
	private synchronized static Long getNextPotential(ArrayList<Long> potentials) {
		Long potential = null;
		if (!potentials.isEmpty()) {
			potential = potentials.remove(0);
		}
		return potential;
	}
	
	
}






