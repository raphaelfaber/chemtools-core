package com.faber.chemtools.core.reactions.business;

import com.faber.chemtools.core.exceptions.InvalidElementException;
import com.faber.chemtools.core.molecules.business.PeriodicTableList;
import com.faber.chemtools.core.molecules.entities.Element;
import com.faber.chemtools.core.molecules.entities.Molecule;
import com.faber.chemtools.core.reactions.entities.ReactionMolecule;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ElementsFromReactionTest {
    @Test
    void retrieveFromReactiomMoleculesFromMoleculeTest() throws InvalidElementException {
        Element O = PeriodicTableList.getElement("O");
        Element Cl = PeriodicTableList.getElement("Cl");
        Element H = PeriodicTableList.getElement("H");
        List<Element> expected = Arrays.asList(O, H, Cl);

        Molecule molecule = new Molecule();
        molecule.addElement(O, 1);
        molecule.addElement(Cl, 1);
        molecule.addElement(H, 1);
        ReactionMolecule reactionMolecule = new ReactionMolecule(2, molecule);

        List<Element> extracted = ElementsFromReaction.retrieveFromReactiomMolecules(List.of(reactionMolecule));
        expected.forEach(e -> {
            assert extracted.contains(e) : "Element not found" + e.getSimbol();
        });
    }
}