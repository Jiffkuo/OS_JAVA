/* Purpose: find and count frequent word in a string
 * Author: Tzu-Chi Kuo @ Santa Clara University
 * Student ID: W1279858
 * Version: 0.0
 */

import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class FreqWordCount {

	public String[] stLine;
	public String freqWord;
	public int freqCount;
	private HashMap<String, Integer> freqMap = new HashMap<String, Integer>();
	
	// Constructor
	public FreqWordCount(String s) {
		/* split all special symbols */
		stLine = s.trim().split("[\\s\\.\\;\\,\\?\\'/\"\\*\\_\\-\\t\\:\\(\\)]+");
	}
	
	// find intermediate sorting result
	public List<Map.Entry<String, Integer>> findFreqWord() {
		/* loop array and put each element into HashMap */
		for (int i = 0; i < stLine.length; i++) {
			freqWord = stLine[i];
			if (freqMap.get(freqWord) == null) {
				freqMap.put(freqWord, 1);
			}else {
				freqCount = freqMap.get(freqWord);
				freqMap.put(freqWord, freqCount+1);
			}
		}

		// for debugging
		/*
		System.out.println("In HashMap:");
		for (String key: freqMap.keySet()){
			System.out.println(key+" = "+freqMap.get(key));
		}
		*/

		// sorting HashMap
		DescendSort desort = new DescendSort(freqMap);
		return desort.resultOfSort();
	}
}
