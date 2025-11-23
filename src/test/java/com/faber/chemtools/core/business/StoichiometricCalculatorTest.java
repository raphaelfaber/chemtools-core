package com.faber.chemtools.core.business;

import com.faber.chemtools.core.entities.Equation;
import com.faber.chemtools.core.exceptions.InvalidReactionException;
import com.faber.chemtools.core.exceptions.MoleculeNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoichiometricCalculatorTest {
    private static final double DELTA = 0.5;
    @Test
    void testStoichiometricCalculation_ByMoles() throws Exception {
        // Equação clássica: H2 + O2 -> H2O
        Equation eq = Equation.factory("H2 + O2 -> H2O");
        eq.balance();
        // Equilibrada: 2H2 + O2 -> 2H2O

        StoichiometricCalculator calc = new StoichiometricCalculator(eq);

        // Define H2 como molécula de referência
        calc.setReferenceMolecule("H2");

        // Define 4 mol de H2 (o dobro de 2)
        calc.setRefenceMoles(4);

        // Esperado:
        // coef H2: 2 * 2 = 4
        // coef O2: 1 * 2 = 2
        // coef H2O: 2 * 2 = 4

        assertEquals("4H2 + 2O2 => 4H2O", calc.toString());
    }

    @Test
    void testStoichiometricCalculation_ByMass() throws Exception {
        // H2 + O2 -> H2O
        Equation eq = Equation.factory("H2 + O2 -> H2O");
        eq.balance();

        StoichiometricCalculator calc = new StoichiometricCalculator(eq);
        calc.setReferenceMolecule("H2");

        // Massa molar do H2 = 2 g/mol → 4 g = 2 mol
        calc.setReferenceMass(4.0,1);

        // Dobro da equação:

        assertEquals("4H2 + 2O2 => 4H2O", calc.toString());
    }

    @Test
    void testInvalidReferenceMolecule() throws Exception {
        Equation eq = Equation.factory("H2 + O2 -> H2O");
        eq.balance();

        StoichiometricCalculator calc = new StoichiometricCalculator(eq);

        assertThrows(MoleculeNotFoundException.class, () -> {
            calc.setReferenceMolecule("CO2");
        });
    }

    @Test
    void testInvalidEquationInConstructor() {
        assertThrows(InvalidReactionException.class, () -> {
            Equation.factory("H2 =>"); // inválida
        });
    }

    @Test
    void waterEquationTest() throws Exception {
        Equation eq = Equation.factory("H2 + O2 => H2O");
        eq.balance();
        assertEquals("2H2 + O2 => 2H2O",eq.toString());

        StoichiometricCalculator calc = new StoichiometricCalculator(eq);
        calc.setReferenceMolecule("H2");
        calc.setRefenceMoles(4,1);
        assertEquals("4H2 + 2O2 => 4H2O", calc.toString());

        int expectedH2Mass = 8;
        int expectedO2Mass = 64;
        int expectedH2OMass = 72;
        assertEquals(expectedH2Mass,calc.calculateMass("H2"),DELTA);
        assertEquals(expectedO2Mass,calc.calculateMass("O2"),DELTA);
        assertEquals(expectedH2OMass,calc.calculateMass("H2O"),DELTA);

        int expectedH2Moles=4;
        int expectedO2Moles=2;
        int expectedH2OMoles=4;

        assertEquals(expectedH2Moles,calc.calculateMoles("H2"),DELTA);
        assertEquals(expectedO2Moles,calc.calculateMoles("O2"),DELTA);
        assertEquals(expectedH2OMoles,calc.calculateMoles("H2O"),DELTA);

    }

    @Test
    void methaneCombustionTest() throws Exception {
        Equation eq = Equation.factory("CH4 + O2 => CO2 + H2O");
        eq.balance();
        assertEquals("CH4 + 2O2 => CO2 + 2H2O", eq.toString());

        StoichiometricCalculator calc = new StoichiometricCalculator(eq);
        calc.setReferenceMolecule("CH4");
        calc.setRefenceMoles(1, 1);
        assertEquals("CH4 + 2O2 => CO2 + 2H2O", calc.toString());

        assertEquals(16, calc.calculateMass("CH4"), DELTA);
        assertEquals(64, calc.calculateMass("O2"), DELTA);
        assertEquals(44, calc.calculateMass("CO2"), DELTA);
        assertEquals(36, calc.calculateMass("H2O"), DELTA);

        assertEquals(1, calc.calculateMoles("CH4"), DELTA);
        assertEquals(2, calc.calculateMoles("O2"), DELTA);
        assertEquals(1, calc.calculateMoles("CO2"), DELTA);
        assertEquals(2, calc.calculateMoles("H2O"), DELTA);
    }

    @Test
    void ironOxidationTest() throws Exception {
        Equation eq = Equation.factory("Fe + O2 => Fe2O3");
        eq.balance();
        assertEquals("4Fe + 3O2 => 2Fe2O3", eq.toString());

        StoichiometricCalculator calc = new StoichiometricCalculator(eq);
        calc.setReferenceMolecule("Fe");
        calc.setRefenceMoles(4, 1);
        assertEquals("4Fe + 3O2 => 2Fe2O3", calc.toString());

        assertEquals(223.38, calc.calculateMass("Fe"), DELTA);
        assertEquals(96, calc.calculateMass("O2"), DELTA);
        assertEquals(319, calc.calculateMass("Fe2O3"), DELTA);

        assertEquals(4, calc.calculateMoles("Fe"), DELTA);
        assertEquals(3, calc.calculateMoles("O2"), DELTA);
        assertEquals(2, calc.calculateMoles("Fe2O3"), DELTA);
    }

    @Test
    void neutralizationTest() throws Exception {
        Equation eq = Equation.factory("HCl + NaOH => NaCl + H2O");
        eq.balance();
        assertEquals("HCl + NaOH => NaCl + H2O", eq.toString());

        StoichiometricCalculator calc = new StoichiometricCalculator(eq);
        calc.setReferenceMolecule("HCl");
        calc.setRefenceMoles(2, 1);
        assertEquals("2HCl + 2NaOH => 2NaCl + 2H2O", calc.toString());

        assertEquals(73, calc.calculateMass("HCl"), DELTA);
        assertEquals(80, calc.calculateMass("NaOH"), DELTA);
        assertEquals(117, calc.calculateMass("NaCl"), DELTA);
        assertEquals(36, calc.calculateMass("H2O"), DELTA);

        assertEquals(2, calc.calculateMoles("HCl"), DELTA);
        assertEquals(2, calc.calculateMoles("NaOH"), DELTA);
        assertEquals(2, calc.calculateMoles("NaCl"), DELTA);
        assertEquals(2, calc.calculateMoles("H2O"), DELTA);
    }

    @Test
    void calciumCarbonateDecompositionTest() throws Exception {
        Equation eq = Equation.factory("CaCO3 => CaO + CO2");
        eq.balance();
        assertEquals("CaCO3 => CaO + CO2", eq.toString());

        StoichiometricCalculator calc = new StoichiometricCalculator(eq);
        calc.setReferenceMolecule("CaCO3");
        calc.setRefenceMoles(1, 1);
        assertEquals("CaCO3 => CaO + CO2", calc.toString());

        assertEquals(100, calc.calculateMass("CaCO3"), DELTA);
        assertEquals(56, calc.calculateMass("CaO"), DELTA);
        assertEquals(44, calc.calculateMass("CO2"), DELTA);

        assertEquals(1, calc.calculateMoles("CaCO3"), DELTA);
        assertEquals(1, calc.calculateMoles("CaO"), DELTA);
        assertEquals(1, calc.calculateMoles("CO2"), DELTA);
    }

    @Test
    void ammoniaSynthesisTest() throws Exception {
        Equation eq = Equation.factory("N2 + H2 => NH3");
        eq.balance();
        assertEquals("N2 + 3H2 => 2NH3", eq.toString());

        StoichiometricCalculator calc = new StoichiometricCalculator(eq);
        calc.setReferenceMolecule("N2");
        calc.setRefenceMoles(2, 1);
        assertEquals("2N2 + 6H2 => 4NH3", calc.toString());

        assertEquals(56, calc.calculateMass("N2"), DELTA);
        assertEquals(12, calc.calculateMass("H2"), DELTA);
        assertEquals(68, calc.calculateMass("NH3"), DELTA);

        assertEquals(2, calc.calculateMoles("N2"), DELTA);
        assertEquals(6, calc.calculateMoles("H2"), DELTA);
        assertEquals(4, calc.calculateMoles("NH3"), DELTA);
    }


    private StoichiometricCalculator buildWaterCalculation() throws Exception {
        Equation eq = Equation.factory("H2 + O2 => H2O");
        eq.balance();
        StoichiometricCalculator calc = new StoichiometricCalculator(eq);
        calc.setReferenceMolecule("H2");
        calc.setRefenceMoles(4, 0);
        return calc;
    }

    @Test
    void calculateMass_roundingCeilingTest() throws Exception {
        StoichiometricCalculator calc = buildWaterCalculation();
        double result = calc.calculateMass("H2", 0);
        assertEquals(8, result, DELTA);
    }

    @Test
    void calculateMoles_decimalRoundingTest() throws Exception {
        StoichiometricCalculator calc = buildWaterCalculation();
        assertEquals(4.000, calc.calculateMoles("H2", 3), DELTA);
    }

    @Test
    void calculateMass_simpleTest() throws Exception {
        StoichiometricCalculator calc = buildWaterCalculation();
        assertEquals(8.0, calc.calculateMass("H2"), DELTA);
    }




}