package com.faber.chemtools.core.reactions.business;

import com.faber.chemtools.core.elements.business.ListElementsFrom;
import com.faber.chemtools.core.exceptions.InvalidElementException;
import com.faber.chemtools.core.elements.business.ElementData;
import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.molecules.entities.Molecule;
import com.faber.chemtools.core.reactions.entities.MoleculeInReaction;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ElementsFromReactionTest {
    @Test
    void retrieveFromReactiomMoleculesFromMoleculeTest() throws InvalidElementException {
        Element O = ElementData.getElement("O");
        Element Cl = ElementData.getElement("Cl");
        Element H = ElementData.getElement("H");
        List<Element> expected = Arrays.asList(O, H, Cl);

        Molecule molecule = new Molecule();
        molecule.addElement(O, 1);
        molecule.addElement(Cl, 1);
        molecule.addElement(H, 1);
        MoleculeInReaction moleculeInReaction = new MoleculeInReaction(2, molecule);

        List<Element> extracted = ListElementsFrom.moleculesInReaction(List.of(moleculeInReaction));
        expected.forEach(e -> {
            assert extracted.contains(e) : "Element not found" + e.getSimbol();
        });
    }
}