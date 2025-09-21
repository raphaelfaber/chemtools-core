package com.faber.chemtools.core.reactions.business;

import com.faber.chemtools.core.exceptions.InvalidElementException;
import com.faber.chemtools.core.elements.business.ElementData;
import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.molecules.entities.Molecule;
import com.faber.chemtools.core.reactions.entities.Reaction;
import org.junit.jupiter.api.Test;

class BalanceEquationTest {
    @Test
    void waterFormationTest() throws InvalidElementException {
        Element H = ElementData.getElement(1);
        Element O = ElementData.getElement(8);

        Molecule H2 = new Molecule();
        H2.addElement(H,2);

        Molecule O2 = new Molecule();
        O2.addElement(O,2);

        Molecule H2O = new Molecule();
        H2O.addElement(H,2);
        H2O.addElement(O,1);


        Reaction reaction = new Reaction();
        reaction.addMultipleReagents(H2O);
        reaction.addMultipleProducts(O2,H2);

        Reaction balanced = BalanceEquation.balance(reaction);
        //2 H2O
        assert balanced.getReagents().getFirst().getStoichiometricCoefficient() == 2;

        //1 O2
        assert balanced.getProducts().getFirst().getStoichiometricCoefficient() == 1;

        //2 H2
        assert balanced.getProducts().get(1).getStoichiometricCoefficient() == 2;

    }
}