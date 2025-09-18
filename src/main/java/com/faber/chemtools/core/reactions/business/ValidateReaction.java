package com.faber.chemtools.core.reactions.business;

import com.faber.chemtools.core.molecules.entities.Element;
import com.faber.chemtools.core.reactions.entities.Reaction;

import java.util.List;

public class ValidateReaction {
    public static boolean hasSameElementsOnReagentsAndProducts(Reaction reaction) {
        List<Element> reagents = ElementsFromReaction.retrieveFromMoleculesInReaction(reaction.getReagents());
        List<Element> products = ElementsFromReaction.retrieveFromMoleculesInReaction(reaction.getProducts());
        for (Element reagent : reagents) {
            if (!products.contains(reagent)) {
                return false;
            }
        }
        return true;
    }
}
