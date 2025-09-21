package com.faber.chemtools.core.molecules.business;

import com.faber.chemtools.core.elements.business.ElementData;
import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.exceptions.InvalidElementException;
import com.faber.chemtools.core.molecules.entities.Molecule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateMassInAmountOfSubstanceTest {

    private static final double DELTA = 0.05;

    @Test
    public void testCalculateMassInAmountOfSubstanceInWater() throws InvalidElementException {
        // test: water (H2O)
        // 2 moles of water -> 36.03 g
        double expectedMass = 36.03;
        Element H = ElementData.getElement(1);
        Element O = ElementData.getElement(8);
        Molecule molecule = new Molecule();
        molecule.addElement(H, 2);
        molecule.addElement(O, 1);

        double result = FromMolecule.calculateMassInAmountOfSubstance(2, molecule);
        assertEquals(expectedMass, result, DELTA, "fail on H2O");
    }

    @Test
    public void testCalculateMassInAmountOfSubstanceInCarbonDioxide() throws InvalidElementException {
        // test: CO2
        // 3 moles of CO2 -> 132.03 g
        double expectedMass = 132.03;
        Element C = ElementData.getElement(6);
        Element O = ElementData.getElement(8);
        Molecule molecule = new Molecule();
        molecule.addElement(C, 1);
        molecule.addElement(O, 2);

        double result = FromMolecule.calculateMassInAmountOfSubstance(3, molecule);
        assertEquals(expectedMass, result, DELTA, "fail on CO2");
    }

    @Test
    public void testCalculateMassInAmountOfSubstanceInMethane() throws InvalidElementException {
        // test: CH4
        // 1 mole of CH4 -> 16.05 g
        double expectedMass = 16.05;
        Element C = ElementData.getElement(6);
        Element H = ElementData.getElement(1);
        Molecule molecule = new Molecule();
        molecule.addElement(C, 1);
        molecule.addElement(H, 4);

        double result = FromMolecule.calculateMassInAmountOfSubstance(1, molecule);
        assertEquals(expectedMass, result, DELTA, "fail on CH4");
    }

    @Test
    public void testCalculateMassInAmountOfSubstanceInAmmonia() throws InvalidElementException {
        // test: NH3
        // 5 moles of NH3 -> 85.15 g
        double expectedMass = 85.15;
        Element N = ElementData.getElement(7);
        Element H = ElementData.getElement(1);
        Molecule molecule = new Molecule();
        molecule.addElement(N, 1);
        molecule.addElement(H, 3);

        double result = FromMolecule.calculateMassInAmountOfSubstance(5, molecule);
        assertEquals(expectedMass, result, DELTA, "fail on NH3");
    }

    @Test
    public void testCalculateMassInAmountOfSubstanceInSulfuricAcid() throws InvalidElementException {
        // test: H2SO4
        // 0.5 moles of H2SO4 -> 49.05 g
        double expectedMass = 49.05;
        Element H = ElementData.getElement(1);
        Element S = ElementData.getElement(16);
        Element O = ElementData.getElement(8);
        Molecule molecule = new Molecule();
        molecule.addElement(H, 2);
        molecule.addElement(S, 1);
        molecule.addElement(O, 4);

        double result = FromMolecule.calculateMassInAmountOfSubstance(0.5, molecule);
        assertEquals(expectedMass, result, DELTA, "fail on H2SO4");
    }

    @Test
    public void testCalculateMassInAmountOfSubstanceInGlucose() throws InvalidElementException {
        // test: C6H12O6
        // 2 moles of glucose -> 360.32 g
        double expectedMass = 360.32;
        Element C = ElementData.getElement(6);
        Element H = ElementData.getElement(1);
        Element O = ElementData.getElement(8);
        Molecule molecule = new Molecule();
        molecule.addElement(C, 6);
        molecule.addElement(H, 12);
        molecule.addElement(O, 6);

        double result = FromMolecule.calculateMassInAmountOfSubstance(2, molecule);
        assertEquals(expectedMass, result, DELTA, "fail on C6H12O6");
    }
}
