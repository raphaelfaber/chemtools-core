package com.faber.chemtools.core.entities;

import com.faber.chemtools.core.exceptions.BalanceEquationFailException;
import com.faber.chemtools.core.exceptions.InvalidMoleculeException;
import com.faber.chemtools.core.exceptions.InvalidReactionException;
import com.faber.chemtools.core.exceptions.MoleculeNotFoundException;
import com.faber.chemtools.core.parser.EquationParser;
import com.faber.chemtools.core.util.BalanceEquation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reaction {
    List<MoleculeInReaction> reagents = new ArrayList<>();
    List<MoleculeInReaction> products = new ArrayList<>();
    List<MoleculeInReaction> molecules = new ArrayList<>();

    private Reaction(){}

    public static Reaction factory(String equation) throws InvalidReactionException {
        Reaction reaction = new Reaction();
        String[] reagentsAndProducts = EquationParser.splitReagentsAndProducts(equation);
        try {
            reaction.reagents = EquationParser.extractRawMolecules(reagentsAndProducts[0]);
            reaction.products = EquationParser.extractRawMolecules(reagentsAndProducts[1]);
        } catch (InvalidMoleculeException e) {
            throw new InvalidReactionException("Fail while parsing molecules");
        }
        reaction.validate();
        reaction.molecules = Stream.concat(reaction.reagents.stream(),reaction.products.stream()).toList();
        return reaction;
    }

    public void validate() throws InvalidReactionException {
        if(reagents.isEmpty() && products.isEmpty()){
            throw new InvalidReactionException("There are no reagents or products");
        }

        hasSameElementsOnReagentsAndProducts();
    }
    public void balance() throws BalanceEquationFailException, InvalidReactionException {
        BalanceEquation.balance(this);
    }
    public List<Element> listElementsOnReagents(){
        return listElements(reagents);
    }
    public List<Element> listElementsOnProducts(){
        return listElements(products);
    }

    public List<Element> listElementsOnReaction(){
        return listElements(molecules);
    }

    public List<MoleculeInReaction> getReagents(){
        return reagents;
    }
    public List<MoleculeInReaction> getProducts(){
        return products;
    }
    public List<MoleculeInReaction> getMolecules(){
        return molecules;
    }

    public void setCoefficients(int[] coefficients) {
        if (coefficients.length != this.reagents.size() + this.products.size()) {
            throw new IllegalArgumentException("coefficients must have same length of reagents and products");
        }
        for (int i = 0; i < coefficients.length; i++) {
            if(i < this.reagents.size()) {
                reagents.get(i).setStoichiometricCoefficient(coefficients[i]);
                continue;
            }
            products.get(i-reagents.size()).setStoichiometricCoefficient(coefficients[i]);
        }

    }

    public int countAtomsReagents(Element element){
        return countAtoms(reagents, element);
    }

    public int countAtomsProducts(Element element){
        return countAtoms(products, element);
    }

    public boolean isBalanced() {
        for (Element element:listElementsOnReaction()) {
            if(countAtomsProducts(element) != countAtomsReagents(element) ){
                return  false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        return listToString(reagents)+" => "+listToString(products);
    }

    public MoleculeInReaction findMoleculeInReaction(Molecule molecule) throws MoleculeNotFoundException {
        return molecules.stream()
                .filter(m -> m.equals(molecule))
                .findFirst()
                .orElseThrow(MoleculeNotFoundException::new);
    }


    private String listToString(List<MoleculeInReaction> moleculesInReaction){
        return moleculesInReaction.stream().map(MoleculeInReaction::toString).collect(Collectors.joining(" + "));
    }
    private List<Element> listElements(List<MoleculeInReaction> list){
        return list.stream()
                .map(MoleculeInReaction::listElements)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    private int countAtoms(List<MoleculeInReaction> list, Element element){
        return list.stream().mapToInt( mol -> {
            return mol.countAtoms(element);
        } ).sum();
    }
    private void hasSameElementsOnReagentsAndProducts() throws InvalidReactionException {
        Set<Element> reagents = new HashSet<>(listElementsOnReagents());
        Set<Element> products = new HashSet<>(listElementsOnProducts());

        if (!reagents.equals(products)) {
            throw new InvalidReactionException("Elements are not the same on reagents and products");
        }
    }


}
