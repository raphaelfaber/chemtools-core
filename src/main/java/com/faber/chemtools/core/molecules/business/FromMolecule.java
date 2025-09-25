package com.faber.chemtools.core.molecules.business;

import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.molecules.entities.Molecule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * Utility class for performing calculations related to molecules,
 * such as molecular weight, mass, and amount of substance (moles).
 */
public class FromMolecule {

    /**
     * Calculates the amount of substance (in moles) for a given mass of a molecule.
     *
     * @param massInGrams the mass of the substance in grams
     * @param molecule    the molecule whose molar mass will be used
     * @return the amount of substance (in moles)
     */
    public static double calculateAmountOfSubstance(double massInGrams, Molecule molecule) {
        if(massInGrams < 0) throw new IllegalArgumentException("massInGrams should be greater than 0");
        if(molecule.readElements().isEmpty()) throw new IllegalArgumentException("molecule should have at least one element");
        BigDecimal mass = BigDecimal.valueOf(massInGrams);
        BigDecimal molMass = BigDecimal.valueOf(calculateMolecularWeight(molecule));
        return mass.divide(molMass, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Calculates the mass (in grams) for a given amount of substance (moles) of a molecule.
     *
     * @param amountOfSubstance the amount of substance (in moles)
     * @param molecule          the molecule whose molar mass will be used
     * @return the calculated mass in grams
     */
    public static double calculateMassInAmountOfSubstance(double amountOfSubstance, Molecule molecule) {
        if(amountOfSubstance < 0) throw new IllegalArgumentException("amountOfSubstance should be greater than 0");
        if(molecule.readElements().isEmpty()) throw new IllegalArgumentException("molecule should have at least one element");
        BigDecimal moles = BigDecimal.valueOf(amountOfSubstance);
        BigDecimal molMass = BigDecimal.valueOf(calculateMolecularWeight(molecule));
        return moles.multiply(molMass).doubleValue();
    }

    /**
     * Calculates the molecular weight (molar mass) of the given molecule.
     *
     * @param molecule the molecule whose molecular weight will be calculated
     * @return the molecular weight of the molecule in g/mol
     */
    public static double calculateMolecularWeight(Molecule molecule) {
        if(molecule.readElements().isEmpty()) throw new IllegalArgumentException("molecule should have at least one element");
        BigDecimal mass = new BigDecimal("0");
        Map<Element, Integer> elements = molecule.readElements();
        for (Element element : elements.keySet()) {
            int atomicity = elements.get(element);
            BigDecimal atomicWeight = BigDecimal.valueOf(element.getAtomicWeight());
            BigDecimal elementMass = atomicWeight.multiply(BigDecimal.valueOf(atomicity));
            mass = mass.add(elementMass);
        }
        return mass.doubleValue();
    }

    /**
     * Counts the number of atoms of a specific element within a molecule.
     *
     * @param molecule the molecule to analyze
     * @param element  the element whose atoms will be counted
     * @return the number of atoms of the given element in the molecule, or 0 if not present
     */
    public static int countAtoms(Molecule molecule, Element element) {
        return molecule.readElements().get(element) != null ? molecule.readElements().get(element) : 0;
    }
}
