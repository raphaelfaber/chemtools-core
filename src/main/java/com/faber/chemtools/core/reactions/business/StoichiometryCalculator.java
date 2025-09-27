package com.faber.chemtools.core.reactions.business;

import com.faber.chemtools.core.molecules.business.FromMolecule;
import com.faber.chemtools.core.reactions.entities.MoleculeInReaction;
import com.faber.chemtools.core.util.externaltools.MathUtil;

public class StoichiometryCalculator {

    public double calculateProportionalMolesFromMass(MoleculeInReaction referenceMolecule, double referenceMassInGrams, MoleculeInReaction expectedMolecule) {
        double amountOfReferenceInMoles = FromMolecule.calculateAmountOfSubstance(referenceMassInGrams, referenceMolecule.getMolecule());
        return calculateProportionalMolesFromMoles(referenceMolecule, amountOfReferenceInMoles, expectedMolecule);
    }

    public double calculateProportionalMolesFromMoles(MoleculeInReaction referenceMolecule, double amountOfReferenceSubstance, MoleculeInReaction expectedMolecule) {
        return MathUtil.fourthProportional(referenceMolecule.getStoichiometricCoefficient(), expectedMolecule.getStoichiometricCoefficient(), amountOfReferenceSubstance);
    }

    public double calculateProportionalMassFromMass(MoleculeInReaction referenceMolecule, double referenceMassInGrams, MoleculeInReaction expectedMolecule) {
        double amountOfReferenceInMoles = FromMolecule.calculateAmountOfSubstance(referenceMassInGrams, referenceMolecule.getMolecule());
        double amountOfExpectedMoles = calculateProportionalMolesFromMoles(referenceMolecule, amountOfReferenceInMoles, expectedMolecule);

        return FromMolecule.calculateMassInAmountOfSubstance(amountOfExpectedMoles, expectedMolecule.getMolecule());
    }

    public double calculateProportionalMassFromMoles(MoleculeInReaction referenceMolecule, double amountOfReferenceSubstance, MoleculeInReaction expectedMolecule) {
        double amountOfExpectedMoles = calculateProportionalMolesFromMoles(referenceMolecule, amountOfReferenceSubstance, expectedMolecule);

        return FromMolecule.calculateMassInAmountOfSubstance(amountOfExpectedMoles, expectedMolecule.getMolecule());
    }

}
