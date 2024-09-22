/*
 * PROJECT III: TriMatrix.java
 *
 * This file contains a template for the class TriMatrix. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class TriMatrix extends Matrix {
    /**
     * An array holding the diagonal elements of the matrix.
     */
    private double[] diag;

    /**
     * An array holding the upper-diagonal elements of the matrix.
     */
    private double[] upper;

    /**
     * An array holding the lower-diagonal elements of the matrix.
     */
    private double[] lower;

    /**
     * Constructor function: should initialise m and n through the Matrix
     * constructor and set up the data array.
     *
     * @param N  The dimension of the array.
     */
    public TriMatrix(int N) {
        // You need to fill in this method.
        super(N,N);
        diag = new double [N];
        upper = new double [N-1];
        lower = new double [N-1];
    }

    /**
     * Getter function: return the (i,j)'th entry of the matrix.
     *
     * @param i  The location in the first co-ordinate.
     * @param j  The location in the second co-ordinate.
     * @return   The (i,j)'th entry of the matrix.
     */
    public double getIJ(int i, int j) {
        // You need to fill in this method.
        if (i<0 || j<0){
          throw new MatrixException("Matrix index must not be negative, no entry exists");
        }
        if (i<diag.length && j<diag.length){
        double wanted = 0;
        if (i==j) {
          wanted=diag[i];
        }
         else if (i-1==j) {
          wanted=lower[i-1];
        }
        else if (i+1==j) {
          wanted=upper[i];
        }
        return wanted;
      } else {
        throw new MatrixException("No such matrix entry exists");
      }
    }

    /**
     * Setter function: set the (i,j)'th entry of the data array.
     *
     * @param i    The location in the first co-ordinate.
     * @param j    The location in the second co-ordinate.
     * @param val  The value to set the (i,j)'th entry to.
     */
    public void setIJ(int i, int j, double val) {
        // You need to fill in this method.
        if (i<0 || j<0){
          throw new MatrixException("Matrix index must not be negative, no entry exists");
        }
        if (i>=diag.length || j>=diag.length){
          throw new MatrixException("Matrix index is outside dimensions, no entry exists");
        }
        if (i<diag.length && j<diag.length){
          if (i==j) {
            diag[i] = val;
          }
          else if (i - 1==j) {
            lower[i-1] = val;
          }
          else if (i + 1==j) {
            upper[i] = val;
          }
          else {
            if (val !=0){
            throw new MatrixException("This is not a TriMatrix, you can only set non-zero values in the 3 main diagonals");
          }
          }
        }
    }

    /**
     * Return the determinant of this matrix.
     *
     * @return The determinant of the matrix.
     */
    public double determinant() {
        // You need to fill in this method.
        double det = 1;
        TriMatrix decomp = decomp();
        for (int i=0; i<diag.length; i++) {
          det = det*decomp.diag[i];
        }
        return det;
    }

    /**
     * Returns the LU decomposition of this matrix. See the formulation for a
     * more detailed description.
     *
     * @return The LU decomposition of this matrix.
     */
    public TriMatrix decomp() {
        // You need to fill in this method.
        TriMatrix decompt = new TriMatrix(diag.length);
        decompt.diag[0] = diag[0];
        for (int i=0; i<upper.length; i++) {
          decompt.upper[i] = upper[i];
          decompt.lower[i] = lower[i]/decompt.diag[i];
          decompt.diag[i+1] = diag[i+1] - (decompt.lower[i]*decompt.upper[i]);
        }
        return decompt;
      }

    /**
     * Add the matrix to another matrix A.
     *
     * @param A  The Matrix to add to this matrix.
     * @return   The sum of this matrix with the matrix A.
     */
    public Matrix add(Matrix A){
        // You need to fill in this method.
        Matrix sum = new GeneralMatrix(A.m, A.n);
        if(A.m==diag.length && A.n==diag.length){
          for (int i=0; i<A.m; i++){
              for (int j=0; j<A.n; j++){
                sum.setIJ(i, j, (getIJ(i,j)+A.getIJ(i,j)));
              }
            }
        } else {
        throw new MatrixException("Matricies don't have the same dimensions, addition not defined");
        }
        return sum;
    }

    /**
     * Multiply the matrix by another matrix A. This is a _left_ product,
     * i.e. if this matrix is called B then it calculates the product BA.
     *
     * @param A  The Matrix to multiply by.
     * @return   The product of this matrix with the matrix A.
     */
    public Matrix multiply(Matrix A) {
        // You need to fill in this method.
        Matrix product = new GeneralMatrix(diag.length, A.n);
        if (diag.length==A.m) {
          for (int i=0; i<diag.length; i++) {
            for (int j=0; j<A.n; j++) {
              double productSum = 0;
              for (int k=0; k<diag.length; k++) {
                productSum = productSum + (getIJ(i,k)*A.getIJ(k,j));
                }
                product.setIJ(i, j, productSum);
              }
            }
          } else {
            throw new MatrixException("Matricies aren't the correct size for multiplication");
            }
            return product;
    }

    /**
     * Multiply the matrix by a scalar.
     *
     * @param a  The scalar to multiply the matrix by.
     * @return   The product of this matrix with the scalar a.
     */
    public Matrix multiply(double a) {
        // You need to fill in this method.
        Matrix scalar = new TriMatrix(diag.length);
        for (int i=0; i<diag.length; i++){
            for (int j=0; j<diag.length; j++){
              scalar.setIJ(i, j, getIJ(i,j)*a);
            }
          }
          return scalar;
    }

    /**
     * Populates the matrix with random numbers which are uniformly
     * distributed between 0 and 1.
     */
    public void random() {
        // You need to fill in this method.
        for (int i=0; i<diag.length; i++) {
          diag[i] = Math.random();
        }
        for (int i=0; i<upper.length; i++) {
          lower[i] = Math.random();
          upper[i] = Math.random();
        }
      }

    /*
     * Your tester function should go here.
     */
    public static void main(String[] args) {
        // You need to fill in this method.
        TriMatrix identity = new TriMatrix(3);
        GeneralMatrix ones = new GeneralMatrix(3, 3);
        TriMatrix weirdo = new TriMatrix(3);
        weirdo.diag = new double[] {2,2,2};
        weirdo.upper = new double[] {1,1};
        weirdo.lower = new double[] {1,1};
        for (int i=0; i<3; i++){
          for (int j=0; j<3; j++){
          ones.setIJ(i, j, 1);
        }
        }
        /*TriMatrix error1 = new TriMatrix(3);
        for (int i=0; i<3; i++){
          for (int j=0; j<3; j++){
          error1.setIJ(i, j, 1);
        }
      }*/
        identity.diag = new double[] {1, 1, 1};
        TriMatrix rando = new TriMatrix(3);
		    rando.random();
        TriMatrix zeros = new TriMatrix(3);
        Matrix a = identity.add(identity);
        Matrix b = zeros.multiply(10);
        Matrix c = identity.multiply(zeros);
        Matrix d = identity.multiply(10);
        Matrix e = ones.multiply(identity);
        double deter = identity.determinant();
        System.out.println("A random 3x3 matrix is: "+rando.toString());
        System.out.println("The 3x3 identity is: "+identity.toString());
        System.out.println("The 3x3 zeros matrix is: "+zeros.toString());
        System.out.println("I+I= "+a.toString());
        System.out.println("zeros*10= "+b.toString());
        System.out.println("I*zeros= "+c.toString());
        System.out.println("I*10= "+d.toString());
        System.out.println("determinant of identity= "+deter);
        System.out.println("determinant of 10*identity= "+d.determinant());
        System.out.println("I*ones= "+e.toString());
        System.out.println("the tester is: "+weirdo.toString());
        System.out.println("determinant of tester= "+weirdo.determinant());
    }
}
