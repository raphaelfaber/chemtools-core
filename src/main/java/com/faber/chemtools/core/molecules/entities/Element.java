package com.faber.chemtools.core.molecules.entities;

public class Element {
    private final String simbol;
    private final String name;
    private final int atomicNumber;
    private final double atomicWeight;

    public Element(String simbol, String name, int atomicNumber, double atomicWeight) {
        this.simbol = simbol;
        this.name = name;
        this.atomicNumber = atomicNumber;
        this.atomicWeight = atomicWeight;
    }

    public String getSimbol() {
        return simbol;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public double getAtomicWeight() {
        return atomicWeight;
    }

    public String toString()
    {
        return simbol;
    }
}
