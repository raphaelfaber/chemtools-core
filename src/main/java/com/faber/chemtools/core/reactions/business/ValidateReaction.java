package com.faber.chemtools.core.reactions.business;

import com.faber.chemtools.core.elements.business.ListElementsFrom;
import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.reactions.entities.Reaction;

import java.util.List;

public class ValidateReaction {
    public static boolean hasSameElementsOnReagentsAndProducts(Reaction reaction) {
        List<Element> reagents = ListElementsFrom.moleculesInReaction(reaction.getReagents());
        List<Element> products = ListElementsFrom.moleculesInReaction(reaction.getProducts());
        for (Element reagent : reagents) {
            if (!products.contains(reagent)) {
                return false;
            }
        }
        return true;
    }
}
