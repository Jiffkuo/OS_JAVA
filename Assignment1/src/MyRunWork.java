/* Purpose: create customer work for multi-thread usage
 * Author: Tzu-Chi Kuo @ Santa Clara University
 * Student ID: W1279858
 * Version: 0.0
 */

import java.util.List;
import java.util.Map;

public class MyRunWork implements Runnable {

	private int tID;
	private String myRunString;
	public List<Map.Entry<String, Integer>> myRunList;
	
	// Constructor
	public MyRunWork(String s, int id) {
		myRunString = s;
		tID = id;
	}
	
	// Result intermediate sorting result
	public List<Map.Entry<String, Integer>> MyRunResultList() {
		return myRunList;
	}
	
	// Override
	public void run() {
		// for debugging
		System.out.println("<<Thread"+tID+" is running>>");
		// intermediate sorting
		FreqWordCount fwc = new FreqWordCount(myRunString);
		myRunList = fwc.findFreqWord();

		// for debugging
		System.out.println("<<Thread"+tID+" is exiting>>");
	}
}
