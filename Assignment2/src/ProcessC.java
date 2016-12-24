/* 
 * Purpose: ProcessC: take the data from Buffer2 and print it
 * Author: Tzu-Chi Kuo @ Santa Clara University
 * Student ID: W1279858
 * Version: 0.0
 * Assignment: HW2
 */

public class ProcessC extends main implements Runnable {

	public void run() {
		while(true) {
			try {
				// get a signal for ProcessC
				Full2.acquire();
				System.out.println("[ProcessC] is enabled!");
				// After synchronized then Print the content of buffer2
				synchronized(Buffer2) {
					System.out.println(Buffer2);
				}
				// wake up ProcessA
				Empty1.release();
				// wake up processB
				Empty2.release();
				// check if finishing the process and break the while loop
				if (Finish) break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}