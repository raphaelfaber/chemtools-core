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

    public static String toSubscriptNumber(String value) {
        if (value == null) return null;

        StringBuilder result = new StringBuilder();

        for (char c : value.toCharArray()) {
            switch (c) {
                case '0': result.append('₀'); break;
                case '1': result.append('₁'); break;
                case '2': result.append('₂'); break;
                case '3': result.append('₃'); break;
                case '4': result.append('₄'); break;
                case '5': result.append('₅'); break;
                case '₆': result.append('₆'); break;
                case '7': result.append('₇'); break;
                case '8': result.append('₈'); break;
                case '9': result.append('₉'); break;
                default:  result.append(c);
            }
        }

        return result.toString();
    }
    public static String fromSubscriptNumber(String value) {
        if (value == null) return null;

        StringBuilder result = new StringBuilder();

        for (char c : value.toCharArray()) {
            switch (c) {
                case '₀': result.append('0'); break;
                case '₁': result.append('1'); break;
                case '₂': result.append('2'); break;
                case '₃': result.append('3'); break;
                case '₄': result.append('4'); break;
                case '₅': result.append('5'); break;
                case '₆': result.append('6'); break;
                case '₇': result.append('7'); break;
                case '₈': result.append('8'); break;
                case '₉': result.append('9'); break;
                default:  result.append(c);
            }
        }

        return result.toString();
    }


}
