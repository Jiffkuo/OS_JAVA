/*
 * Purpose:
 *  Main entry
 *  Write a program that simulates a paging system using the aging algorithm.
 *  The number of page frames is a parameter.
 *  The sequence of page references should be read from a file.
 *  For a given input file, plot the number of page faults per 1000 memory references
 *  as a function of the number of page frames available.
 * Author: Tzu-Chi Kuo @ Santa Clara University
 * Student ID: W1279858
 * Version: 0.0
 * Assignment: HW4
 * 
 */

import java.io.IOException;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Solution {
	public static void main(String[] args) throws IOException {
		// generate total number of memory references
		int totalOfMemoryData = 10000;
		int perMemoryData = 1000;
		int pageRatio = totalOfMemoryData/perMemoryData;
		// generate random reference data
		int randomDataRange = 50;
		// number of page frames
		int numOfPageFrames = 10;
		// msbBitMask (= aging counter size): 1, 2, 4, 8, ...
		int msbBitMask = 8;
		// file name
		String fName = "random_mem.txt";
		
		// create random memory reference data text file
		//new Random_mem(fName, totalOfMemoryData, randomDataRange);
		// initialize aging algorithm
		Aging aging = new Aging(fName);
		// initialize a frame to plot the graph
		JFrame jFrame = new JFrame();
		jFrame.setSize(1024, 768);
		int[] plotPageFault = new int[numOfPageFrames];
		String[] plotPageFrames = new String[numOfPageFrames];
		
		System.out.println("Memory data range = 1 ~ "+randomDataRange);
		System.out.println("========================================================================");
		System.out.println("[Number of page frames, number of Page fault (/1000), Total page fault]");
		// count page fault and print result
		for (int i = 1; i <= numOfPageFrames; i++) {
			int cntPageFault = aging.CheckPageFault(i, msbBitMask);
			// record data to the graph
			plotPageFault[i-1] = cntPageFault/pageRatio;
			plotPageFrames[i-1] = "#PageFrames= "+ Integer.toString(i);
			System.out.printf("[%2d", i);
			System.out.println(", "+cntPageFault/pageRatio+", "+cntPageFault+"]");
		}
		// plot the result in the graph
		jFrame.getContentPane().add(new PlotBarChart(plotPageFault, plotPageFrames, "#PageFault(/1000) vs. #PageFrames"));
		// create a Window for the graph
		WindowListener wndCloser = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		jFrame.addWindowListener(wndCloser);
		jFrame.setVisible(true);
	}
}
