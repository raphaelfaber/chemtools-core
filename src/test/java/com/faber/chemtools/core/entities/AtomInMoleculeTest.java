package com.faber.chemtools.core.entities;

import com.faber.chemtools.core.comparators.ChemicalFormulaComparator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AtomInMoleculeTest {
    @Test
    void testSortElementsShouldSortByChemicalRulesWithoutModifyingOriginal() {
        List<AtomInMolecule> original = new ArrayList<>();
        original.add(new AtomInMolecule(Atom.factory("C"), 1));
        original.add(new AtomInMolecule(Atom.factory("O"), 2));
        original.add(new AtomInMolecule(Atom.factory("Na"), 1));

        List<AtomInMolecule> sorted = AtomInMolecule.sortElements(original);
        assertEquals("C", original.get(0).atom.getElement().getSymbol());
        assertEquals("O", original.get(1).atom.getElement().getSymbol());
        assertEquals("Na", original.get(2).atom.getElement().getSymbol());

        assertEquals("Na", sorted.get(0).atom.getElement().getSymbol());
        assertEquals("C", sorted.get(1).atom.getElement().getSymbol());
        assertEquals("O", sorted.get(2).atom.getElement().getSymbol());

        assertNotSame(original, sorted);
    }
}