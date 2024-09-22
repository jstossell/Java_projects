import java.util.Scanner;
import java.io.*;
/*
 * PROJECT I: Project1.java
 *
 * As in project 0, this file - and the others you downloaded - form a
 * template which should be modified to be fully functional.
 *
 * This file is the *last* file you should implement, as it depends on both
 * Point and Circle. Thus your tasks are to:
 *
 * 1) Make sure you have carefully read the project formulation. It contains
 *    the descriptions of all of the functions and variables below.
 * 2) Write the class Point.
 * 3) Write the class Circle
 * 4) Write this class, Project1. The Results() method will perform the tasks
 *    laid out in the project formulation.
 */
public class Project1 {
	// -----------------------------------------------------------------------
	// Do not modify the names of the variables below! This is where you will
	// store the results generated in the Results() function.
	// -----------------------------------------------------------------------
	public int    circleCounter; // Number of non-singular circles in the file.
	public int    posFirstLast;  // Indicates whether the first and last
	// circles overlap or not.
	public double maxArea;       // Area of the largest circle (by area).
	public double minArea;       // Area of the smallest circle (by area).
	public double averageArea;   // Average area of the circles.
	public double stdArea;       // Standard deviation of area of the circles.
	public double medArea;       // Median of the area.
	public int    stamp=472143;
	// -----------------------------------------------------------------------
	// You may implement - but *not* change the names or parameters of - the
	// functions below.
	// -----------------------------------------------------------------------

	/**
	 * Default constructor for Project1. You should leave it empty.
	 */
	public Project1() {
		// This method is complete.
	}

	/**
	 * Results function. It should open the file called FileName (using
	 * Scanner), and from it generate the statistics outlined in the project
	 * formulation. These are then placed in the instance variables above.
	 *
	 * @param fileName  The name of the file containing the circle data.
	 */
	public void results(String fileName){
		double x,y,rad;
		int lineCount = 0;
		int nonSing = 0;
		double  maxRad = 0;
		double minRad = 1000000000;

		try{
			Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)));

			while(scanner.hasNext()) {

				x = scanner.nextDouble();
				y = scanner.nextDouble();
				rad = scanner.nextDouble();

				if (rad!= 0) {
					nonSing++;
				}
				if (rad > maxRad) {
					maxRad = rad;
					maxArea = Math.PI*maxRad*maxRad;
				}
				if (rad < minRad) {
					minRad = rad;
					minArea = Math.PI*minRad*minRad;
				}
				lineCount++;
			}
		} catch(Exception e) {
			System.err.println("An error has occured. See below for details");
			e.printStackTrace();
		}

		int i = 0;
		Circle [] circle = new Circle[lineCount];
		try{
			Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)));

			while(scanner.hasNext()) {

				x = scanner.nextDouble();
				y = scanner.nextDouble();
				rad = scanner.nextDouble();
				circle[i] = new Circle(x, y, rad);
				i++;
			}
		}
		catch(Exception e) {
			System.err.println("An error has occured. See below for details");
			e.printStackTrace();
		}


		circleCounter = nonSing;
		posFirstLast = circle[0].overlap(circle[lineCount-1]);
		averageArea = averageArea(circle);
		stdArea = areaStandardDeviation(circle);
		medArea = median(circle);

		/**
		System.out.println("Information about the data:");
		System.out.println("  Number of non singular circles: " + circleCounter);
		System.out.println("  Maximum Area: " + maxArea);
		System.out.println("  Minimum Area: " + minArea);
		System.out.println("  Median Area: " + median(circle));
		System.out.println("  Average Area: " + averageArea(circle));
		System.out.println("  Standard deviation of Area: " + areaStandardDeviation(circle));
		System.out.println(" The number of the statement 'the first and last circle overlap is' " +posFirstLast);
		*/


		// You need to fill in this method

	}
	public double median(Circle[] circles){
		double[] circlesArea = new double[circles.length];
		
		   for (int i = 0; i < circles.length; i++) {
		   circlesArea[i] = circles[i].area();   
		   }
		 bubbleSort(circlesArea);
		
		   if (circles.length % 2==1) {
		   medArea = circlesArea[circles.length-1/2];
		   }
		   else { medArea = (circlesArea[(circles.length)/2]/2 + circlesArea[(circles.length)/2-1]/2);
		   }
		return medArea;
	}

	public void bubbleSort(double[] arr)
       	{
		boolean doneSwap = true;
		while (doneSwap)
	       	{
			doneSwap = false;
			for (int i = 0; i < arr.length-1; i++) 
			{
				if (arr[i+1] < arr[i]) { double tmp = arr[i+1]; }
			}
		}
	}

	/**
	 * A function to calculate the avarage area of circles in the array provided.
	 *
	 * @param circles  An array if Circles
	 */
	public double averageArea(Circle[] circles) {
		// You need to fill in this method and remove the return 0.0 statement.
		double[] circlesArea = new double[circles.length]; 
		for (int i = 0; i < circles.length; i++) {
			circlesArea[i] = circles[i].area();
		}
		double total = 0;
		for(int i=0; i<(circles.length); i++){
			total = total + circlesArea[i];
		}
		averageArea = total/(circles.length);
		return averageArea;
	}




	/**
	 * A function to calculate the standart deviation of areas in the circles in the array provided.
	 *
	 * @param circles  An array of Circles
	 */
	public double areaStandardDeviation(Circle[] circles) {
		// You need to fill in this method and remove the return 0.0 statement.
		double stdDeviationSum = 0;
		double areaStandardDeviation=0;
		double[] circlesArea = new double[circles.length];
		for (int i = 0; i < circles.length; i++) {
			circlesArea[i] = circles[i].area();
		}
		for (int i=0; i<(circles.length);i++){
			stdDeviationSum = stdDeviationSum + Math.pow(circlesArea[i] - averageArea(circles), 2);
			areaStandardDeviation = Math.sqrt(stdDeviationSum/(circles.length));
		}
		return areaStandardDeviation;
	}


	// =======================================================
	// Tester - tests methods defined in this class
	// =======================================================

	/**
	 * Your tester function should go here (see week 14 lecture notes if
	 * you're confused). It is not tested by BOSS, but you will receive extra
	 * credit if it is implemented in a sensible fashion.
	 */
	public static void main(String args[]){
		// You need to fill in this method.
		Project1 p = new Project1();
		p.results("Project1.data");
	}
}
