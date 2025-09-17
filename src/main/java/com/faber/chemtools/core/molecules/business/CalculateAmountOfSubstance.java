package com.faber.chemtools.core.molecules.business;

import com.faber.chemtools.core.molecules.entities.Molecule;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateAmountOfSubstance {
    public static double calculate(double massInGrams, Molecule molecule) {
        BigDecimal mass = BigDecimal.valueOf(massInGrams);
        BigDecimal molMass = BigDecimal.valueOf(CalculateMolecularWeight.calculate(molecule));
        return mass.divide(molMass, RoundingMode.HALF_UP).doubleValue();
    }
}
