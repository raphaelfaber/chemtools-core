package com.faber.chemtools.core.molecules.business;

import com.faber.chemtools.core.elements.business.ElementData;
import com.faber.chemtools.core.exceptions.InvalidElementException;
import com.faber.chemtools.core.molecules.entities.Molecule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateMolecularWeightTest {
    static class TestCase {
        String formula;
        Molecule molecule;
        double expectedWeight;

        TestCase(String formula, double expectedWeight, int[][] elements) throws InvalidElementException {
            this.formula = formula;
            this.expectedWeight = expectedWeight;
            this.molecule = new Molecule();
            for (int[] element : elements) {
                this.molecule.addElement(ElementData.getElement(element[0]), element[1]);
            }
        }
    }

    @Test
    void CalculateMolecularWeight() throws InvalidElementException {
        TestCase[] testCases = new TestCase[]{
                new TestCase("H2O", 18.015, new int[][]{{1, 2}, {8, 1}}),
                new TestCase("CO2", 44.009, new int[][]{{6, 1}, {8, 2}}),
                new TestCase("CH4", 16.043, new int[][]{{6, 1}, {1, 4}}),
                new TestCase("C2H6", 30.070, new int[][]{{6, 2}, {1, 6}}),
                new TestCase("Ca(OH)2", 74.093, new int[][]{{20, 1}, {8, 2}, {1, 2}}),
                new TestCase("Al2(SO4)3", 342.150, new int[][]{{13, 2}, {16, 3}, {8, 12}}),
                new TestCase("K4(ON(SO3)2)2", 536.632, new int[][]{{19, 4}, {8, 14}, {7, 2}, {16, 4}}),
                new TestCase("NaCl", 58.443, new int[][]{{11, 1}, {17, 1}}),
                new TestCase("Mg(OH)2", 58.319, new int[][]{{12, 1}, {8, 2}, {1, 2}}),
                new TestCase("C6H12O6", 180.156, new int[][]{{6, 6}, {1, 12}, {8, 6}}),
                new TestCase("Fe2(SO4)3", 399.877, new int[][]{{26, 2}, {16, 3}, {8, 12}}),
                new TestCase("H2SO4", 98.079, new int[][]{{1, 2}, {16, 1}, {8, 4}})
        };
        for (TestCase test : testCases) {
            double expectedMss = test.expectedWeight;
            double calculatedMass = FromMolecule.calculateMolecularWeight(test.molecule);

            assertEquals(calculatedMass, expectedMss, 0.1, "Expected: " + expectedMss + " but was: " + calculatedMass);
        }
    }
}