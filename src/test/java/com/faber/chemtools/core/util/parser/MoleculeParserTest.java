package com.faber.chemtools.core.util.parser;

import com.faber.chemtools.core.exceptions.InvalidElementException;
import com.faber.chemtools.core.exceptions.InvalidMoleculeException;
import com.faber.chemtools.core.elements.business.ElementData;
import com.faber.chemtools.core.molecules.entities.Molecule;

import org.junit.jupiter.api.Test;

class MoleculeParserTest {
    @Test
    void extractMoleculeTest() throws InvalidElementException, InvalidMoleculeException {
        class TestCase {
            final String formula;
            final int[][] elements; // {atomicNumber, quantity}

            TestCase(String formula, int[][] elements) {
                this.formula = formula;
                this.elements = elements;
            }
        }
        TestCase[] testCases = new TestCase[]{
                new TestCase("H2O", new int[][]{{1, 2}, {8, 1}}),                   // Água
                new TestCase("CO2", new int[][]{{6, 1}, {8, 2}}),                   // Dióxido de carbono
                new TestCase("CH4", new int[][]{{6, 1}, {1, 4}}),                   // Metano
                new TestCase("C2H6", new int[][]{{6, 2}, {1, 6}}),                  // Etano
                new TestCase("Ca(OH)2", new int[][]{{20, 1}, {8, 2}, {1, 2}}),       // Hidróxido de cálcio
                new TestCase("Al2(SO4)3", new int[][]{{13, 2}, {16, 3}, {8, 12}}),   // Sulfato de alumínio
                new TestCase("K4(ON(SO3)2)2", new int[][]{{19, 4}, {8, 14}, {7, 2}, {16, 4}}), // Grupo aninhado expandido
                new TestCase("NaCl", new int[][]{{11, 1}, {17, 1}}),                // Cloreto de sódio
                new TestCase("Mg(OH)2", new int[][]{{12, 1}, {8, 2}, {1, 2}}),       // Hidróxido de magnésio
                new TestCase("C6H12O6", new int[][]{{6, 6}, {1, 12}, {8, 6}}),       // Glicose
                new TestCase("Fe2(SO4)3", new int[][]{{26, 2}, {16, 3}, {8, 12}}),   // Sulfato de ferro(III)
                new TestCase("H2SO4", new int[][]{{1, 2}, {16, 1}, {8, 4}})          // Ácido sulfúrico
        };

        for (TestCase test : testCases) {
            Molecule expected = new Molecule();
            expected.setFormula(test.formula);
            for (int[] e : test.elements) {
                if (e[0] > 0)
                    expected.addElement(ElementData.getElement(e[0]), e[1]);
            }

            Molecule result = MoleculeParser.extract(test.formula);
            assert expected.equals(result) : "Extraction failed for molecule: " + test.formula;
        }
    }
}