package com.faber.chemtools.core.reactions.business;

import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.molecules.business.FromMolecule;
import com.faber.chemtools.core.reactions.entities.MoleculeInReaction;

import java.util.List;

public class FromReaction {

    public static int countAtoms(List<MoleculeInReaction> reagentsOrProducts, Element element) {
        int totalAtoms = 0;
        for (MoleculeInReaction moleculeInReaction : reagentsOrProducts) {
            totalAtoms += moleculeInReaction.getStoichiometricCoefficient() * FromMolecule.countAtoms(moleculeInReaction.getMolecule(),element);
        }
        return totalAtoms;
    }
}
