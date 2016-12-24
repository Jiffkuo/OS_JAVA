/* 
 * Purpose: ProcessB: copy the data from Buffer1 to Buffer2
 * Author: Tzu-Chi Kuo @ Santa Clara University
 * Student ID: W1279858
 * Version: 0.0
 * Assignment: HW2
 */

public class ProcessB extends main implements Runnable {

	public void run() {
		while(true) {
			try {
				// get signals for ProcessB
				Full1.acquire();
				Empty2.acquire();
				System.out.println("[ProcessB] is enabled!");
				// After synchronized then copy Buffer1 to Buffer2
				synchronized(Buffer1) {
					Buffer2 = Buffer1;
				}
				// wake up ProcessC	
				Full2.release();
				// check if finishing the process and break the while loop
				if (Finish) break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
