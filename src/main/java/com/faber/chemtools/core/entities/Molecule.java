package com.faber.chemtools.core.entities;

import com.faber.chemtools.core.exceptions.InvalidMoleculeException;
import com.faber.chemtools.core.parser.EquationParser;
import com.faber.chemtools.core.util.FormulaExpander;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Molecule {
    String formula;
    List<AtomInMolecule> atoms = new ArrayList<AtomInMolecule>();

    private Molecule() {}
    public static Molecule factory(String formula) throws InvalidMoleculeException {
        String MOLECULE_PATTERN = "([A-Z][a-z]?)(\\d*)";

        if (formula == null || formula.isEmpty()) {
            throw new InvalidMoleculeException("The formula is empty or null");
        }
        formula = EquationParser.fromSubscriptNumber(formula);
        String expandedFormula = FormulaExpander.expand(formula);

        Pattern pattern = Pattern.compile(MOLECULE_PATTERN);
        Matcher matcher = pattern.matcher(expandedFormula);

        Molecule molecule = new Molecule();
        molecule.formula = EquationParser.toSubscriptNumber(formula);

        while (matcher.find()) {
            Atom atom = Atom.factory(matcher.group(1));
            int atomicity = 1;
            if (matcher.group(2) != null && !matcher.group(2).isEmpty()) {
                atomicity = Integer.parseInt(matcher.group(2));
            }
            AtomInMolecule atomInMolecule = new AtomInMolecule(atom, atomicity);
            molecule.atoms.add(atomInMolecule);
        }
        return molecule;
    }

    public static Molecule factory(List<AtomInMolecule> atoms){
        List<AtomInMolecule> sortedElements = AtomInMolecule.sortElements(atoms);
        Molecule molecule = new Molecule();
        molecule.atoms = sortedElements;
        molecule.formula = sortedElements.stream().map(AtomInMolecule::toString).collect(Collectors.joining(""));
        return molecule;
    }

    public List<Element> listElements(){
        return atoms.stream().map(AtomInMolecule::getElement).collect(Collectors.toList());
    }

    @Override
    public String toString(){
        return formula;
    }

    public int countAtoms(Element element){
        return atoms.stream()
                .mapToInt(atom -> atom.count(element))
                .sum();

    }
    public double calculateMolarWeight() {
        return atoms.stream()
                .map(AtomInMolecule::calculateTotalWeight)
                .reduce(0.0, Double::sum);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Molecule){
            if(((Molecule) o).formula.equals(this.formula)){
                return true;
            }
        }
        return false;
    }
}
