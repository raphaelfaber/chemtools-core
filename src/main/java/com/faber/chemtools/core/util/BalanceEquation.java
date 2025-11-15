package com.faber.chemtools.core.util;

import com.faber.chemtools.core.entities.Element;
import com.faber.chemtools.core.entities.MoleculeInReaction;
import com.faber.chemtools.core.entities.Reaction;
import com.faber.chemtools.core.exceptions.BalanceEquationFailException;
import com.faber.chemtools.core.exceptions.InvalidReactionException;

import com.faber.chemtools.core.util.externaltools.MatrixHandler;

import java.util.List;

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
     */
    public static void balance(Reaction reaction) throws InvalidReactionException, BalanceEquationFailException {
        int[][] matrix = extractReactionMatrix(reaction);
        int[] coefficients = MatrixHandler.solveMatrix(matrix);
        reaction.setCoefficients(coefficients);
        if(!reaction.isBalanced()) throw new BalanceEquationFailException("Fail during balance");
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

        List<Element> elementsInReaction = reaction.listElementsOnReaction();
        int[][] matrix = new int[elementsInReaction.size()][reagents.size() + products.size()];

        for (int i = 0; i < elementsInReaction.size(); i++) {
            Element element = elementsInReaction.get(i);
            for (int j = 0; j < reagents.size(); j++) {
                matrix[i][j] = reagents.get(j).countAtoms(element);
            }
            for (int j = 0; j < products.size(); j++) {
                matrix[i][reagents.size() + j] =
                        Math.negateExact(products.get(j).countAtoms(element));
            }
        }
        return matrix;
    }
}
