package com.faber.chemtools.core.entities;

import com.faber.chemtools.core.comparators.ChemicalFormulaComparator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AtomInMolecule {
    Atom atom;
    int atomicity;

    public AtomInMolecule(Atom atom, int atomicity) {
        this.atom = atom;
        this.atomicity = atomicity;
    }
    @Override
    public String toString(){
        return atom.getSymbol() + (atomicity > 1 ? atomicity : "");
    }

    public static List<AtomInMolecule> sortElements(List<AtomInMolecule> atomInMoleculeList) {
        List<AtomInMolecule> copy = new ArrayList<>(atomInMoleculeList);
        copy.sort(Comparator.comparing(
                atom -> atom.atom.getElement(),
                new ChemicalFormulaComparator()
        ));
        return copy;
    }

    public Element getElement(){
        return atom.getElement();
    }

    public int count(Element element){
        if(atom.element.equals(element)){
            return atomicity;
        }
        return 0;
    }
    public double calculateTotalWeight(){
        return new BigDecimal(atom.getAtomicWeight()).multiply(BigDecimal.valueOf(atomicity)).doubleValue() ;
    }
}
