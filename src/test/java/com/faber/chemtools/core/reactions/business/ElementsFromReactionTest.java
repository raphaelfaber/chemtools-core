package com.faber.chemtools.core.reactions.business;

import com.faber.chemtools.core.elements.business.ListElementsFrom;
import com.faber.chemtools.core.exceptions.InvalidElementException;
import com.faber.chemtools.core.elements.business.ElementData;
import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.molecules.entities.Molecule;
import com.faber.chemtools.core.reactions.entities.MoleculeInReaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ElementsFromReactionTest {

    private Element O, Cl, H;
    private Molecule molecule;
    private MoleculeInReaction moleculeInReaction;

    @BeforeEach
    void setup() throws InvalidElementException {
        O = ElementData.getElement("O");
        Cl = ElementData.getElement("Cl");
        H = ElementData.getElement("H");

        molecule = new Molecule();
        molecule.addElement(O, 4);
        molecule.addElement(Cl, 1);
        molecule.addElement(H, 1);

        moleculeInReaction = new MoleculeInReaction(2, molecule);
    }

    // --- Basic extraction test ---
    @Test
    void shouldRetrieveAllUniqueElementsFromMolecule() {
        Set<Element> expected = Set.of(O, H, Cl);

        List<Element> extracted = ListElementsFrom.moleculesInReaction(List.of(moleculeInReaction));

        assertEquals(expected.size(), extracted.size(), "Wrong number of elements extracted");
        assertTrue(extracted.containsAll(expected), "Some expected elements were not found");
    }

    // --- Basic atom counting ---
    @Test
    void shouldCountAtomsCorrectly() {
        List<MoleculeInReaction> listOfMoleculesInReaction = List.of(moleculeInReaction);

        assertEquals(8, FromReaction.countAtoms(listOfMoleculesInReaction, O), "Incorrect count for O");
        assertEquals(2, FromReaction.countAtoms(listOfMoleculesInReaction, H), "Incorrect count for H");
        assertEquals(2, FromReaction.countAtoms(listOfMoleculesInReaction, Cl), "Incorrect count for Cl");
    }

    // --- Edge case: empty list ---
    @Test
    void emptyReactionShouldReturnZeroAtoms() {
        List<MoleculeInReaction> empty = List.of();

        assertEquals(0, FromReaction.countAtoms(empty, O));
        assertEquals(0, FromReaction.countAtoms(empty, H));
    }

    @Test
    void emptyReactionShouldReturnNoElements() {
        List<Element> extracted = ListElementsFrom.moleculesInReaction(List.of());

        assertTrue(extracted.isEmpty(), "Empty reaction should produce no elements");
    }

    // --- Edge case: element not present ---
    @Test
    void elementNotPresentShouldReturnZero() throws InvalidElementException {
        Element Na = ElementData.getElement("Na");

        List<MoleculeInReaction> listOfMoleculesInReaction = List.of(moleculeInReaction);

        assertEquals(0, FromReaction.countAtoms(listOfMoleculesInReaction, Na),
                "Nonexistent element should return zero count");
    }

    // --- Multiple molecules ---
    @Test
    void shouldHandleMultipleMoleculesInReaction() throws InvalidElementException {
        Element Na = ElementData.getElement("Na");

        Molecule second = new Molecule();
        second.addElement(Na, 1);
        second.addElement(O, 2);
        MoleculeInReaction secondInReaction = new MoleculeInReaction(3, second);

        List<MoleculeInReaction> reaction = List.of(moleculeInReaction, secondInReaction);

        // total O = 8 (from first) + 6 (from second) = 14
        assertEquals(14, FromReaction.countAtoms(reaction, O));
        // total H = 2 (only in first molecule)
        assertEquals(2, FromReaction.countAtoms(reaction, H));
        // total Cl = 2 (only in first molecule)
        assertEquals(2, FromReaction.countAtoms(reaction, Cl));
        // total Na = 3 (1 in second molecule * coefficient 3)
        assertEquals(3, FromReaction.countAtoms(reaction, Na));

        List<Element> extracted = ListElementsFrom.moleculesInReaction(reaction);
        assertTrue(extracted.containsAll(Set.of(O, H, Cl, Na)), "Should contain all elements from both molecules");
    }

    // --- Coefficient edge case ---
    @Test
    void shouldHandleLargeCoefficients() {
        MoleculeInReaction big = new MoleculeInReaction(1000, molecule);
        List<MoleculeInReaction> reaction = List.of(big);

        // Expect scaling by 1000
        assertEquals(4000, FromReaction.countAtoms(reaction, O));
        assertEquals(1000, FromReaction.countAtoms(reaction, Cl));
        assertEquals(1000, FromReaction.countAtoms(reaction, H));
    }

    // --- Defensive test: molecule with no elements ---
    @Test
    void moleculeWithNoElementsShouldProduceNothing() {
        Molecule emptyMolecule = new Molecule();
        MoleculeInReaction emptyInReaction = new MoleculeInReaction(5, emptyMolecule);

        List<Element> extracted = ListElementsFrom.moleculesInReaction(List.of(emptyInReaction));

        assertTrue(extracted.isEmpty(), "Molecule with no elements should return empty extraction");
    }

    // --- Defensive test: null element query ---
    @Test
    void nullElementShouldThrowException() {
        List<MoleculeInReaction> listOfMoleculesInReaction = List.of(moleculeInReaction);

        assertThrows(NullPointerException.class,
                () -> FromReaction.countAtoms(listOfMoleculesInReaction, null),
                "Null element should throw NullPointerException");
    }
}
