/*
 * Purpose:
 *   Generate a sequence of page reference data randomly to be input file
 * Author: Tzu-Chi Kuo @ Santa Clara University
 * Student ID: W1279858
 * Version: 0.0
 * Assignment: HW4
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Random_mem {
	public Random_mem(String fileName, int numOfRandom, int rangeOfRandom) throws IOException {
		FileWriter fWriter = new FileWriter(fileName);
		Random rand = new Random();
		
		// generate total numOfRandom set reference data
		for (int i = 0; i < numOfRandom; i++) {
			// generate 1 ~ rangeOfRandom random integer value
			int val = rand.nextInt(rangeOfRandom) + 1;
			fWriter.write(String.valueOf(val)+"\n");
		}
		fWriter.close();
	}
}
