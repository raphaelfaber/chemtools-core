package com.faber.chemtools.core.molecules.business;

import java.util.LinkedHashMap;
import java.util.Map;

public class FormulaExpander {
    public static String expand(String formula) {
        Map<String, Integer> counts;
        counts = parse(formula, new int[]{0});
        StringBuilder result = new StringBuilder();
        counts.forEach((el, q) -> result.append(el).append(q > 1 ? q : ""));
        return result.toString();
    }

    private static Map<String, Integer> parse(String s, int[] pos) {
        Map<String, Integer> counts = new LinkedHashMap<>();

        while (pos[0] < s.length() && s.charAt(pos[0]) != ')') {
            char c = s.charAt(pos[0]);

            if (c == '(') {
                pos[0]++;
                Map<String, Integer> inner = parse(s, pos);
                pos[0]++; // pula ')'
                int mult = readNumber(s, pos);
                inner.forEach((el, q) -> counts.merge(el, q * mult, Integer::sum));
            } else {
                String el = readElement(s, pos);
                int mult = readNumber(s, pos);
                counts.merge(el, mult, Integer::sum);
            }
        }
        return counts;
    }

    private static String readElement(String s, int[] pos) {
        StringBuilder el = new StringBuilder();
        el.append(s.charAt(pos[0]++));
        if (pos[0] < s.length() && Character.isLowerCase(s.charAt(pos[0]))) {
            el.append(s.charAt(pos[0]++));
        }
        return el.toString();
    }

    private static int readNumber(String s, int[] pos) {
        int num = 0;
        while (pos[0] < s.length() && Character.isDigit(s.charAt(pos[0]))) {
            num = num * 10 + (s.charAt(pos[0]++) - '0');
        }
        return num == 0 ? 1 : num;
    }
}
