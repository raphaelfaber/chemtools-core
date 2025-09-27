package com.faber.chemtools.core.reactions.business;

import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.exceptions.MoleculeNotFoundException;
import com.faber.chemtools.core.molecules.business.FromMolecule;
import com.faber.chemtools.core.molecules.entities.Molecule;
import com.faber.chemtools.core.reactions.entities.MoleculeInReaction;

import java.util.List;

public class FromReaction {

    /**
     * Counts the total number of atoms of a specific element
     * in a list of molecules participating in a reaction.
     *
     * @param reagentsOrProducts the list of molecules in the reaction (reagents or products)
     * @param element the chemical element to count
     * @return the total number of atoms of the specified element
     */
    public static int countAtoms(List<MoleculeInReaction> reagentsOrProducts, Element element) {
        int totalAtoms = 0;
        for (MoleculeInReaction moleculeInReaction : reagentsOrProducts) {
            totalAtoms += moleculeInReaction.getStoichiometricCoefficient() * FromMolecule.countAtoms(moleculeInReaction.getMolecule(),element);
        }
        return totalAtoms;
    }

    /**
     * Finds a specific molecule in a list of molecules participating in a reaction.
     * The search is based on the molecular formula.
     *
     * @param reagentsOrProducts the list of molecules in the reaction (reagents or products)
     * @param molecule the molecule to find
     * @return the MoleculeInReaction object corresponding to the given molecule
     * @throws MoleculeNotFoundException if the molecule is not found in the list
     */
    public MoleculeInReaction findMolecule(List<MoleculeInReaction> reagentsOrProducts, Molecule molecule) throws MoleculeNotFoundException {
        return reagentsOrProducts.stream()
                .filter(moleculeInReaction -> moleculeInReaction.getMolecule().getFormula() != null && !moleculeInReaction.getMolecule().getFormula().isEmpty())
                .filter(moleculeInReaction -> moleculeInReaction.getMolecule().getFormula().equals(molecule.getFormula()))
                .distinct()
                .findFirst()
                .orElseThrow(MoleculeNotFoundException::new);
    }
}
