package com.faber.chemtools.core.entities;

import com.faber.chemtools.core.exceptions.InvalidMoleculeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MoleculeTest {

    private static final double DELTA = 0.5;

    @Test
    @DisplayName("Factory(string) should parse a simple formula: H2O")
    void testSimpleFormula() throws InvalidMoleculeException {
        Molecule m = Molecule.factory("H2O");

        assertEquals("H2O", m.toString());
        assertEquals(2, m.atoms.size());

        assertEquals("H", m.atoms.get(0).atom.getElement().getSymbol());
        assertEquals(2, m.atoms.get(0).atomicity);

        assertEquals("O", m.atoms.get(1).atom.getElement().getSymbol());
        assertEquals(1, m.atoms.get(1).atomicity);
    }

    @Test
    void testCalculateMolarWeight_H2O() throws InvalidMoleculeException {
        Molecule water = Molecule.factory("H2O");

        double expected = 2 * 1.00794 + 15.9994;

        assertEquals(expected, water.calculateMolarWeight(), DELTA);
    }

    @Test
    void testCalculateMolarWeight_CO2() throws InvalidMoleculeException {
        Molecule co2 = Molecule.factory("CO2");

        double expected = 12.0107 + (2 * 15.9994);

        assertEquals(expected, co2.calculateMolarWeight(), DELTA);
    }

    @Test
    void testCalculateMolarWeight_C6H12O6() throws InvalidMoleculeException {
        Molecule glucose = Molecule.factory("C6H12O6");

        double expected =
                6 * 12.0107 +
                        12 * 1.00794 +
                        6 * 15.9994;

        assertEquals(expected, glucose.calculateMolarWeight(), DELTA);
    }

    @Test
    void testCalculateMolarWeight_singleAtom() throws InvalidMoleculeException {
        Molecule oxygen = Molecule.factory("O");

        double expected = 15.9994;

        assertEquals(expected, oxygen.calculateMolarWeight(), DELTA);
    }

    @Test
    void testCalculateMolarWeight_complexFormula() throws InvalidMoleculeException {
        Molecule ammoniumSulfate = Molecule.factory("(NH4)2SO4");

        double expected =
                2 * 14.0067 +
                        8 * 1.00794 +
                        32.065 +
                        4 * 15.9994;

        assertEquals(expected, ammoniumSulfate.calculateMolarWeight(), DELTA);
    }

}
