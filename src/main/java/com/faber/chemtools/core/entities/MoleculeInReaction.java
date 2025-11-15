package com.faber.chemtools.core.entities;

import com.faber.chemtools.core.exceptions.InvalidMoleculeException;

import java.math.BigDecimal;
import java.util.List;

public class MoleculeInReaction {
    Molecule molecule;
    int stoichiometricCoefficient = 1;

    private MoleculeInReaction (){}

    public static MoleculeInReaction factory(String formula, int stoichiometricCoefficient) throws InvalidMoleculeException {
        MoleculeInReaction moleculeInReaction = new MoleculeInReaction();
        moleculeInReaction.molecule = Molecule.Factory(formula);
        moleculeInReaction.stoichiometricCoefficient = stoichiometricCoefficient;
        return moleculeInReaction;
    }
    public static MoleculeInReaction factory(Molecule molecule, int stoichiometricCoefficient) throws InvalidMoleculeException {
        MoleculeInReaction moleculeInReaction = new MoleculeInReaction();
        moleculeInReaction.molecule = molecule;
        moleculeInReaction.stoichiometricCoefficient = stoichiometricCoefficient;
        return moleculeInReaction;
    }

    public void adjustStoichiometricCoefficient(int newStoichiometricCoefficient){
        this.stoichiometricCoefficient = newStoichiometricCoefficient;
    }
    public List<Element> listElements(){
        return molecule.listElements();
    }

    public int countAtoms(Element element){
        return new BigDecimal(molecule.countAtoms(element)).multiply(new BigDecimal(stoichiometricCoefficient)).intValue();
    }
    public double calculateWeight(){
        return new BigDecimal(molecule.calculateMolarWeight()).multiply(BigDecimal.valueOf(stoichiometricCoefficient)).doubleValue();
    }
    @Override
    public String toString(){
        return (stoichiometricCoefficient==1?"":stoichiometricCoefficient)+molecule.toString();
    }
}
