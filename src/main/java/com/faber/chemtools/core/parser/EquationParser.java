package com.faber.chemtools.core.parser;

import com.faber.chemtools.core.entities.MoleculeInReaction;
import com.faber.chemtools.core.exceptions.InvalidMoleculeException;
import com.faber.chemtools.core.exceptions.InvalidReactionException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EquationParser {
    public static List<MoleculeInReaction> extractRawMolecules(String reagentsOrProducts) throws InvalidMoleculeException {
        List<MoleculeInReaction> result = new ArrayList<>();
        String[] pieces = reagentsOrProducts.split("\\+");

        for (String raw : pieces) {
            raw = raw.trim();


            raw = raw.replace("(l)", "")
                    .replace("(g)", "")
                    .replace("(aq)", "")
                    .replace("(s)", "");


            String pattern = "(^\\d{0,})(.+?$)";
            Matcher matcher = Pattern.compile(pattern).matcher(raw);

            int coefficient = 1;

            if (matcher.find()) {
                String coefGroup = matcher.group(1);
                if (coefGroup != null && !coefGroup.isEmpty()) {
                    coefficient = Integer.parseInt(coefGroup);
                }

                String formula = matcher.group(2).trim();

                result.add(MoleculeInReaction.factory(formula, coefficient));
            }
        }

        return result;
    }
    public static String[] splitReagentsAndProducts(String equation) throws InvalidReactionException {
        String[] reagentAndProducts;
        if (equation.contains("=>") || equation.contains("->") || equation.contains("=")) {
            reagentAndProducts = equation.split("=>|->|=");
            if (reagentAndProducts.length == 2) {
                return reagentAndProducts;
            }
        }
        throw new InvalidReactionException();
    }

}
