import java.util.*;
import java.lang.*;
import java.io.*;

public class ActivitySelection {

	// Prints a maximum set of activities that can be done by a single
    // person, one at a time.
    //  n   -->  Total number of activities
    //  s[] -->  An array that contains start time of all activities
    //  f[] -->  An array that contains finish time of all activities
    public static void printMaxActivities(int s[], int f[], int n)
    {
    	int i, j;
    	System.out.print("Following activities are selected : \n");
    	for (int k = 0; k < n; k++)
    	{
    		i = k;
    		System.out.print(i+1+" ");
    		// Consider rest of the activities
    		for (j = 1; j < n; j++)
    		{
    			// If this activity has start time greater than or
    			// equal to the finish time of previously selected
    			// activity, then select it
    			if (s[j] >= f[i])
    			{
    				System.out.print(j+1+" ");
    				i = j;
    			}
    		}
    		System.out.println();
    	}
    }
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int s[] =  {1, 2, 4, 1, 5,  8,  9, 11, 13};
		int f[] =  {3, 5, 7, 8, 9, 10, 11, 14, 16};
		int n = s.length;
		printMaxActivities(s, f, n);
	}

}
