/* Purpose: find and count frequent word in a string
 * Author: Tzu-Chi Kuo @ Santa Clara University
 * Student ID: W1279858
 * Version: 0.0
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DescendSort {

	private HashMap<String, Integer> descendMap;
	
	// Constructor
	public DescendSort(HashMap<String, Integer> map) {
		descendMap = map;
	}
	
	// Using Collection sorting and return the result
	public List<Map.Entry<String, Integer>> resultOfSort() {
		List<Map.Entry<String, Integer>> list =
				new ArrayList<Map.Entry<String, Integer>>(descendMap.entrySet());
		// descending sort
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
				return entry2.getValue() - entry1.getValue();
			}
		});
		
		// for debugging
		System.out.println("*****************************");
		System.out.println("* Intermediate Sorting      *");
		System.out.println("* (only display <5 results) *");
		System.out.println("* Display format :          *");
		System.out.println("* freq. word = # of word    *");
		System.out.println("*****************************");
		int rloop = list.size();
		if (rloop > 5) rloop = 5;
		for (int i = 0; i < rloop; i++) {
		//for (Map.Entry<String, Integer> entry: list){
		//	System.out.println(entry.getKey()+" = "+descendMap.get(entry.getKey()));
			System.out.println(list.get(i).getKey()+" = "+list.get(i).getValue());
		}
		System.out.println("");
		return list;
	}

}
