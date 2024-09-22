/*
 * PROJECT I: Circle.java
 *
 * This file contains a template for the class Circle. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Point class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Circle {

    /*
     * Here, you should define private variables that represent the radius and
     * centre of this particular Circle. The radius should be of type double,
     * and the centre should be of type Point.
     */

	private Point Center = new Point();
	private double Radius;

    // =========================
    // Constructors
    // =========================
    /**
     * Default constructor - performs no initialization.
     */
    public Circle() {
        // This method is complete.
    }

    /**
     * Alternative constructor, which sets the circle up with x and y
     * co-ordinates representing the centre, and a radius. Remember you should
     * not store these x and y co-ordinates explicitly, but instead create a
     * Point to hold them for you.
     *
     * @param xc   x-coordinate of the centre of the circle
     * @param yc   y-coordinate of the centre of the circle
     * @param rad  radius of the circle
     */
    public Circle(double xc, double yc, double rad) {
        // You need to fill in this method
				this.Center = new Point(xc,yc);
				this.Radius =rad;
    }

    /**
     * Alternative constructor, which sets the circle up with a Point
     * representing the centre, and a radius.
     *
     * @param center  Point representing the centre
     * @param rad     Radius of the circle
     */

    public Circle(Point center, double rad) {
        this.Center = center;
				this.Radius = rad;

    }

    // =========================
    // Setters and Getters
    // =========================

    /**
     * Setter - sets the co-ordinates of the centre.
     *
     * @param xc  new x-coordinate of the centre
     * @param yc  new y-coordinate of the centre
     */
    public void setCenter(double xc, double yc) {
       //fill this in
			 this.Center = new Point(xc,yc);
    }

    /**
     * Setter - sets the centre of the circle to a new Point.
     *
     * @param p  A Point representing the new centre of the circle.
     */
    public void setCenter(Point p){
        this.Center = p;
    }

    /**
     * Setter - change the radius of this circle.
     *
     * @param rad  New radius for the circle.
     */
    public void setRadius(double rad) {
	// fill this in
      this.Radius=rad;
   }

    /**
     * Getter - returns the centre of this circle.
     *
     * @return The centre of this circle (a Point).
     */
    public Point getCenter(){
        // You need to fill in this method.
	return Center;
    }

    /**
     * Getter - extract the radius of this circle.
     *
     * @return The radius of this circle.
     */
    public double getRadius(){
        // You need to fill in this method.
	return Radius;
    }

    // =========================
    // Convertors
    // =========================

    /**
     * Calculates a String representation of the Circle.
     *
     * @return A String of the form: "[Ax,Ay], r=Radius" where Ax and Ay are
     *         numerical values of the coordinates, and Radius is a numerical
     *         value of the radius.
     */
    public String toString() {
        // You need to fill in this method.
 			String Circle = ("[" + Center.getX()  + "," + Center.getY() + "]," + "r=" + Radius);
			return Circle;
    }

    // ==========================
    // Service routines
    // ==========================

    /**
     * Similar to the equals() function in Point. Returns true if two Circles
     * are equal. By this we mean:
     *
     * - They have the same radius (up to tolerance).
     * - They have the same centre (up to tolerance).
     *
     * Remember that the second test is already done in the Point class!
     *
     * @return true if the two circles are equal.
     */
    public boolean equals(Circle c) {
        // You need to fill in this method.
				if (Math.abs(Radius - c.getRadius()) <= Point.GEOMTOL && Center.equals(c.getCenter())) {
					return true;
				}
				else {
					return false;
				}
    }

    // -----------------------------------------------------------------------
    // Do not change the method below! It is essential for marking the
    // project, and you may lose marks if it is changed.
    // -----------------------------------------------------------------------

    /**
     * Compare this Circle with some Object, using the test above.
     *
     * @param obj  The object to compare with.
     * @return true if the two objects are equal.
     */
    public boolean equals(Object obj) {
        // This method is complete.

        if (obj instanceof Circle) {
            boolean test = false;
            Circle C = (Circle)obj;

            test = this.equals(C);

            return test;
        } else {
            return false;
        }
    }

    // ======================================
    // Implementors
    // ======================================

    /**
     * Computes and returns the area of the circle.
     *
     * @return  Area of the circle
     */
    public double area() {
        // You need to fill in this method.
				double area = Math.PI*Radius*Radius;
				return area;
    }

    /**
     * Tests whether this circle overlaps/touches/is disjoint with another
     * Circle, as set out in the project formulation.
     *
     * @return An integer describing the overlap with C:
     *         0 - disjoint; 1 - overlaps; 2 - touches; 3 - identical.
     */
    public int overlap(Circle C) {
        // You need to fill in this method.
				if (C.Center.equals(Center) == true && (Radius - C.Radius)< Point.GEOMTOL) {
					return 3;
				}
				else if(Center.distance(C.Center) <= Math.abs(Radius - C.Radius)) {
					return 0;
				}
 			  else if(Center.distance(C.Center) > (Radius + C.Radius)) {
					return 0;
				}
				else if(Center.distance(C.Center) == (Radius + C.Radius)){
					return 1;
				}
				else if(Center.distance(C.Center) < (Radius + C.Radius)) {
					return 2;
				}
				else {
					return 2;
				}
    }


    // =======================================================
    // Tester - test methods defined in this class
    // =======================================================

    public static void main(String args[]) {
        // You need to fill in this method.
				Circle c1 = new Circle(1,1,1);
				Circle c2 = new Circle(1,1,2);
				double b  = c1.area();
       // System.out.println( "The area of the circle is: " + b);
	//System.out.println(" " + c2.overlap(c1));
		}
}
