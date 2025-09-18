package com.faber.chemtools.core.molecules.business;

import com.faber.chemtools.core.molecules.entities.Element;
import com.faber.chemtools.core.molecules.entities.Molecule;

import java.util.List;

public class ElementsFromMolecule {
    public static List<Element> retrieve(Molecule molecule)
    {
        return molecule
                .readElements()
                .keySet()
                .stream()
                .distinct()
                .toList();
    }

    public static int countAtoms(Molecule molecule, Element element)
    {
        return molecule.readElements().get(element) != null? molecule.readElements().get(element) : 0;
    }
}
