package com.faber.chemtools.core.entities;

public class Atom {
    Element element;
    double atomicWeight;
    private Atom() {}

    public static Atom factory(String symbol){
        Atom atom = new Atom();
        atom.element = Element.fromSymbol(symbol);
        return atom;
    }
    public static Atom factory(int atomicNumber){
        Atom atom = new Atom();
        atom.element = Element.fromAtomicNumber(atomicNumber);
        return atom;
    }

    public static Atom factory(Element element){
        Atom atom = new Atom();
        atom.element = element;
        return atom;
    }

    public String getElementName(){
        return element.getName();
    }
    public Element getElement(){
        return element;
    }
    public String getSymbol(){
        return element.getSymbol();
    }
    public double getAtomicWeight(){
        return atomicWeight;
    }
}
