package com.faber.chemtools.core.molecules.business;

import com.faber.chemtools.core.exceptions.InvalidMoleculeException;
import com.faber.chemtools.core.molecules.entities.Molecule;
import com.faber.chemtools.core.util.parser.MoleculeParser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ProcessTest {
    @Test
    @Disabled("It's only example")
    void process() throws InvalidMoleculeException {
        String formula = "HCl";
        double amountInGrams = 200;
        Molecule molecule = MoleculeParser.extract(formula);
        System.out.println("Molecule Weight: " + FromMolecule.calculateMolecularWeight(molecule));
        System.out.println("There are " + FromMolecule.calculateAmountOfSubstance(amountInGrams,molecule)+" moles in "+amountInGrams+"g of "+molecule.getFormula());
    }
}
