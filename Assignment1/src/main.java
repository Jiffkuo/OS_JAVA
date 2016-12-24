/* Purpose: main and entry function
 * Author: Tzu-Chi Kuo @ Santa Clara University
 * Student ID: W1279858
 * Version: 0.0
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class main {
	
	// Constructor
	public main() {
		// Do nothing
	}
	
	// main function
	public static void main(String[] args) {
		try {
			// Read file - ReadFileToString.java
			// Another test file - hawthorne.txt
			System.out.println("Read and parse txt file");
			ReadFileToString fl = new ReadFileToString("./input.txt");
			ArrayList<String> slist = fl.lineToString();
			int numOfThread = 0;
			System.out.println("The number of paragraph = the number of Thread to be created = "+slist.size());

			// Create and start multiple threads - MyRunWork.java
			System.out.println("Creating thread and sorting");
			System.out.println("....");
			ArrayList<Thread> threadList = new ArrayList<Thread>();
			ArrayList<MyRunWork> workList = new ArrayList<MyRunWork>();
			for (final String s: slist) {
				MyRunWork myrun = new MyRunWork(s, numOfThread);
				Thread mytd = new Thread(myrun);
				mytd.setName(Integer.toString(numOfThread));
				threadList.add(mytd);
				workList.add(myrun);
				// start the thread
				mytd.start();
				numOfThread++;
			}
			
			// Check all threads are finished
			boolean isFinshed = true;
			while(true) {
				for (Thread t : threadList) {
					isFinshed = true;
					if (t.isAlive()){
						isFinshed = false;
					}
				}
				if (isFinshed) break;
			}

			// Collect each thread result to a single List
			final List<Map.Entry<String, Integer>> threadOrder;
			threadOrder = Collections.synchronizedList(new ArrayList<Map.Entry<String, Integer>>());
			for (MyRunWork work : workList) {
				if (work.MyRunResultList() != null) {
					threadOrder.addAll(work.MyRunResultList());
				}
			}

			// Re-organize the List to HashMap
			HashMap<String, Integer> finalMap = new HashMap<String, Integer>();
			String finalWord;
			int finalCount;
			for (Map.Entry<String, Integer> entry : threadOrder){
				finalWord = entry.getKey();
				finalCount = entry.getValue();
				if (finalMap.get(finalWord) == null) {
					finalMap.put(finalWord, finalCount);
				}else {
					finalCount = finalCount + finalMap.get(finalWord);
					finalMap.put(finalWord, finalCount);
				}
				// for debugging
				//System.out.println(entry.getKey()+" = "+entry.getValue());
			}
			// for debugging
			/*
			System.out.println("In Final HashMap:");
			for (String key: finalMap.keySet()){
				System.out.println(key+" = "+finalMap.get(key));
			}
			*/
			
			// Do final descending sort - DescendSort.java
			List<Map.Entry<String, Integer>> finalOrder = new ArrayList<Map.Entry<String, Integer>>(finalMap.entrySet());
			DescendSort finalSort = new DescendSort(finalMap);
			finalOrder = finalSort.resultOfSort();
			System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
			System.out.println("v Final Sorting Result            v");
			System.out.println("v (only display 5 results)        v");
			System.out.println("v Display format :                v");
			System.out.println("v freq. word = # of word          v");
			System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");

			// debugging
			/*
			for (Map.Entry<String, Integer> entry : finalOrder) {
				System.out.println(entry.getKey()+" = "+entry.getValue());
			}
			*/
			for (int i = 0; i < 5; i++){
				System.out.println(finalOrder.get(i).getKey()+" = "+finalOrder.get(i).getValue());
			}

		} catch (IOException ioe) {
			System.out.println("[Error] cannot read the file:"+ioe.getMessage());
		}
	}
}
