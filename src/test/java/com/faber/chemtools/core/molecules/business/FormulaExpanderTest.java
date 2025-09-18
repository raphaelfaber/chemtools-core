package com.faber.chemtools.core.molecules.business;

import com.faber.chemtools.core.util.parser.FormulaExpander;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormulaExpanderTest {

    @Test
    void testSimpleFormulas() {
        assertEquals("H2O", FormulaExpander.expand("H2O"));
        assertEquals("CO2", FormulaExpander.expand("CO2"));
    }

    @Test
    void testWithGroups() {
        assertEquals("CaO2H2", FormulaExpander.expand("Ca(OH)2"));
        assertEquals("Al2S3O12", FormulaExpander.expand("Al2(SO4)3"));
    }

    @Test
    void testNestedGroups() {
        assertEquals("K4O14N2S4", FormulaExpander.expand("K4(ON(SO3)2)2"));
    }

    @Test
    void testWithoutExplicitNumbers() {
        assertEquals("NaCl", FormulaExpander.expand("NaCl"));
        assertEquals("CH4", FormulaExpander.expand("CH4"));
    }

    @Test
    void testTwoLetterElements() {
        assertEquals("Fe2O3", FormulaExpander.expand("Fe2O3"));
        assertEquals("MgO2H2", FormulaExpander.expand("Mg(OH)2"));
    }

    @Test
    void testLargeMultipliers() {
        assertEquals("C6H12O6", FormulaExpander.expand("C6H12O6")); // glucose
        assertEquals("Al2O9", FormulaExpander.expand("Al2(O3)3"));
    }
}
