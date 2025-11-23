package com.faber.chemtools.core.entities;

import com.faber.chemtools.core.exceptions.InvalidMoleculeException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class MoleculeInReaction {
    private Molecule molecule;
    private double stoichiometricCoefficient = 1;

    private MoleculeInReaction (){}

    public static MoleculeInReaction factory(String formula, int stoichiometricCoefficient) throws InvalidMoleculeException {
        MoleculeInReaction moleculeInReaction = new MoleculeInReaction();
        moleculeInReaction.molecule = Molecule.factory(formula);
        moleculeInReaction.stoichiometricCoefficient = stoichiometricCoefficient;
        return moleculeInReaction;
    }
    public static MoleculeInReaction factory(Molecule molecule, int stoichiometricCoefficient) throws InvalidMoleculeException {
        MoleculeInReaction moleculeInReaction = new MoleculeInReaction();
        moleculeInReaction.molecule = molecule;
        moleculeInReaction.stoichiometricCoefficient = stoichiometricCoefficient;
        return moleculeInReaction;
    }

    public void setStoichiometricCoefficient(double newStoichiometricCoefficient){
        this.stoichiometricCoefficient = newStoichiometricCoefficient;
    }

    public double getStoichiometricCoefficient() {
        return stoichiometricCoefficient;
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
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.########");
        String coefStr = df.format(stoichiometricCoefficient);

        return (stoichiometricCoefficient == 1 ? "" : coefStr) + molecule.toString();
    }
    public boolean equals(Molecule molecule){
        return this.molecule.equals(molecule);
    }

    public double calculateProportion(double moles){
        return new BigDecimal(moles).divide(new BigDecimal(stoichiometricCoefficient),10, RoundingMode.HALF_UP).doubleValue();
    }

    public double calculateAmountOfMolesInMass(double mass){
        return new BigDecimal(mass).divide(new BigDecimal(molecule.calculateMolarWeight()),10, RoundingMode.HALF_UP).doubleValue();
    }
}
