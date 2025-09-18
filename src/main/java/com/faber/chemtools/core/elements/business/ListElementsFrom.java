package com.faber.chemtools.core.elements.business;

import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.molecules.entities.Molecule;
import com.faber.chemtools.core.reactions.entities.MoleculeInReaction;
import com.faber.chemtools.core.reactions.entities.Reaction;

import java.util.ArrayList;
import java.util.List;

public class ListElementsFrom {
    public static List<Element> molecule(Molecule molecule)
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

    public static List<Element> moleculesInReaction(List<MoleculeInReaction> moleculesListInReaction) {
        return moleculesListInReaction
                .stream()
                .map(MoleculeInReaction::getMolecule)
                .map(ListElementsFrom::molecule)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    public static List<Element> reaction(Reaction reaction) {
        List<Element> elements = new ArrayList<>(moleculesInReaction(reaction.getProducts()));
        elements.addAll(moleculesInReaction(reaction.getReagents()));
        return List.copyOf(elements);
    }
}
