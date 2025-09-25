package com.faber.chemtools.core.reactions.business;

import com.faber.chemtools.core.exceptions.BalanceEquationFailException;
import com.faber.chemtools.core.exceptions.InvalidElementException;
import com.faber.chemtools.core.elements.business.ElementData;
import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.exceptions.InvalidReactionException;
import com.faber.chemtools.core.molecules.entities.Molecule;
import com.faber.chemtools.core.reactions.entities.Reaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalanceEquationTest {

    @Test
    void shouldBalanceWaterFormation() throws InvalidElementException, InvalidReactionException, BalanceEquationFailException {
        Element hydrogen = ElementData.getElement(1);
        Element oxygen = ElementData.getElement(8);

        Molecule h2 = new Molecule();
        h2.addElement(hydrogen, 2);

        Molecule o2 = new Molecule();
        o2.addElement(oxygen, 2);

        Molecule h2o = new Molecule();
        h2o.addElement(hydrogen, 2);
        h2o.addElement(oxygen, 1);

        Reaction reaction = new Reaction();
        reaction.addMultipleReagents(h2o); // Initially wrong side
        reaction.addMultipleProducts(o2, h2);

        Reaction balanced = BalanceEquation.balance(reaction);

        // Expected: 2 H2O → 2 H2 + 1 O2
        assertEquals(2, balanced.getReagents().getFirst().getStoichiometricCoefficient(), "H2O coefficient mismatch");
        assertEquals(1, balanced.getProducts().getFirst().getStoichiometricCoefficient(), "O2 coefficient mismatch");
        assertEquals(2, balanced.getProducts().get(1).getStoichiometricCoefficient(), "H2 coefficient mismatch");
        assertTrue(ValidateReaction.isBalanced(reaction));
    }

    //CH₄ + O₂ → CO₂ + H₂O
    //Balanced: CH₄ + 2 O₂ → CO₂ + 2 H₂O
    @Test
    void shouldBalanceMethaneCombustion() throws InvalidElementException, InvalidReactionException, BalanceEquationFailException {
        Element carbon = ElementData.getElement("C");
        Element hydrogen = ElementData.getElement("H");
        Element oxygen = ElementData.getElement("O");

        Molecule ch4 = new Molecule();
        ch4.addElement(carbon, 1);
        ch4.addElement(hydrogen, 4);

        Molecule o2 = new Molecule();
        o2.addElement(oxygen, 2);

        Molecule co2 = new Molecule();
        co2.addElement(carbon, 1);
        co2.addElement(oxygen, 2);

        Molecule h2o = new Molecule();
        h2o.addElement(hydrogen, 2);
        h2o.addElement(oxygen, 1);

        Reaction reaction = new Reaction();
        reaction.addMultipleReagents(ch4, o2);
        reaction.addMultipleProducts(co2, h2o);

        Reaction balanced = BalanceEquation.balance(reaction);

        assertEquals(1, balanced.getReagents().getFirst().getStoichiometricCoefficient(), "CH4 coefficient mismatch");
        assertEquals(2, balanced.getReagents().get(1).getStoichiometricCoefficient(), "O2 coefficient mismatch");
        assertEquals(1, balanced.getProducts().getFirst().getStoichiometricCoefficient(), "CO2 coefficient mismatch");
        assertEquals(2, balanced.getProducts().get(1).getStoichiometricCoefficient(), "H2O coefficient mismatch");
        assertTrue(ValidateReaction.isBalanced(reaction));
    }

    //N₂ + H₂ → NH₃
    //Balanced: N₂ + 3 H₂ → 2 NH₃
    @Test
    void shouldBalanceAmmoniaFormation() throws InvalidElementException, InvalidReactionException, BalanceEquationFailException {
        Element nitrogen = ElementData.getElement("N");
        Element hydrogen = ElementData.getElement("H");

        Molecule n2 = new Molecule();
        n2.addElement(nitrogen, 2);

        Molecule h2 = new Molecule();
        h2.addElement(hydrogen, 2);

        Molecule nh3 = new Molecule();
        nh3.addElement(nitrogen, 1);
        nh3.addElement(hydrogen, 3);

        Reaction reaction = new Reaction();
        reaction.addMultipleReagents(n2, h2);
        reaction.addMultipleProducts(nh3);

        Reaction balanced = BalanceEquation.balance(reaction);

        assertEquals(1, balanced.getReagents().getFirst().getStoichiometricCoefficient(), "N2 coefficient mismatch");
        assertEquals(3, balanced.getReagents().get(1).getStoichiometricCoefficient(), "H2 coefficient mismatch");
        assertEquals(2, balanced.getProducts().getFirst().getStoichiometricCoefficient(), "NH3 coefficient mismatch");
        assertTrue(ValidateReaction.isBalanced(reaction));
    }

    @Test
    void shouldThrowIfReactionCannotBeBalanced() throws InvalidElementException {
        Reaction impossibleReaction = new Reaction();
        Molecule h2 = new Molecule();
        h2.addElement(ElementData.getElement("H"), 2);

        Molecule o2 = new Molecule();
        o2.addElement(ElementData.getElement("O"), 2);

        // Invalid: H2 → O2
        impossibleReaction.addMultipleReagents(h2);
        impossibleReaction.addMultipleProducts(o2);

        assertThrows(InvalidReactionException.class, () -> BalanceEquation.balance(impossibleReaction));
    }

}
