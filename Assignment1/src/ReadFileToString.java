/* Purpose: This class is used to read a text file and translate to String
 * Author: Tzu-Chi Kuo @ Santa Clara University
 * Student ID: W1279858
 * Version: 0.0
 */

import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class ReadFileToString {
	
	private String fileName;
	
	// Constructor
	public ReadFileToString(String f) {
		fileName = f;
	}

	// Convert one line of text to String one by one
	public ArrayList<String> lineToString() throws IOException {
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> sl = new ArrayList<String>();
		try {
			// read the file line by line
			String oneline = br.readLine();
			while(oneline != null) {
				// since readLine will remove \n, we need to check empty line in the string
				if (!oneline.trim().equals("")) {
					StringBuilder sb = new StringBuilder();
					sb.append(oneline);
					sb.append("\n");
					sl.add(sb.toString());
				}
				oneline = br.readLine();
			}
		} finally {
			br.close();
		}
		return sl;
	}
}
