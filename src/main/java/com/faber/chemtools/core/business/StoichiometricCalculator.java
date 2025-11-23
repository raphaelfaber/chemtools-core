package com.faber.chemtools.core.business;

import com.faber.chemtools.core.entities.Equation;
import com.faber.chemtools.core.entities.Molecule;
import com.faber.chemtools.core.entities.MoleculeInReaction;
import com.faber.chemtools.core.entities.Reaction;
import com.faber.chemtools.core.exceptions.InvalidMoleculeException;
import com.faber.chemtools.core.exceptions.MoleculeNotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StoichiometricCalculator {
    private static final int DEFAULT_DECIMAL_PLACES = 4;
    private final Equation equation;
    private final Reaction reactionOriginalProportion;
    private Reaction reactionNewProportion;
    private MoleculeInReaction referenceMolecule;

    public StoichiometricCalculator(Equation equation) {
        this.equation = equation;
        reactionOriginalProportion = equation.copyReaction();
        reactionNewProportion = equation.copyReaction();
    }

    public void setReferenceMolecule(String formula) throws InvalidMoleculeException, MoleculeNotFoundException {
        this.referenceMolecule = reactionOriginalProportion.findMoleculeInReaction(Molecule.factory(formula));
    }

    public void setRefenceMoles(double moles) {
        setRefenceMoles(moles, DEFAULT_DECIMAL_PLACES);
    }

    public void setReferenceMass(double mass) {
        setReferenceMass(mass, DEFAULT_DECIMAL_PLACES);
    }

    public void setRefenceMoles(double moles, int decimalPlaces) {
        changeProportion(
                new BigDecimal(referenceMolecule.calculateProportion(moles))
                        .setScale(decimalPlaces, RoundingMode.HALF_UP)
                        .doubleValue()
        );
    }

    public void setReferenceMass(double mass, int decimalPlaces) {
        changeProportion(
                new BigDecimal(
                        referenceMolecule.calculateAmountOfMolesInMass(mass)
                )
                        .setScale(decimalPlaces, RoundingMode.HALF_UP)
                        .doubleValue()
        );
    }

    private void changeProportion(double newProportion) {
        reactionNewProportion = equation.copyReaction();
        reactionNewProportion.getMolecules().forEach(
                molecule -> molecule.setStoichiometricCoefficient(
                        new BigDecimal(molecule.getStoichiometricCoefficient()).multiply(BigDecimal.valueOf(newProportion)).doubleValue()
                ));
    }

    @Override
    public String toString() {
        return reactionNewProportion.toString();
    }

    public double calculateMass(String moleculeFormula) throws InvalidMoleculeException, MoleculeNotFoundException {
        return reactionNewProportion
                .findMoleculeInReaction(Molecule.factory(moleculeFormula))
                .calculateWeight();
    }

    public double calculateMass(String moleculeFormula, int decimalPlaces) throws InvalidMoleculeException, MoleculeNotFoundException {
        return new BigDecimal(calculateMass(moleculeFormula))
                .setScale(decimalPlaces, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public double calculateMoles(String moleculeFormula) throws InvalidMoleculeException, MoleculeNotFoundException {
        return reactionNewProportion
                .findMoleculeInReaction(Molecule.factory(moleculeFormula))
                .getStoichiometricCoefficient();
    }

    public double calculateMoles(String moleculeFormula, int decimalPlaces) throws InvalidMoleculeException, MoleculeNotFoundException {
        return new BigDecimal(calculateMoles(moleculeFormula))
                .setScale(decimalPlaces, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
