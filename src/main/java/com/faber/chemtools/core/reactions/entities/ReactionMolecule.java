package com.faber.chemtools.core.reactions.entities;

import com.faber.chemtools.core.molecules.entities.Molecule;

public class ReactionMolecule {
    private int stoichiometricCoefficient;
    private final Molecule molecule;

    public ReactionMolecule(int stoichiometricCoefficient, Molecule molecule) {
        this.molecule = molecule;
        this.stoichiometricCoefficient = stoichiometricCoefficient;
    }

    public int getStoichiometricCoefficient() {
        return stoichiometricCoefficient;
    }

    public Molecule getMolecule() {
        return molecule;
    }

    public void setStoichiometricCoefficient(int stoichiometricCoefficient) {
        this.stoichiometricCoefficient = stoichiometricCoefficient;
    }

}
