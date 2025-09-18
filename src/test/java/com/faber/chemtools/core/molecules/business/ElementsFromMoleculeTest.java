package com.faber.chemtools.core.molecules.business;

import com.faber.chemtools.core.elements.business.ListElementsFrom;
import com.faber.chemtools.core.elements.business.ElementData;
import com.faber.chemtools.core.exceptions.InvalidElementException;
import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.molecules.entities.Molecule;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ElementsFromMoleculeTest {
    @Test
    void detectElementsTest() throws InvalidElementException {
        Molecule moleculeTest = new Molecule();
        Element O = ElementData.getElement("O");
        Element Cl = ElementData.getElement("Cl");
        Element H = ElementData.getElement("H");
        moleculeTest.addElement(O,4);
        moleculeTest.addElement(H,1);
        moleculeTest.addElement(Cl,1);

        List<Element> expected = Arrays.asList(O,H,Cl);
        List<Element> extracted = ListElementsFrom.molecule(moleculeTest);

        assert expected.size() == extracted.size();

        expected.forEach(e-> {
            assert extracted.contains(e) : "Element not found"+ e.getSimbol();
        });

        assert FromMolecule.countAtoms(moleculeTest,O) == 4 : "Element "+O.toString()+" count failed. Expected:"+4+" real count:"+ FromMolecule.countAtoms(moleculeTest,O);
    }

}