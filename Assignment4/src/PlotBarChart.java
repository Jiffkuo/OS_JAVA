/*
 * Purpose:
 *  Plot the result to a bar chart graph
 * Author: Tzu-Chi Kuo @ Santa Clara University
 * Student ID: W1279858
 * Version: 0.0
 * Assignment: HW4
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PlotBarChart extends JPanel {
	private int[]     values;
	private String[]  names;
	private String    title;

	// Construct: initialization
	public PlotBarChart(int[] y_axis, String[] x_axis, String titl) {
		values = y_axis;
		names = x_axis;
		title = titl;
	}
	
	@Override
	public void paintComponent(Graphics graph) {
		super.paintComponent(graph);
		if (values == null || values.length == 0)
			return;
		
		// get the frame information: width and height
	    Dimension d = getSize();
	    int clientWidth = d.width;
	    int clientHeight = d.height;
	    // get bar width to plot
	    int barWidth = clientWidth / values.length;

	    // set font type for title and label
	    Font titleFont = new Font("Dialog", Font.BOLD, 15);
	    FontMetrics titleFontMetrics = graph.getFontMetrics(titleFont);
	    Font labelFont = new Font("Monospaced", Font.PLAIN, 12);
	    FontMetrics labelFontMetrics = graph.getFontMetrics(labelFont);

	    // plot title in the frame
	    int titleWidth = titleFontMetrics.stringWidth(title);
	    int yAxis = titleFontMetrics.getAscent();
	    int xAxis = (clientWidth - titleWidth) / 2;
	    graph.setFont(titleFont);
	    graph.drawString(title, xAxis, yAxis);

	    // figure out a max and min value to be reference: x, y-axis position
		int minValue = 0;
		int maxValue = 0;
		for (int i = 0; i < values.length; i++) {
			if (minValue > values[i]) {
				minValue = values[i];
			}
			if (maxValue < values[i]) {
				maxValue = values[i];
			}
		}
	    if (maxValue == minValue) {
	    	return;
	    }

	    // get top and bottom position
	    int tPos = titleFontMetrics.getHeight();
	    int bPos = labelFontMetrics.getHeight();
	    // get scaler factor in order to plot the result in the frame
	    double scale = (double)(clientHeight - tPos - bPos) / (maxValue - minValue);
	    yAxis = clientHeight - labelFontMetrics.getDescent();

	    // start to plot bar chart
	    graph.setFont(labelFont);
	    for (int i = 0; i < values.length; i++) {
	      int valueX = i * barWidth + 1;
	      int valueY = tPos;
	      int height = (int)(values[i] * scale);
	      // define plot position
	      if (values[i] >= 0)
	        valueY += (int)((maxValue - values[i]) * scale);
	      else {
	        valueY += (int)(maxValue * scale);
	        height = -height;
	      }
	      // set the bar chart with blue green
	      graph.setColor(Color.green);
	      // plot the bar chart with rectangle and fill the color
	      graph.fillRect(valueX, valueY, barWidth - 10, height);
	      // set the frame of rectangle with black color
	      graph.setColor(Color.black);
	      // plot the frame
	      graph.drawRect(valueX, valueY, barWidth - 10, height);
	      // attach the string in the graph
	      String result = "#PageFault=" + String.valueOf(values[i]);
	      graph.drawString(result, valueX, valueY);
	      // attach label in the graph
	      int labelWidth = labelFontMetrics.stringWidth(names[i]);
	      xAxis = i * barWidth + (barWidth - labelWidth) / 2;
	      graph.drawString(names[i], xAxis, yAxis);
	    }
	}
}
