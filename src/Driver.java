import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import model.PerfectNumber;

public class Driver {
	
	
	private static final int REPEAT = 25;
	private static final int MAX_THREADS = 12;
	private static final long MAXVALUE = 3000000000L;
	
	
	public static void main(String[] args) {
		
		System.out.println("Perfect Numbers up to 3 billion: " + PerfectNumber.getPerfectNumbers(4, MAXVALUE) + "\n");
		
		System.out.println("Finding optimal thread count...");
		HashMap<Integer, Long> map = new HashMap<Integer, Long>();
		for (int i = 1; i <= MAX_THREADS; i++) {
			long average = averageTimeThorough(i);
			map.put(i, average);
			System.out.println("Average for " + i + " threads: " + average + " miliseconds");
		}
		
		long quickestTime = Collections.min(map.values());
		ArrayList<Integer> quickestThreadCount = getKeysWithValue(map, quickestTime);

		System.out.println("Fastest calculation was " + quickestTime + " miliseconds with " + quickestThreadCount + " threads");
	}
	
	
	private static ArrayList<Integer> getKeysWithValue(HashMap<Integer, Long> map, Long value) {
		ArrayList<Integer> matchedKeys = new ArrayList<Integer>();
		for (Integer current : map.keySet()) {
			if (map.get(current).equals(value)) {
				matchedKeys.add(current);
			}
		}
		return matchedKeys;
	}
	
	
	private static long averageTimeThorough(int threads) {
		long average = 0;
		for (int i = 0; i<REPEAT; i++) {
			average += averageTime(threads);
		}
		average /= REPEAT;
		return average;
	}
	
	
	private static long averageTime(int threads) {
		long startTime = System.currentTimeMillis();
		PerfectNumber.getPerfectNumbers(threads, MAXVALUE);
		long endTime = System.currentTimeMillis();
		long time = endTime - startTime;
		return time;
	}

}
