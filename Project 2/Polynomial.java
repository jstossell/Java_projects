/*
 * PROJECT II: Polynomial.java
 *
 * This file contains a template for the class Polynomial. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * This class is designed to use Complex in order to represent polynomials
 * with complex co-efficients. It provides very basic functionality and there
 * are very few methods to implement! The project formulation contains a
 * complete description.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file! You should also test this class using the main()
 * function.
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

class Polynomial {
    /**
     * An array storing the complex co-efficients of the polynomial.
     */
    Complex[] coeff;

    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * General constructor: assigns this polynomial a given set of
     * co-efficients.
     *
     * @param coeff  The co-efficients to use for this polynomial.
     */
    public Polynomial(Complex[] coeff) {
        // You need to fill in this function.
		int powerMax=0;
		for (int i=0; i<coeff.length; i++) {
		if ( coeff[i].abs() !=0) {
			powerMax=i+1;
			}
		}
		this.coeff = new Complex[powerMax];
		for (int j=0; j<powerMax; j++) {
			this.coeff[j]=coeff[j];
		}
	}

    /**
     * Default constructor: sets the Polynomial to the zero polynomial.
     */
    public Polynomial() {
        // You need to fill in this function.
        Complex c = new Complex(0,0);
        for (int i=0; i<this.coeff.length; i++) {
    			this.coeff[i]=c;
    		}
	}

    // ========================================================
    // Operations and functions with polynomials.
    // ========================================================

    /**
     * Create a string representation of the polynomial.
     *
     * For example: (1.0+1.0i)+(1.0+2.0i)X+(1.0+3.0i)X^2
     */
    public String toString() {
        // You need to fill in this function.
		int i;
		String poly = "";
		poly = poly + "(" +coeff[0].toString()+ ")+";
		for (i=1; i<coeff.length-1; i++) {
			poly = poly + "(" +coeff[i].toString()+ ")x^" +i+ "+";
		}
		poly = poly + "(" +coeff[coeff.length-1].toString()+ ")x^"+(coeff.length-1);
		return poly;
    }

    /**
     * Returns the degree of this polynomial.
     */
    public int degree() {
        // You need to fill in this function.
        int powerMax=0;
        for (int i=0; i<coeff.length; i++) {
		if ( coeff[i].abs() !=0 ) {
			powerMax=i;
			}
        }
        return powerMax;
    }

    /**
     * Evaluates the polynomial at a given point z.
     *
     * @param z  The point at which to evaluate the polynomial
     * @return   The complex number P(z).
     */
    public Complex evaluate(Complex z) {
        // You need to fill in this function.
		Complex a = new Complex(0,0);
		 for (int i=0; i<coeff.length; i++) {
			a = (a.add(coeff[coeff.length -i-1])).multiply(z);
		 }
		 return a;
    }

    /**
     * Calculate and returns the derivative of this polynomial.
     *
     * @return The derivative of this polynomial.
     */
    public Polynomial derivative() {
        // You need to fill in this function.
		Complex[] deriv = new Complex[coeff.length-1];
		for (int i=0; i<coeff.length-1; i++) {
			Complex di = new Complex(i+1,0);
			deriv[i] = coeff[i+1].multiply(di);
		}
		return new Polynomial(deriv);
    }

    // ========================================================
    // Tester function.
    // ========================================================

    public static void main(String[] args) {
        // You need to fill in this function.
		Complex a = new Complex(1,1);
		Complex b = new Complex(1,0);
		Complex[] help ={new Complex(1,1),new Complex(1,1),new Complex(1,1)};
		Polynomial p = new Polynomial(help);
		Complex d = p.evaluate(b);
		System.out.println("The polynomial evaluated at (1,0) is "+ d.toString());
		System.out.println("The derivative of this polynomial is "+ p.derivative().toString());
		System.out.println("The degree of this polynomial is "+ p.degree());
		System.out.println("The polynomial is " +p.toString());
    System.out.println("The default polynomial constructor is "+ b.toString());

    }


}
