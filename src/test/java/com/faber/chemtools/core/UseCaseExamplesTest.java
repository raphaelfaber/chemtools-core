package com.faber.chemtools.core;

import com.faber.chemtools.core.elements.business.ElementData;
import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.exceptions.*;
import com.faber.chemtools.core.molecules.business.FromMolecule;
import com.faber.chemtools.core.molecules.entities.Molecule;
import com.faber.chemtools.core.reactions.business.BalanceEquation;
import com.faber.chemtools.core.reactions.business.FromReaction;
import com.faber.chemtools.core.reactions.business.StoichiometryCalculator;
import com.faber.chemtools.core.reactions.entities.MoleculeInReaction;
import com.faber.chemtools.core.reactions.entities.Reaction;
import com.faber.chemtools.core.util.parser.EquationParser;
import com.faber.chemtools.core.util.parser.MoleculeParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UseCaseExamplesTest {

    @Test
    void obtainingElementInstance() throws InvalidElementException {
        /*
         * There is only one instance of each element. It's read-only and can be obtained through the ElementData class using the getElement method.
         */

        // Using symbol
        Element oxygen = ElementData.getElement("O");
        // Using atomic number
        Element oxygen2 = ElementData.getElement(8);
        Assertions.assertEquals(oxygen, oxygen2);
    }

    @Test
    void creatingMoleculeObjects() throws InvalidElementException, InvalidMoleculeException {
        /*
         * Molecule objects can be created manually or obtained through a parser.
         */

        /* Manually */
        Element H = ElementData.getElement("H");
        Element O = ElementData.getElement("O");

        Molecule molecule = new Molecule();
        molecule.addElement(H,2);
        molecule.addElement(O,1);
        // Note: Manually created molecules do not contain a formula; you should set it manually.
        // Also: toString() uses the formula. If formula is null, it will iterate through elements and print them. The correct order is not guaranteed. For example: H2O may print as OH2 depending on the order added.
        molecule.setFormula("H2O");

        /* Parser */
        Molecule molecule2 = MoleculeParser.extract("H2O");

        // For parsed molecules, the input string is set as the formula.
        Assertions.assertTrue(molecule.equals(molecule2));
    }

    @Test
    void moleculeOperations() throws InvalidMoleculeException, InvalidElementException {
        double expected;
        double actual;
        Molecule H2O = MoleculeParser.extract("H2O");
        // Some operations / info from molecules

        // 1) Count atoms
        Element H = ElementData.getElement("H");
        Element O = ElementData.getElement("O");
        // Atomicity of O in H2O => 1
        int HAtomicity = FromMolecule.countAtoms(H2O, H);
        // Atomicity of H in H2O => 2
        int OAtomicity = FromMolecule.countAtoms(H2O, O);
        Assertions.assertTrue(HAtomicity == 2 && OAtomicity == 1);

        // 2) Calculate molecular weight
        // What is the mass of 1 mol of water?
        expected = 18;
        actual = FromMolecule.calculateMolecularWeight(H2O);
        Assertions.assertEquals(expected,actual,0.2);

        // 3) Calculate moles from mass in grams
        // Example: how many moles are in 36g of water? Expected is 2 moles
        expected = 2;
        actual = FromMolecule.calculateAmountOfSubstance(36,H2O);

        Assertions.assertEquals(expected,actual);

        // 4) Calculate mass in grams from moles
        // How many grams of water are in 3 moles? Expected is 18*3 = 54g approximately.
        expected = 54;
        actual = FromMolecule.calculateMassInAmountOfSubstance(3,H2O);
        Assertions.assertEquals(expected,actual,0.1);

    }

    @Test
    void obtainingChemicalReactionInstance() throws InvalidElementException, InvalidReactionException {
        /*
         * A chemical reaction instance can be created manually or obtained through a parser.
         */
        // Unbalanced reaction
        // H2 + O2 -> H2O

        /* Manually */
        Element H = ElementData.getElement("H");
        Element O = ElementData.getElement("O");

        Molecule H2 = new Molecule();
        H2.addElement(H,2);

        Molecule O2 = new Molecule();
        O2.addElement(O,2);

        Molecule H2O = new Molecule();
        H2O.addElement(O,1);
        H2O.addElement(H,2);
        H2O.setFormula("H2O");

        Reaction reaction = new Reaction();

        reaction.addMultipleReagents(H2,O2);
        reaction.addMultipleProducts(H2O);

        /* Parser */
        Reaction reaction2 = EquationParser.extract("H2 + O2 -> H2O"); // -> , = or => can be used to separate reagents and products

        Assertions.assertEquals(reaction.toString(), reaction2.toString());
    }

    @Test
    void settingEquationCoefficient() throws InvalidReactionException {
        Reaction reaction = EquationParser.extract("H2 + O2 -> H2O");
        // 2 H2 + 1 O2 -> 2 H2O, the coefficients are 2 : 1 : 2
        reaction.setCoefficients(new int[]{2,1,2});

        Assertions.assertEquals("2 H2 + 1 O2 -> 2 H2O", reaction.toString());
    }

    @Test
    void automaticBalanceEquation() throws InvalidReactionException, BalanceEquationFailException {
        // ChemTools can automatically balance equations.

        // Manually balanced
        Reaction expected = EquationParser.extract("H2 + O2 -> H2O");
        expected.setCoefficients(new int[]{2,1,2});

        // Automatically balanced
        Reaction actual = EquationParser.extract("H2 + O2 -> H2O");
        BalanceEquation.balance(actual);

        Assertions.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void obtainMoleculeInReaction() throws BalanceEquationFailException, InvalidReactionException, InvalidMoleculeException, MoleculeNotFoundException {
        // There is an object that represents a molecule in a reaction. Use FromReaction.findMolecule to obtain it.
        Reaction reaction = EquationParser.extract("H2 + O2 -> H2O");
        BalanceEquation.balance(reaction);

        // 1) Using a molecule object
        // Obtain a molecule object instance, then find the molecule in reagents. The finding criteria is the formula.
        Molecule H2Molecule = MoleculeParser.extract("H2");
        MoleculeInReaction H2InReaction = FromReaction.findMolecule(reaction.getReagents(),H2Molecule);

        Assertions.assertEquals("H2", H2InReaction.getMolecule().getFormula());
        Assertions.assertEquals(2, H2InReaction.getStoichiometricCoefficient());

        // 2) Using formula
        H2InReaction = FromReaction.findMolecule(reaction.getReagents(),"H2");
        Assertions.assertEquals("H2", H2InReaction.getMolecule().getFormula());
        Assertions.assertEquals(2, H2InReaction.getStoichiometricCoefficient());
    }

    @Test
    void stoichiometryCalculations() throws InvalidReactionException, BalanceEquationFailException, InvalidMoleculeException, MoleculeNotFoundException {
        /*
         * Once a reaction is balanced, you can use it to perform stoichiometry calculations.
         */
        double tolerance = 0.2;
        double expected;
        double actual;

        Reaction reaction = EquationParser.extract("H2 + O2 -> H2O");
        BalanceEquation.balance(reaction);

        MoleculeInReaction H2InReaction = FromReaction.findMolecule(reaction.getReagents(),MoleculeParser.extract("H2"));
        MoleculeInReaction H2OinReaction = FromReaction.findMolecule(reaction.getProducts(),MoleculeParser.extract("H2O"));

        // 1) Amount in moles of water (H2O) obtained from 4g of H2
        expected = 2;
        actual = StoichiometryCalculator.calculateProportionalMolesFromMass(H2InReaction,4,H2OinReaction);
        Assertions.assertEquals(expected,actual,tolerance);

        // 2) Amount in grams of water (H2O) obtained from 4g of H2
        expected = 36;
        actual = StoichiometryCalculator.calculateProportionalMassFromMass(H2InReaction,4,H2OinReaction);
        Assertions.assertEquals(expected,actual,tolerance);

        // 3) Amount in moles of water (H2O) obtained from 8 moles of H2
        expected = 8;
        actual = StoichiometryCalculator.calculateProportionalMolesFromMoles(H2InReaction,8,H2OinReaction);
        Assertions.assertEquals(expected,actual,tolerance);

        // 4) Amount in grams of water (H2O) obtained from 8 moles of H2
        expected = 144;
        actual = StoichiometryCalculator.calculateProportionalMassFromMoles(H2InReaction,8,H2OinReaction);
        Assertions.assertEquals(expected,actual,tolerance);

    }
}
