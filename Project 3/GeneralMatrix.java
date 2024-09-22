/*
 * PROJECT III: GeneralMatrix.java
 *
 * This file contains a template for the class GeneralMatrix. Not all methods
 * are implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

import java.util.Arrays;

public class GeneralMatrix extends Matrix {
    /**
     * This instance variable stores the elements of the matrix.
     */
    private double[][] data;

    /**
     * Constructor function: should initialise m and n through the Matrix
     * constructor and set up the data array.
     *
     * @param m  The first dimension of the array.
     * @param n  The second dimension of the array.
     */
    public GeneralMatrix(int m, int n) throws MatrixException {
        // You need to fill in this method.
        super(m,n);
        this.data = new double [m][n];
    }

    /**
     * Constructor function. This is a copy constructor; it should create a
     * copy of the matrix A.
     *
     * @param A  The matrix to create a copy of.
     */
    public GeneralMatrix(GeneralMatrix A) {
        // You need to fill in this method.
        super(A.m,A.n);
        data = new double[A.m][A.n];
        for (int i=0; i<A.m; i++){
            for (int j=0; j<A.n; j++){
              data[i][j]=A.data[i][j];
            }
          }
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
        if (i<m && j<n){
        return data[i][j];
      } else {
        throw new MatrixException("Matrix index is too large, choose one within the dimensions");
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
        if (i<m && j<n){
        data[i][j] = val;
      } else {
        throw new MatrixException("Matrix index is too large, choose one within the bounds");
      }
    }

    /**
     * Return the determinant of this matrix.
     *
     * @return The determinant of the matrix.
     */
    public double determinant() {
        // You need to fill in this method.
        if(m==n){
        double[] d = new double [1];
        GeneralMatrix decomp = decomp(d);
        double det = 1;
        for (int i=0; i<m; i++) {
          det = det*decomp.getIJ(i,i);
        }
        det = det*d[0];
        return det;
      } else {
        throw new MatrixException("Matrix is not square, determinant not defined");
      }
    }

    /**
     * Add the matrix to another matrix A.
     *
     * @param A  The Matrix to add to this matrix.
     * @return   The sum of this matrix with the matrix A.
     */
    public Matrix add(Matrix A) {
        // You need to fill in this method.
        Matrix sum = new GeneralMatrix(m, n);
        if (m==A.m && n==A.n){
          for (int i=0; i<m; i++){
              for (int j=0; j<n; j++){
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
        Matrix product = new GeneralMatrix(m, A.n);
        if (n==A.m) {
          for (int i=0; i<m; i++) {
            for (int j=0; j<A.n; j++) {
              double productSum = 0;
              for (int k=0; k<n; k++) {
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
        Matrix scalar = new GeneralMatrix(m,n);
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
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
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
              data[i][j]= Math.random();
            }
          }
        }

    /**
     * Returns the LU decomposition of this matrix; i.e. two matrices L and U
     * so that A = LU, where L is lower-diagonal and U is upper-diagonal.
     *
     * On exit, decomp returns the two matrices in a single matrix by packing
     * both matrices as follows:
     *
     * [ u_11 u_12 u_13 u_14 ]
     * [ l_21 u_22 u_23 u_24 ]
     * [ l_31 l_32 u_33 u_34 ]
     * [ l_41 l_42 l_43 l_44 ]
     *
     * where u_ij are the elements of U and l_ij are the elements of l. When
     * calculating the determinant you will need to multiply by the value of
     * d[0] calculated by the function.
     *
     * If the matrix is singular, then the routine throws a MatrixException.
     *
     * This method is an adaptation of the one found in the book "Numerical
     * Recipies in C" (see online for more details).
     *
     * @param d  An array of length 1. On exit, the value contained in here
     *           will either be 1 or -1, which you can use to calculate the
     *           correct sign on the determinant.
     * @return   The LU decomposition of the matrix.
     */
    public GeneralMatrix decomp(double[] d) {
        // This method is complete. You should not even attempt to change it!!
        if (n != m)
            throw new MatrixException("Matrix is not square");
        if (d.length != 1)
            throw new MatrixException("d should be of length 1");

        int           i, imax = -10, j, k;
        double        big, dum, sum, temp;
        double[]      vv   = new double[n];
        GeneralMatrix a    = new GeneralMatrix(this);

        d[0] = 1.0;

        for (i = 1; i <= n; i++) {
            big = 0.0;
            for (j = 1; j <= n; j++)
                if ((temp = Math.abs(a.data[i-1][j-1])) > big)
                    big = temp;
            if (big == 0.0)
                throw new MatrixException("Matrix is singular");
            vv[i-1] = 1.0/big;
        }

        for (j = 1; j <= n; j++) {
            for (i = 1; i < j; i++) {
                sum = a.data[i-1][j-1];
                for (k = 1; k < i; k++)
                    sum -= a.data[i-1][k-1]*a.data[k-1][j-1];
                a.data[i-1][j-1] = sum;
            }
            big = 0.0;
            for (i = j; i <= n; i++) {
                sum = a.data[i-1][j-1];
                for (k = 1; k < j; k++)
                    sum -= a.data[i-1][k-1]*a.data[k-1][j-1];
                a.data[i-1][j-1] = sum;
                if ((dum = vv[i-1]*Math.abs(sum)) >= big) {
                    big  = dum;
                    imax = i;
                }
            }
            if (j != imax) {
                for (k = 1; k <= n; k++) {
                    dum = a.data[imax-1][k-1];
                    a.data[imax-1][k-1] = a.data[j-1][k-1];
                    a.data[j-1][k-1] = dum;
                }
                d[0] = -d[0];
                vv[imax-1] = vv[j-1];
            }
            if (a.data[j-1][j-1] == 0.0)
                a.data[j-1][j-1] = 1.0e-20;
            if (j != n) {
                dum = 1.0/a.data[j-1][j-1];
                for (i = j+1; i <= n; i++)
                    a.data[i-1][j-1] *= dum;
            }
        }

        return a;
    }

    /*
     * Your tester function should go here.
     */
    public static void main(String[] args) {
        // You need to fill in this method.
        GeneralMatrix identity = new GeneralMatrix(3, 3);
        for (int i=0; i<3; i++){
          identity.setIJ(i, i, 1);
        }
        GeneralMatrix weird = new GeneralMatrix(3, 3);
        double[][] weirdo = {{2,1,0},{1,2,1},{0,1,2}};
        for (int i=0; i<3; i++) {
          for (int j=0; j<3; j++) {
            weird.setIJ(i,j,weirdo[i][j]);
          }
        }
        GeneralMatrix error3 = new GeneralMatrix(2, 3);
        double[][] errorThree = {{2,1,0},{1,2,1}};
        for (int i=0; i<2; i++) {
          for (int j=0; j<3; j++) {
            error3.setIJ(i,j,errorThree[i][j]);
          }
        }
        GeneralMatrix rando = new GeneralMatrix(2, 2);
		    rando.random();
        GeneralMatrix zeros = new GeneralMatrix(3,3);
        Matrix a = identity.add(identity);
        Matrix b = zeros.multiply(10);
        Matrix c = identity.multiply(zeros);
        Matrix d = identity.multiply(10);
        Matrix e = identity.multiply(weird);
        double deter = identity.determinant();
        //Matrix error1 = rando.add(identity);
        //Matrix error2 = rando.multiply(identity);
        //error3.determinant();
        System.out.println("A random 2x2 matrix is: "+rando.toString());
        System.out.println("The 3x3 identity is: "+identity.toString());
        System.out.println("The 3x3 zeros matrix is: "+zeros.toString());
        System.out.println("I+I= "+a.toString());
        System.out.println("zeros*10= "+b.toString());
        System.out.println("I*zeros= "+c.toString());
        System.out.println("I*test= "+e.toString());
        System.out.println("I*10= "+d.toString());
        System.out.println("determinant of identity= "+deter);
        System.out.println("determinant of 10*identity= "+d.determinant());
        System.out.println("The test matrix is: "+weird.toString());
        System.out.println("The test determinant is: "+weird.determinant());
    }
}
