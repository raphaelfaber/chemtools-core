package com.faber.chemtools.core.reactions.business;

import com.faber.chemtools.core.elements.business.ListElementsFrom;
import com.faber.chemtools.core.exceptions.InvalidReactionException;
import com.faber.chemtools.core.molecules.business.FromMolecule;
import com.faber.chemtools.core.util.externaltools.MatrixHandler;
import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.reactions.entities.Reaction;
import com.faber.chemtools.core.reactions.entities.MoleculeInReaction;

import java.util.*;

/**
 * Provides functionality to balance chemical reactions using
 * a matrix-based approach. Each element in the reaction is
 * represented as a row, and each molecule as a column in the matrix.
 *
 * <p>The system of equations derived from the matrix is solved
 * to obtain the stoichiometric coefficients for the reaction.</p>
 */
public class BalanceEquation {

    /**
     * Balances a chemical reaction by determining the stoichiometric coefficients.
     *
     * @param reaction the chemical reaction to balance
     * @return the balanced reaction with coefficients set
     */
    public static Reaction balance(Reaction reaction) throws InvalidReactionException {
        if(!ValidateReaction.hasSameElementsOnReagentsAndProducts(reaction)) throw new InvalidReactionException() ;
        int[][] matrix = extractReactionMatrix(reaction);
        int[] coefficients = MatrixHandler.solveMatrix(matrix);
        reaction.setCoefficients(coefficients);
        return reaction;
    }

    /**
     * Builds the matrix representation of the given reaction, where:
     * <ul>
     *     <li>Rows correspond to the elements present in the reaction.</li>
     *     <li>Columns correspond to the molecules (reagents and products).</li>
     *     <li>Reagent atom counts are positive; product atom counts are negative.</li>
     * </ul>
     *
     * @param reaction the chemical reaction to analyze
     * @return a 2D integer matrix representing the system of equations for balancing
     */
    private static int[][] extractReactionMatrix(Reaction reaction) {
        List<MoleculeInReaction> reagents = reaction.getReagents();
        List<MoleculeInReaction> products = reaction.getProducts();

        List<Element> elementsInReaction = ListElementsFrom.reaction(reaction);
        int[][] matrix = new int[elementsInReaction.size()][reagents.size() + products.size()];

        for (int i = 0; i < elementsInReaction.size(); i++) {
            Element element = elementsInReaction.get(i);
            for (int j = 0; j < reagents.size(); j++) {
                matrix[i][j] = FromMolecule.countAtoms(reagents.get(j).getMolecule(), element);
            }
            for (int j = 0; j < products.size(); j++) {
                matrix[i][reagents.size() + j] =
                        Math.negateExact(FromMolecule.countAtoms(products.get(j).getMolecule(), element));
            }
        }
        return matrix;
    }
}
