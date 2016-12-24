/*
 * Purpose: Banker's algorithm
 *     Input: Available and resource allocation array table
 *     Output: A log file includes each request and decision
 * Author: Tzu-Chi Kuo @ Santa Clara University
 * Student ID: W1279858
 * Version: 0.0
 * Assignment: HW3
 */

import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;

public class banker {
	
	public banker (int[] clients, int avail, PrintWriter fWriter) {
		boolean status = true;
		int allResource = avail;
		int index = 0;

		System.out.println("Available Resource: "+avail);
		System.out.println("Input: Id [has,  max]");
		fWriter.println("Available Resource: "+avail);
		fWriter.println("Input: Id [has,  max]");

		// covert input array to vector
		List<TreeNode> result = new ArrayList<TreeNode>();
		Vector<TreeNode> vector = new Vector<TreeNode>();
		for (int i = 0; i < clients.length-1; i+=2) {
			System.out.println("       "+index+" ["+clients[i]+", "+clients[i+1]+"]");
			fWriter.println("       "+index+" ["+clients[i]+", "+clients[i+1]+"]");
			vector.addElement(new TreeNode(index, clients[i], clients[i+1]));
			index++;
		}
		index = 0;
		
		// cycle through each client to evaluate safe or unsafe
		while(!vector.isEmpty()) {
			TreeNode cur = new TreeNode(0, 0 ,0);
			cur = vector.get(index);
			// Output request
			System.out.print("Request Client: (Id:"+cur._id+")");
			System.out.println("["+cur._has+", "+cur._max+"]");
			fWriter.print("Request Client: (Id:"+cur._id+")");
			fWriter.println("["+cur._has+", "+cur._max+"]");
			
			// evaluate if resource can meet max value
			if (cur._has + allResource >= cur._max) {
				allResource = allResource + cur._has;
				vector.remove(index);
				result.add(cur);
				index = 0;
			} else {
				index++;
				System.out.println(" ! Pending request ==> (Id:"+cur._id+")");
				fWriter.println(" ! Pending request ==> (Id:"+cur._id+")");
				// no client can be satisfied
				if (index == vector.size()) {
					status = false;
					break;
				}
			}
			System.out.println("Available Resource: "+allResource);
			fWriter.println("Available Resource: "+allResource);
		}
		
		// Output decision and state
		if (status) {
			System.out.println("\nSafe state!");
			System.out.print("Decision path is (Id) ");
			fWriter.println("\nSafe state!");
			fWriter.print("Decision path is (Id) ");
			for(TreeNode n : result) {
				System.out.print(n._id+" ");
				fWriter.print(n._id+" ");
			}
		}else {
			System.out.println("\nUnsafe state\n");
			fWriter.println("\nUnsafe state\n");
		}
	}

	// define a class to record each client information
	private class TreeNode {
		int _id;
		int _has;
		int _max;
		
		public TreeNode(int id, int has, int max) {
			_id = id;
			_has = has;
			_max = max;
		}
	}
}
