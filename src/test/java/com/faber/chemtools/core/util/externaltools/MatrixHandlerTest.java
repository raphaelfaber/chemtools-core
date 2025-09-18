package com.faber.chemtools.core.util.externaltools;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MatrixHandlerTest {

    @Test
    void waterFormationTest() {
        int[] expected = {2, 1, 2}; // 2H2 + O2 -> 2H2O
        int[][] matrix = {
                {2, 0, -2},
                {0, 2, -1}
        };
        int[] result = MatrixHandler.solveMatrix(matrix);
        assert Arrays.equals(result, expected);
    }

    @Test
    void carbonDioxideFormationTest() {
        int[] expected = {1, 1, 1}; // C + O2 -> CO2
        int[][] matrix = {
                {1, 0, -1}, // C
                {0, 2, -2}  // O
        };
        int[] result = MatrixHandler.solveMatrix(matrix);
        assert Arrays.equals(result, expected);
    }

    @Test
    void methaneCombustionTest() {
        int[] expected = {1, 2, 1, 2}; // CH4 + 2O2 -> CO2 + 2H2O
        int[][] matrix = {
                {1, 0, -1, 0}, // C
                {4, 0, 0, -2}, // H
                {0, 2, -2, -1} // O
        };
        int[] result = MatrixHandler.solveMatrix(matrix);
        assert Arrays.equals(result, expected);
    }

    @Test
    void ammoniaSynthesisTest() {
        int[] expected = {1, 3, 2}; // N2 + 3H2 -> 2NH3
        int[][] matrix = {
                {2, 0, -1}, // N
                {0, 2, -3}  // H
        };
        int[] result = MatrixHandler.solveMatrix(matrix);
        assert Arrays.equals(result, expected);
    }

    @Test
    void combustionOfEthanolTest() {
        int[] expected = {1, 3, 2, 3}; // 1C2H5OH + 3O2 -> 2CO2 + 3H2O
        int[][] matrix = {
                {2, 0, -1, 0}, // C
                {6, 0, 0, -2}, // H
                {1, 2, -2, -1} // O
        };
        int[] result = MatrixHandler.solveMatrix(matrix);
        assert Arrays.equals(result, expected);
    }

    @Test
    void nitroglycerinDecompositionTest() {
        int[][] matrix = {
                {3, -1, 0, 0, 0},   // C
                {5, 0, 0, -2, 0},   // H
                {3, 0, -2, 0, 0},   // N
                {9, -2, 0, -1, -2}  // O
        };

        int[] expected = {4, 12, 6, 10, 1};

        int[] result = MatrixHandler.solveMatrix(matrix);

        assertArrayEquals(result, expected, "Matrix results were different. Expected:" + Arrays.toString(expected) +
                " Result:" + Arrays.toString(result));
    }

    @Test
    void methyleneBromideOxidationTest() {
        //CH2Br2 + O2 = CO2 + H2O + Br2
        int[][] matrix = {
                {1, 0, -1, 0, 0},   // C
                {2, 0, 0, -2, 0},   // H
                {0, 2, -2, -1, 0},  // O
                {2, 0, 0, 0, -2}    // Br
        };

        int[] expected = {2, 3, 2, 2, 2};

        int[] result = MatrixHandler.solveMatrix(matrix);

        assertArrayEquals(result, expected, "Matrix results were different. Expected:" + Arrays.toString(expected) +
                " Result:" + Arrays.toString(result));
    }
}