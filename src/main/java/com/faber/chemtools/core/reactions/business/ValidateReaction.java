package com.faber.chemtools.core.reactions.business;

import com.faber.chemtools.core.elements.business.ListElementsFrom;
import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.reactions.entities.Reaction;

import java.util.List;

public class ValidateReaction {
    /**
     * Checks whether the set of chemical elements appearing in the reactants
     * is exactly the same as the set of elements appearing in the products.
     * <p>
     * This comparison ignores the quantity of atoms and the order of elements,
     * focusing only on the presence of element types. For example:
     * <ul>
     *   <li>Reagents: {H, O}, Products: {H, O} → returns true</li>
     *   <li>Reagents: {H, O}, Products: {H, O, Br} → returns false</li>
     *   <li>Reagents: {C, H, O}, Products: {O, H, C} → returns true</li>
     * </ul>
     *
     * @param reaction the chemical reaction containing reagents and products
     * @return true if reagents and products share the exact same set of elements,
     *         false otherwise
     */
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
