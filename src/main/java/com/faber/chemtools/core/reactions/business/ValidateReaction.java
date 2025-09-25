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
     *</p>
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

    /**
     * Checks if a chemical reaction is balanced.
     * <p>
     * A reaction is considered balanced if, for every element present in the reagents,
     * the total number of atoms on the reagent side is equal to the total number
     * of atoms on the product side. This method only verifies elements found in
     * the reagents; if products contain additional elements not present in the reagents,
     * they will not be checked and the reaction may incorrectly be considered balanced.
     * </p>
     * @param reaction the {@link Reaction} to verify
     * @return {@code true} if the reaction is balanced for all elements found in the reagents,
     *         {@code false} otherwise
     */
    public static boolean isBalanced(Reaction reaction) {
        List<Element> elements = ListElementsFrom.moleculesInReaction(reaction.getReagents());
        for (Element element : elements) {
            if(FromReaction.countAtoms(reaction.getReagents(),element) != FromReaction.countAtoms(reaction.getProducts(),element) ){
                return  false;
            }
        }
        return true;
    }
}
