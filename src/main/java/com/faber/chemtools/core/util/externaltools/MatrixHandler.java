package com.faber.chemtools.core.util.externaltools;

import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.fraction.FractionField;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrix;

import java.util.Arrays;


public class MatrixHandler {
    /*
    * Disclaimer: This class was generated using OPENAI ChatGPT-5 assist
    * */
    public static int[] solveMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        FieldMatrix<Fraction> A = new Array2DRowFieldMatrix<>(FractionField.getInstance(), rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                A.setEntry(i, j, new Fraction(matrix[i][j]));
            }
        }

        return solveHomogeneous(A);
    }

    private static int[] solveHomogeneous(FieldMatrix<Fraction> A) {
        int rows = A.getRowDimension();
        int cols = A.getColumnDimension();

        Fraction[][] mat = new Fraction[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                mat[i][j] = A.getEntry(i, j);

        // Gauss-Jordan elimination
        int rank = 0;
        for (int col = 0; col < cols; col++) {
            int pivotRow = -1;
            for (int r = rank; r < rows; r++) {
                if (!mat[r][col].equals(Fraction.ZERO)) {
                    pivotRow = r;
                    break;
                }
            }
            if (pivotRow == -1) continue;

            // Swap rows
            Fraction[] temp = mat[rank];
            mat[rank] = mat[pivotRow];
            mat[pivotRow] = temp;

            // Normalize pivot
            Fraction pivot = mat[rank][col];
            for (int c = col; c < cols; c++) {
                mat[rank][c] = mat[rank][c].divide(pivot);
            }

            // Eliminate below
            for (int r = 0; r < rows; r++) {
                if (r != rank && !mat[r][col].equals(Fraction.ZERO)) {
                    Fraction factor = mat[r][col];
                    for (int c = col; c < cols; c++) {
                        mat[r][c] = mat[r][c].subtract(factor.multiply(mat[rank][c]));
                    }
                }
            }

            rank++;
        }

        // All free variables = 1
        Fraction[] sol = new Fraction[cols];
        Arrays.fill(sol, Fraction.ONE);

        // Adjust solution using first pivot in each row
        for (int r = 0; r < rows; r++) {
            int pivotCol = -1;
            for (int c = 0; c < cols; c++) {
                if (!mat[r][c].equals(Fraction.ZERO)) {
                    pivotCol = c;
                    break;
                }
            }
            if (pivotCol == -1) continue;
            Fraction sum = Fraction.ZERO;
            for (int c = pivotCol + 1; c < cols; c++) {
                sum = sum.add(mat[r][c].multiply(sol[c]));
            }
            sol[pivotCol] = sum.negate();
        }

        // Transform fractions to integers
        int lcm = 1;
        for (Fraction f : sol) {
            lcm = lcm(lcm, f.getDenominator());
        }

        int[] result = new int[cols];
        for (int i = 0; i < cols; i++) {
            result[i] = sol[i].multiply(lcm).intValue();
        }

        // Normalize by GCD
        int gcd = result[0];
        for (int val : result) {
            gcd = gcd(gcd, val);
        }
        if (gcd == 0) gcd = 1;
        for (int i = 0; i < result.length; i++) {
            result[i] /= gcd;
        }

        // Ensure all positive
        if (Arrays.stream(result).allMatch(x -> x <= 0)) {
            for (int i = 0; i < result.length; i++) {
                result[i] = -result[i];
            }
        }

        return result;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? Math.abs(a) : gcd(b, a % b);
    }

    private static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}
