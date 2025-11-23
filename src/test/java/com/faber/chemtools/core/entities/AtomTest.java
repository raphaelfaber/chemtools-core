package com.faber.chemtools.core.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtomTest {

    @Test
    void shouldCreateAtomFromSymbol() {
        Atom atom = Atom.factory("C");
        assertEquals(Element.C, atom.element);
        assertEquals("Carbon", atom.getElementName());
    }

    @Test
    void shouldCreateAtomFromAtomicNumber() {
        Atom atom = Atom.factory(8); // O
        assertEquals(Element.O, atom.element);
        assertEquals("Oxygen", atom.getElementName());
    }

    @Test
    void shouldCreateAtomFromElement() {
        Atom atom = Atom.factory(Element.N);
        assertEquals(Element.N, atom.element);
        assertEquals("Nitrogen", atom.getElementName());
    }

    @Test
    void shouldThrowExceptionForInvalidSymbol() {
        assertThrows(IllegalArgumentException.class, () -> Atom.factory("Xx"));
    }

    @Test
    void shouldThrowExceptionForInvalidAtomicNumber() {
        assertThrows(IllegalArgumentException.class, () -> Atom.factory(999));
    }

    @Test
    void atomsFromEquivalentInputsShouldBeConsistent() {
        Atom bySymbol = Atom.factory("H");
        Atom byNumber = Atom.factory(1);
        Atom byElement = Atom.factory(Element.H);

        assertEquals("Hydrogen", bySymbol.getElementName());
        assertEquals(bySymbol.element, byNumber.element);
        assertEquals(byNumber.element, byElement.element);
    }

    @Test
    void atomShouldHoldCorrectElementReference() {
        Element e = Element.Fe;
        Atom atom = Atom.factory(e);
        assertSame(e, atom.element);
        assertEquals("Iron", atom.getElementName());
    }


}