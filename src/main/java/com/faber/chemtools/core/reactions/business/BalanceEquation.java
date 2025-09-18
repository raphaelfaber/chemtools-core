package com.faber.chemtools.core.reactions.business;

import com.faber.chemtools.core.externaltools.MatrixHandler;
import com.faber.chemtools.core.molecules.business.ElementsFromMolecule;
import com.faber.chemtools.core.molecules.entities.Element;
import com.faber.chemtools.core.reactions.entities.Reaction;
import com.faber.chemtools.core.reactions.entities.ReactionMolecule;

import java.util.*;

public class BalanceEquation {
    public static Reaction balance(Reaction reaction) {
        int[][] matrix = extractReactionMatrix(reaction);
        int[] coefficients = MatrixHandler.solveMatrix(matrix);
        reaction.setCoefficients(coefficients);
        return reaction;
    }

    private static int[][] extractReactionMatrix(Reaction reaction) {
        List<ReactionMolecule> reagents = reaction.getReagents();
        List<ReactionMolecule> products = reaction.getProducts();

        List<Element> elementsInReaction = ElementsFromReaction.listAllElements(reaction);
        int[][] matrix = new int[elementsInReaction.size()][reagents.size() + products.size()];

        for (int i = 0; i < elementsInReaction.size(); i++) {
            Element element = elementsInReaction.get(i);
            for (int j = 0; j < reagents.size(); j++) {
                matrix[i][j] = ElementsFromMolecule.countAtoms(reagents.get(j).getMolecule(), element);
            }
            for (int j = 0; j < products.size(); j++) {
                matrix[i][reagents.size() + j] =
                        Math.negateExact(ElementsFromMolecule.countAtoms(products.get(j).getMolecule(), element));
            }
        }
        return matrix;
    }
}
