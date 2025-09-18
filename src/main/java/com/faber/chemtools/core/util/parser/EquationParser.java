package com.faber.chemtools.core.util.parser;

import com.faber.chemtools.core.exceptions.InvalidMoleculeException;
import com.faber.chemtools.core.exceptions.InvalidReactionException;
import com.faber.chemtools.core.reactions.business.ValidateReaction;
import com.faber.chemtools.core.reactions.entities.Reaction;
import com.faber.chemtools.core.reactions.entities.MoleculeInReaction;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EquationParser {
    public static Reaction extract(String equation) throws InvalidReactionException {
        if (equation == null || equation.isEmpty()) {
            throw new InvalidReactionException();
        }

        String[] reagentsAndProducts = splitReagentsAndProducts(equation);
        List<MoleculeInReaction> reagents = extractMolecules(reagentsAndProducts[0]);
        List<MoleculeInReaction> products = extractMolecules(reagentsAndProducts[1]);

        Reaction reaction = new Reaction(reagents, products);
        if(!ValidateReaction.hasSameElementsOnReagentsAndProducts(reaction)){
            throw new InvalidReactionException();
        }
        return reaction;
    }

    private static String[] splitReagentsAndProducts(String equation) throws InvalidReactionException {
        String[] reagentAndProducts;
        if (equation.contains("=>")||equation.contains("->")||equation.contains("=")) {
            reagentAndProducts = equation.split("=>|->|=");
            if (reagentAndProducts.length == 2) {
                return reagentAndProducts;
            }
        }
        throw new InvalidReactionException();
    }

    private static List<MoleculeInReaction> extractMolecules(String reagentsOrProducts) {
        List<MoleculeInReaction> molecules = new ArrayList<>();
        String[] stringMolecules = reagentsOrProducts.split("\\+");
        try {
            for (String strMolecule : stringMolecules) {
                strMolecule = strMolecule.trim();
                //remove states if exists
                strMolecule = strMolecule.replace("(l)", "");
                strMolecule = strMolecule.replace("(g)", "");
                strMolecule = strMolecule.replace("(aq)", "");
                strMolecule = strMolecule.replace("(s)", "");

                String moleculeWithCoefficientPattern = "(^\\d{0,})(.+?$)";
                Pattern pattern = Pattern.compile(moleculeWithCoefficientPattern);
                Matcher matcher = pattern.matcher(strMolecule);
                int coefficient = 1;

                if (matcher.find()) {
                    if (matcher.group(1) != null && !matcher.group(1).isEmpty()) {
                        coefficient = Integer.parseInt(matcher.group(1));
                    }

                    molecules.add(new MoleculeInReaction(coefficient, MoleculeParser.extract(matcher.group(2).trim())));
                }

            }
        } catch (InvalidMoleculeException e) {
            throw new RuntimeException(e);
        }
        return molecules;
    }

}

