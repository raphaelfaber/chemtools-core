package com.faber.chemtools.core.molecules.business;

import com.faber.chemtools.core.elements.business.ElementData;
import com.faber.chemtools.core.exceptions.InvalidElementException;
import com.faber.chemtools.core.molecules.entities.Molecule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateAmountOfSubstanceTest {

    static class TestCase {
        String formula;
        Molecule molecule;
        double massInGrams;
        double expectedMoles;

        TestCase(String formula, double massInGrams, double expectedMoles, int[][] elements) throws InvalidElementException {
            this.formula = formula;
            this.massInGrams = massInGrams;
            this.expectedMoles = expectedMoles;
            this.molecule = new Molecule();
            for (int[] element : elements) {
                this.molecule.addElement(ElementData.getElement(element[0]), element[1]);
            }
        }
    }

    @Test
    void shouldCalculateMolesForCommonCompounds() throws InvalidElementException {
        TestCase[] testCases = new TestCase[]{
                new TestCase("H2O", 18.015, 1.0, new int[][]{{1, 2}, {8, 1}}),
                new TestCase("CO2", 44.009, 1.0, new int[][]{{6, 1}, {8, 2}}),
                new TestCase("CH4", 16.043, 1.0, new int[][]{{6, 1}, {1, 4}}),
                new TestCase("C2H6", 30.070, 1.0, new int[][]{{6, 2}, {1, 6}}),
                new TestCase("Ca(OH)2", 148.186, 2.0, new int[][]{{20, 1}, {8, 2}, {1, 2}}),
                new TestCase("Al2(SO4)3", 684.300, 2.0, new int[][]{{13, 2}, {16, 3}, {8, 12}}),
                new TestCase("NaCl", 58.443, 1.0, new int[][]{{11, 1}, {17, 1}}),
                new TestCase("C6H12O6", 180.156, 1.0, new int[][]{{6, 6}, {1, 12}, {8, 6}})
        };

        for (TestCase test : testCases) {
            double calculatedMoles = FromMolecule.calculateAmountOfSubstance(test.massInGrams, test.molecule);
            assertEquals(test.expectedMoles, calculatedMoles, 0.01,
                    "Mismatch in number of moles for " + test.formula);
        }
    }

    @Test
    void shouldHandleNonIntegerMoles() throws InvalidElementException {
        TestCase water = new TestCase("H2O", 9.007, 0.5, new int[][]{{1, 2}, {8, 1}});
        double calculated = FromMolecule.calculateAmountOfSubstance(water.massInGrams, water.molecule);
        assertEquals(water.expectedMoles, calculated, 0.01, "0.5 mol of water should be correct");
    }

    @Test
    void shouldReturnZeroForZeroMass() throws InvalidElementException {
        TestCase salt = new TestCase("NaCl", 0.0, 0.0, new int[][]{{11, 1}, {17, 1}});
        double calculated = FromMolecule.calculateAmountOfSubstance(salt.massInGrams, salt.molecule);
        assertEquals(0.0, calculated, "0 grams should yield 0 mol");
    }

    @Test
    void shouldThrowForNegativeMass() throws InvalidElementException {
        TestCase methane = new TestCase("CH4", -16.043, -1.0, new int[][]{{6, 1}, {1, 4}});
        assertThrows(IllegalArgumentException.class,
                () -> FromMolecule.calculateAmountOfSubstance(methane.massInGrams, methane.molecule),
                "Negative mass should throw IllegalArgumentException");
    }

    @Test
    void shouldWorkWithLargeMasses() throws InvalidElementException {
        TestCase co2 = new TestCase("CO2", 44_009.0, 1000.0, new int[][]{{6, 1}, {8, 2}});
        double calculated = FromMolecule.calculateAmountOfSubstance(co2.massInGrams, co2.molecule);
        assertEquals(co2.expectedMoles, calculated, 0.1, "Large inputs should scale correctly");
    }

    @Test
    void shouldThrowWhenMoleculeHasNoElements() {
        Molecule empty = new Molecule();
        assertThrows(IllegalArgumentException.class,
                () -> FromMolecule.calculateAmountOfSubstance(10.0, empty),
                "Empty molecule should not allow mole calculation");
    }

    @Test
    void shouldThrowWhenMoleculeIsNull() {
        assertThrows(NullPointerException.class,
                () -> FromMolecule.calculateAmountOfSubstance(10.0, null),
                "Null molecule should not be accepted");
    }
}
