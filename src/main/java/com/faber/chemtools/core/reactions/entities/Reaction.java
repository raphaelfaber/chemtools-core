package com.faber.chemtools.core.reactions.entities;

import com.faber.chemtools.core.molecules.entities.Molecule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reaction {
    List<ReactionMolecule> reagents = new ArrayList<>();
    List<ReactionMolecule> products = new ArrayList<>();

    public Reaction() {

    }

    public Reaction(List<ReactionMolecule> reagents, List<ReactionMolecule> products) {
        this.reagents = reagents;
        this.products = products;
    }

    public void addMultipleReagents(Molecule...reagents) {
        Stream.of(reagents).forEach(this::addReagent);
    }

    public void addMultipleProducts(Molecule...products) {
        Stream.of(products).forEach(this::addProduct);
    }

    public void addReagent(Molecule reagent) {
        this.reagents.add(new ReactionMolecule(1,reagent));
    }

    public void addProduct(Molecule product) {
        this.products.add(new ReactionMolecule(1,product));
    }

    public List<ReactionMolecule> getReagents() {
        return this.reagents;
    }

    public List<ReactionMolecule> getProducts() {
        return this.products;
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

    public String toString(){
        String reagentsString = this.reagents.stream()
                .map( reactionMolecule -> reactionMolecule.getStoichiometricCoefficient()+" "+reactionMolecule.getMolecule().toString() )
                .collect(Collectors.joining(" + "));
        String productsString = this.products.stream()
                .map( reactionMolecule -> reactionMolecule.getStoichiometricCoefficient()+" "+reactionMolecule.getMolecule().toString() )
                .collect(Collectors.joining(" + "));
        return reagentsString +" -> "+ productsString;
    }
}
