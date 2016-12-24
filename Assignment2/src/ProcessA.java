/* 
 * Purpose: ProcessA: read a file data and save one line of file to Buffer1
 * Author: Tzu-Chi Kuo @ Santa Clara University
 * Student ID: W1279858
 * Version: 0.0
 * Assignment: HW2
 */

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;

public class ProcessA extends main implements Runnable {
	
	public void run(){
		FileReader fr;
		BufferedReader br = null;
		String oneline = null;
		try {
			fr = new FileReader("./input.txt");
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			try {
				// read the file line by line
				oneline = br.readLine();
				while(oneline != null) {
					// get A signal for ProcessA
					Empty1.acquire();
					System.out.println("[ProcessA] is enabled!");
					// since readLine will remove \n
					// we need to check empty line in the string
					if (!oneline.trim().equals("")) {
						StringBuilder sb = new StringBuilder();
						sb.append(oneline);
						sb.append("\n");
						// copy data to string Buffer1
						Buffer1 = sb.toString();
						// wake up ProcessB
						Full1.release();
					}
					oneline = br.readLine();
				}
				// all data is read and terminate the processes
	    		Finish = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
	    	try {
	    		br.close();
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
		}
	}

}
