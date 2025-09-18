package com.faber.chemtools.core.reactions.business;

import com.faber.chemtools.core.elements.business.ListElementsFrom;
import com.faber.chemtools.core.util.externaltools.MatrixHandler;
import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.reactions.entities.Reaction;
import com.faber.chemtools.core.reactions.entities.MoleculeInReaction;

import java.util.*;

public class BalanceEquation {
    public static Reaction balance(Reaction reaction) {
        int[][] matrix = extractReactionMatrix(reaction);
        int[] coefficients = MatrixHandler.solveMatrix(matrix);
        reaction.setCoefficients(coefficients);
        return reaction;
    }

    private static int[][] extractReactionMatrix(Reaction reaction) {
        List<MoleculeInReaction> reagents = reaction.getReagents();
        List<MoleculeInReaction> products = reaction.getProducts();

        List<Element> elementsInReaction = ListElementsFrom.reaction(reaction);
        int[][] matrix = new int[elementsInReaction.size()][reagents.size() + products.size()];

        for (int i = 0; i < elementsInReaction.size(); i++) {
            Element element = elementsInReaction.get(i);
            for (int j = 0; j < reagents.size(); j++) {
                matrix[i][j] = ListElementsFrom.countAtoms(reagents.get(j).getMolecule(), element);
            }
            for (int j = 0; j < products.size(); j++) {
                matrix[i][reagents.size() + j] =
                        Math.negateExact(ListElementsFrom.countAtoms(products.get(j).getMolecule(), element));
            }
        }
        return matrix;
    }
}
