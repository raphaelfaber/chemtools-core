package com.faber.chemtools.core.reactions.business;

import com.faber.chemtools.core.molecules.business.ElementsFromMolecule;
import com.faber.chemtools.core.molecules.entities.Element;
import com.faber.chemtools.core.reactions.entities.Reaction;
import com.faber.chemtools.core.reactions.entities.MoleculeInReaction;

import java.util.ArrayList;
import java.util.List;

public class ElementsFromReaction {
    public static List<Element> retrieveFromMoleculesInReaction(List<MoleculeInReaction> moleculesListInReaction) {
        return moleculesListInReaction
                .stream()
                .map(MoleculeInReaction::getMolecule)
                .map(ElementsFromMolecule::retrieve)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    public static List<Element> listAllElements(Reaction reaction) {
        List<Element> elements = new ArrayList<>(retrieveFromMoleculesInReaction(reaction.getProducts()));
        elements.addAll(retrieveFromMoleculesInReaction(reaction.getReagents()));
        return List.copyOf(elements);
    }

}
