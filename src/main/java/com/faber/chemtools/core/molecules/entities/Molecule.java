package com.faber.chemtools.core.molecules.entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Molecule {
    String formula;
    Map<Element, Integer> elements = new HashMap<>();

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public void addElement(Element element, int atomicity) {
        if (elements.containsKey(element)) {
            elements.put(element, elements.get(element) + atomicity);
            return;
        }
        elements.put(element, atomicity);
    }

    public Map<Element, Integer> readElements() {
        return Collections.unmodifiableMap(elements);
    }

    public boolean equals(Molecule molecule) {
        if (!this.formula.equals(molecule.getFormula())) {
            return false;
        }
        if (this.elements.size() != molecule.elements.size()) {
            return false;
        }
        for (Map.Entry<Element, Integer> entry : this.elements.entrySet()) {
            if (!Objects.equals(entry.getValue(), molecule.elements.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }
}
