/*
 * PROJECT III: Project3.java
 *
 * This file contains a template for the class Project3. None of methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class, as well as GeneralMatrix and TriMatrix.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Project3 {
    /**
     * Calculates the variance of the distribution defined by the determinant
     * of a random matrix. See the formulation for a detailed description.
     *
     * @param m           The matrix object that will be filled with random
     *                    samples.
     * @param numSamples  The number of samples to generate when calculating
     *                    the variance.
     * @return            The variance of the distribution.
     */
    public static double matVariance(Matrix m, int numSamples) {
        // You need to fill in this method.
        double variance;
		    double sum = 0;
        double sumOfSquares =0;
		    for (int i=0; i<numSamples; i++) {
          m.random();
          sum = sum + m.determinant();
          sumOfSquares =  sumOfSquares + Math.pow(m.determinant(), 2);
        }
        variance = (sumOfSquares/numSamples) - Math.pow((sum/numSamples) ,2);
        return variance;
    }

    /**
     * This function should calculate the variances of matrices for matrices
     * of size 2 <= n <= 50. See the formulation for more detail.
     */
    public static void main(String[] args) {
        // You need to fill in this method.
        for (int n=2; n<=50; n++) {
          int generalSamples = 15000;
          int triSamples = 150000;
          GeneralMatrix g = new GeneralMatrix(n, n);
          TriMatrix t = new TriMatrix(n);
          double var1 = matVariance(g, generalSamples);
          double var2 = matVariance(t, triSamples);
          System.out.println(n+ "\t" +var1 + "\t" +var2);
        }
    }
}
