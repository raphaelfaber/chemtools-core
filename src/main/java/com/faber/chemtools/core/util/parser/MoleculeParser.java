package com.faber.chemtools.core.util.parser;

import com.faber.chemtools.core.exceptions.InvalidElementException;
import com.faber.chemtools.core.exceptions.InvalidMoleculeException;
import com.faber.chemtools.core.elements.business.ElementData;
import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.molecules.entities.Molecule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoleculeParser {
    public static final String MOLECULE_PATTERN = "([A-Z][a-z]?)(\\d*)";

    public static Molecule extract(String formula) throws InvalidMoleculeException {
        if (formula == null || formula.isEmpty()) {
            throw new InvalidMoleculeException("The formula is empty or null");
        }

        String expandedFormula = FormulaExpander.expand(formula);

        Pattern pattern = Pattern.compile(MOLECULE_PATTERN);
        Matcher matcher = pattern.matcher(expandedFormula);

        Molecule molecule = new Molecule();
        molecule.setFormula(formula);

        try {
            while (matcher.find()) {
                Element element = ElementData.getElement(matcher.group(1));
                int atomicity = 1;
                if (matcher.group(2) != null && !matcher.group(2).isEmpty()) {
                    atomicity = Integer.parseInt(matcher.group(2));
                }
                molecule.addElement(element, atomicity);
            }
        } catch (InvalidElementException e) {
            throw new InvalidMoleculeException("Failed to parse formula "+formula+" as molecule");
        }
        return molecule;
    }

}
