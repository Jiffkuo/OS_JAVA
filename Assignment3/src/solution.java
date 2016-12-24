/* 
 * Purpose: 
 *    Program a simulation of the banker¡¦s algorithm.
 *    Your program should cycle through each of the bank clients
 *    asking for a request and evaluating whether it is safe or unsafe.
 *    Output a log of requests and decisions to a file.
 *    main entry function
 * Author: Tzu-Chi Kuo @ Santa Clara University
 * Student ID: W1279858
 * Version: 0.0
 * Assignment: HW3
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class solution {

	public static void main(String[] args) {
		int [] input = {
				3, 9,
				2, 4,
				1, 5,
				2, 7,
				5, 15
		};
		int avail = 0;
		PrintWriter fWriter = null;
		try {
			fWriter = new PrintWriter("output.log");
			System.out.println("=============================");
			fWriter.println("=============================");
			avail = 1;
			new banker(input, avail, fWriter);
			System.out.println("=============================");
			fWriter.println("=============================");
			avail = 2;
			new banker(input, avail, fWriter);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (fWriter != null) {
				fWriter.close();
			}
		}
	}

}
