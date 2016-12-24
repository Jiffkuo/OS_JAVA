/*
 * Purpose:
 *   Implement Aging algorithm, using HashMap to simulate page table
 * Author: Tzu-Chi Kuo @ Santa Clara University
 * Student ID: W1279858
 * Version: 0.0
 * Assignment: HW4
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Aging{
	// for store memory references data
	ArrayList<Integer> intList = new ArrayList<Integer>();

	// initialize aging algorithm: read memory references
	public Aging(String fileName) throws IOException {
		FileReader fReader = new FileReader(fileName);
		BufferedReader bReader = new BufferedReader(fReader);
		String s = null;
		// read file and store data to an Array List
		while ((s = bReader.readLine()) != null){
			intList.add(Integer.valueOf(s));
		}
		fReader.close();
		
		// debug purpose
		/*
		for (Integer i : intList) {
			System.out.println(i);
		}
		*/
		System.out.println("Total number of memory references data is "+intList.size());

	}
	
	// Check total number of page fault
	public int CheckPageFault(int numOfPageFrame, int msbBitMask) {
		int numOfPageFault = 0;
		// Key: memory reference data; Value: counter value
		Map<Integer, Integer> agingMap = new HashMap<Integer, Integer>(numOfPageFrame);

		// go through all memory references and update agingMap
		for (int i = 0; i < intList.size(); i++){
			int key = intList.get(i);
			int lowestKey = 0;
			int lowestVal = Integer.MAX_VALUE;
			
			boolean hitPageFault = true;
			// check reference data in the agingMap (page table)
			Iterator iterator = agingMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry)iterator.next();
				int tempKey = (Integer)entry.getKey();
				int tempVal = (Integer)entry.getValue();
				// shift counter right 1 bit
				tempVal = tempVal >>>1;
				// reference data is set
				if (tempKey == key) {
					// set the most-significant bit
					tempVal = tempVal | msbBitMask;
					hitPageFault = false;
				} else {
					// exam the lowest count value of non frequency used page 
					if(tempVal < lowestVal) {
						lowestVal = tempVal;
						lowestKey = tempKey;
					}
				}
				agingMap.put(tempKey, tempKey);
			}
			// update page fault
			if (hitPageFault) {
				// replace the lowest count page with current memory data
				if(agingMap.containsKey(lowestKey) && agingMap.size() > numOfPageFrame) {
					agingMap.remove(lowestKey);
				}
				agingMap.put(key, 0);
				numOfPageFault++;
			}
		}
		return numOfPageFault;
	}
}
