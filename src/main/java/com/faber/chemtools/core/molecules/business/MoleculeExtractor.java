package com.faber.chemtools.core.molecules.business;

import com.faber.chemtools.core.exceptions.InvalidElementException;
import com.faber.chemtools.core.exceptions.InvalidMoleculeException;
import com.faber.chemtools.core.molecules.entities.Element;
import com.faber.chemtools.core.molecules.entities.Molecule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoleculeExtractor {
    public static Molecule extract(String formula) throws InvalidMoleculeException {
        if (formula == null || formula.isEmpty()) {
            throw new InvalidMoleculeException();
        }

        String expandedFormula = FormulaExpander.expand(formula);

        String strMoleculePattern = "([A-Z][a-z]?)(\\d*)";
        Pattern pattern = Pattern.compile(strMoleculePattern);
        Matcher matcher = pattern.matcher(expandedFormula);

        Molecule molecule = new Molecule();
        molecule.setFormula(formula);

        try {
            while (matcher.find()) {
                Element element = PeriodicTableList.getElement(matcher.group(1));
                int atomicity = 1;
                if (matcher.group(2) != null && !matcher.group(2).isEmpty()) {
                    atomicity = Integer.parseInt(matcher.group(2));
                }
                molecule.addElement(element, atomicity);
            }
        } catch (InvalidElementException e) {
            throw new InvalidMoleculeException();
        }
        return molecule;
    }
}
