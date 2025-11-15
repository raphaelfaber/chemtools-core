package com.faber.chemtools.core.entities;

import com.faber.chemtools.core.exceptions.InvalidMoleculeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoleculeTest {
    @Test
    @DisplayName("Factory(string) should parse a simple formula: H2O")
    void testSimpleFormula() throws InvalidMoleculeException {
        Molecule m = Molecule.Factory("H2O");

        assertEquals("H2O", m.toString());
        assertEquals(2, m.atoms.size());

        assertEquals("H", m.atoms.get(0).atom.getElement().getSymbol());
        assertEquals(2, m.atoms.get(0).atomicity);

        assertEquals("O", m.atoms.get(1).atom.getElement().getSymbol());
        assertEquals(1, m.atoms.get(1).atomicity);
    }

    @Test
    @DisplayName("Factory(string) should throw exception on empty formula")
    void testEmptyFormula() {
        assertThrows(InvalidMoleculeException.class, () -> Molecule.Factory(""));
        assertThrows(InvalidMoleculeException.class, () -> Molecule.Factory((String) null));
    }

    @Test
    @DisplayName("Factory(string) should parse molecules with multi-letter symbols: NaCl")
    void testTwoLetterSymbols() throws InvalidMoleculeException {
        Molecule m = Molecule.Factory("NaCl");

        assertEquals(2, m.atoms.size());
        assertEquals("Na", m.atoms.get(0).atom.getElement().getSymbol());
        assertEquals("Cl", m.atoms.get(1).atom.getElement().getSymbol());
    }

    @Test
    @DisplayName("Factory(string) should respect atomicity: C6H12O6")
    void testGlucose() throws InvalidMoleculeException {
        Molecule m = Molecule.Factory("C6H12O6");

        assertEquals(3, m.atoms.size());

        assertEquals(6, m.atoms.get(0).atomicity);  // C6
        assertEquals(12, m.atoms.get(1).atomicity); // H12
        assertEquals(6, m.atoms.get(2).atomicity);  // O6
    }

    @Test
    @DisplayName("Factory(string) should accept formulas with parentheses (if FormulaExpander is correct)")
    void testParenthesesExpansion() throws InvalidMoleculeException {
        // Example: Mg(OH)2 => Mg1 O2 H2
        Molecule m = Molecule.Factory("Mg(OH)2");

        assertEquals(3, m.atoms.size());

        assertEquals("Mg", m.atoms.get(0).atom.getElement().getSymbol());
        assertEquals("O", m.atoms.get(1).atom.getElement().getSymbol());
        assertEquals("H", m.atoms.get(2).atom.getElement().getSymbol());

        assertEquals(1, m.atoms.get(0).atomicity); // Mg
        assertEquals(2, m.atoms.get(1).atomicity); // O2
        assertEquals(2, m.atoms.get(2).atomicity); // H2
    }

    @Test
    @DisplayName("Factory(list) should sort atoms using ChemicalFormulaComparator")
    void testFactoryListSorting() {
        AtomInMolecule o = new AtomInMolecule(Atom.factory("O"), 1);
        AtomInMolecule c = new AtomInMolecule(Atom.factory("C"), 1);
        AtomInMolecule h = new AtomInMolecule(Atom.factory("H"), 4);

        List<AtomInMolecule> list = List.of(o, h, c);

        Molecule m = Molecule.Factory(list);

        // Expected order: C H O (Hill system for organics)
        assertEquals("C", m.atoms.get(0).atom.getElement().getSymbol());
        assertEquals("H", m.atoms.get(1).atom.getElement().getSymbol());
        assertEquals("O", m.atoms.get(2).atom.getElement().getSymbol());
    }

    @Test
    @DisplayName("Factory(list) should build formula from atoms")
    void testBuildFormulaFromList() {
        AtomInMolecule c = new AtomInMolecule(Atom.factory("C"), 6);
        AtomInMolecule h = new AtomInMolecule(Atom.factory("H"), 6);

        Molecule m = Molecule.Factory(List.of(c, h));

        assertEquals("C6H6", m.toString());
    }

    @Test
    @DisplayName("toString should return the original formula")
    void testToString() throws InvalidMoleculeException {
        Molecule m = Molecule.Factory("NH3");
        assertEquals("NH3", m.toString());
    }

}