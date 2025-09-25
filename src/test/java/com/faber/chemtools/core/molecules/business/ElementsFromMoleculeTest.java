package com.faber.chemtools.core.molecules.business;

import com.faber.chemtools.core.elements.business.ListElementsFrom;
import com.faber.chemtools.core.elements.business.ElementData;
import com.faber.chemtools.core.exceptions.InvalidElementException;
import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.molecules.entities.Molecule;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElementsFromMoleculeTest {

    @Test
    void shouldDetectElementsCorrectly() throws InvalidElementException {
        Molecule testMolecule = new Molecule();
        Element oxygen = ElementData.getElement("O");
        Element chlorine = ElementData.getElement("Cl");
        Element hydrogen = ElementData.getElement("H");

        testMolecule.addElement(oxygen, 4);
        testMolecule.addElement(hydrogen, 1);
        testMolecule.addElement(chlorine, 1);

        List<Element> expected = Arrays.asList(oxygen, hydrogen, chlorine);
        List<Element> extracted = ListElementsFrom.molecule(testMolecule);

        assertEquals(expected.size(), extracted.size(), "Unexpected number of extracted elements");

        expected.forEach(e ->
                assertTrue(extracted.contains(e), "Element not found: " + e.getSimbol())
        );

        assertEquals(
                4,
                FromMolecule.countAtoms(testMolecule, oxygen),
                "Atom count failed for element " + oxygen
        );
    }

    @Test
    void shouldExtractElementsFromWaterMolecule() throws InvalidElementException {
        Molecule water = new Molecule();
        Element hydrogen = ElementData.getElement("H");
        Element oxygen = ElementData.getElement("O");

        water.addElement(hydrogen, 2);
        water.addElement(oxygen, 1);

        List<Element> extracted = ListElementsFrom.molecule(water);

        assertEquals(2, extracted.size());
        assertTrue(extracted.contains(hydrogen));
        assertTrue(extracted.contains(oxygen));
        assertEquals(2, FromMolecule.countAtoms(water, hydrogen));
        assertEquals(1, FromMolecule.countAtoms(water, oxygen));
    }

    @Test
    void shouldThrowExceptionForInvalidElement() {
        assertThrows(InvalidElementException.class, () -> {
            ElementData.getElement("Xx"); // Nonexistent element
        });
    }


    @Test
    void shouldReturnEmptyListForEmptyMolecule() {
        Molecule empty = new Molecule();

        List<Element> extracted = ListElementsFrom.molecule(empty);

        assertTrue(extracted.isEmpty(), "Expected no elements from an empty molecule");
    }

    @Test
    void shouldCountAtomsInGlucoseMolecule() throws InvalidElementException {
        Molecule glucose = new Molecule();
        Element carbon = ElementData.getElement("C");
        Element hydrogen = ElementData.getElement("H");
        Element oxygen = ElementData.getElement("O");

        glucose.addElement(carbon, 6);
        glucose.addElement(hydrogen, 12);
        glucose.addElement(oxygen, 6);

        assertEquals(6, FromMolecule.countAtoms(glucose, carbon));
        assertEquals(12, FromMolecule.countAtoms(glucose, hydrogen));
        assertEquals(6, FromMolecule.countAtoms(glucose, oxygen));
    }

}
