package com.faber.chemtools.core.molecules.business;

import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.molecules.entities.Molecule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class FromMolecule {
    public static double calculateAmountOfSubstance(double massInGrams, Molecule molecule) {
        BigDecimal mass = BigDecimal.valueOf(massInGrams);
        BigDecimal molMass = BigDecimal.valueOf(calculateMolecularWeight(molecule));
        return mass.divide(molMass, RoundingMode.HALF_UP).doubleValue();
    }

    public static double calculateMolecularWeight(Molecule molecule) {
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

    public static int countAtoms(Molecule molecule, Element element)
    {
        return molecule.readElements().get(element) != null? molecule.readElements().get(element) : 0;
    }
}
