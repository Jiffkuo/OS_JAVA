/* 
 * Purpose: main and entry function
 * Author: Tzu-Chi Kuo @ Santa Clara University
 * Student ID: W1279858
 * Version: 0.0
 * Assignment: HW2
 */

import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class main {

	// semaphore Empty1 for ProcessA, Full1 and Empty2 for ProcessB, Full2 for ProcessC
	public static final Semaphore Empty1 = new Semaphore(1);
	public static final Semaphore Empty2 = new Semaphore(1);	
	public static final Semaphore Full1 = new Semaphore(0);
	public static final Semaphore Full2 = new Semaphore(0);
	// data buffer
	public static String Buffer1 = new String();
	public static String Buffer2 = new String();
	// flag to terminate processB and processC
	public boolean Finish = false;
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new ProcessA()).start();
		new Thread(new ProcessB()).start();
		new Thread(new ProcessC()).start();
		Thread.sleep(100);
		System.out.println("Processes finished!!");
		System.exit(0);
	}

}
