package com.faber.chemtools.core.molecules.business;

import com.faber.chemtools.core.molecules.entities.Element;
import com.faber.chemtools.core.molecules.entities.Molecule;

import java.math.BigDecimal;
import java.util.Map;

public class CalculateMolecularWeight {
    public static double calculate(Molecule molecule) {
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
}
