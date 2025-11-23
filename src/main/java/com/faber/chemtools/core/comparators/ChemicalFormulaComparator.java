package com.faber.chemtools.core.comparators;

import com.faber.chemtools.core.entities.Element;

import java.util.Comparator;

public class ChemicalFormulaComparator implements Comparator<Element> {

    @Override
    public int compare(Element a, Element b) {

        // 1) Metals first
        if (a.isMetal() && !b.isMetal()) return -1;
        if (b.isMetal() && !a.isMetal()) return 1;

        // 2) Inside metals or inside non-metals:
        // Apply Hill ordering

        // 2.1) Carbon first (if present)
        if (a == Element.C && b != Element.C) return -1;
        if (b == Element.C && a != Element.C) return 1;

        // 2.2) Hydrogen second
        if (a == Element.H && b != Element.H) return -1;
        if (b == Element.H && a != Element.H) return 1;

        // 2.3) Alphabetical fallback
        return a.getSymbol().compareTo(b.getSymbol());
    }
}
