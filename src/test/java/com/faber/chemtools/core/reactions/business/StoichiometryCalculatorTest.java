package com.faber.chemtools.core.reactions.business;

import com.faber.chemtools.core.elements.business.ElementData;
import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.molecules.entities.Molecule;
import com.faber.chemtools.core.reactions.entities.MoleculeInReaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoichiometryCalculatorTest {

    private static final double DELTA = 0.1;

    @Nested
    class WaterFormationTest {
        private Element H, O;
        private MoleculeInReaction H2, O2, H2O;

        @BeforeEach
        void setup() throws Exception {
            H = ElementData.getElement("H");
            O = ElementData.getElement("O");

            // 2 H2 + O2 -> 2 H2O
            Molecule mH2 = new Molecule();
            mH2.addElement(H, 2);
            H2 = new MoleculeInReaction(2, mH2);

            Molecule mO2 = new Molecule();
            mO2.addElement(O, 2);
            O2 = new MoleculeInReaction(1, mO2);

            Molecule mH2O = new Molecule();
            mH2O.addElement(H, 2);
            mH2O.addElement(O, 1);
            H2O = new MoleculeInReaction(2, mH2O);
        }

        @Test
        void shouldCalculateProportionalMolesFromMoles() {
            assertEquals(1.0, StoichiometryCalculator.calculateProportionalMolesFromMoles(H2, 2.0, O2), DELTA);
            assertEquals(2.0, StoichiometryCalculator.calculateProportionalMolesFromMoles(H2, 2.0, H2O), DELTA);
        }

        @Test
        void shouldCalculateProportionalMolesFromMass() {
            assertEquals(1.0, StoichiometryCalculator.calculateProportionalMolesFromMass(H2, 4.0, O2), DELTA);
            assertEquals(2.0, StoichiometryCalculator.calculateProportionalMolesFromMass(H2, 4.0, H2O), DELTA);
        }

        @Test
        void shouldCalculateProportionalMassFromMass() {
            assertEquals(36.0, StoichiometryCalculator.calculateProportionalMassFromMass(H2, 4.0, H2O), DELTA);
        }

        @Test
        void shouldCalculateProportionalMassFromMoles() {
            assertEquals(32.0, StoichiometryCalculator.calculateProportionalMassFromMoles(H2, 2.0, O2), DELTA);
        }
    }

    @Nested
    class MethaneCombustionTest {
        private Element H, O, C;
        private MoleculeInReaction CH4, O2, CO2, H2O;

        @BeforeEach
        void setup() throws Exception {
            H = ElementData.getElement("H");
            O = ElementData.getElement("O");
            C = ElementData.getElement("C");

            // CH4 + 2 O2 -> CO2 + 2 H2O
            Molecule mCH4 = new Molecule();
            mCH4.addElement(C, 1);
            mCH4.addElement(H, 4);
            CH4 = new MoleculeInReaction(1, mCH4);

            Molecule mO2 = new Molecule();
            mO2.addElement(O, 2);
            O2 = new MoleculeInReaction(2, mO2);

            Molecule mCO2 = new Molecule();
            mCO2.addElement(C, 1);
            mCO2.addElement(O, 2);
            CO2 = new MoleculeInReaction(1, mCO2);

            Molecule mH2O = new Molecule();
            mH2O.addElement(H, 2);
            mH2O.addElement(O, 1);
            H2O = new MoleculeInReaction(2, mH2O);
        }

        @Test
        void shouldCalculateProportionalMassFromMass() {
            assertEquals(44.0, StoichiometryCalculator.calculateProportionalMassFromMass(CH4, 16.0, CO2), DELTA);
            assertEquals(36.0, StoichiometryCalculator.calculateProportionalMassFromMass(CH4, 16.0, H2O), DELTA);
            assertEquals(44.0, StoichiometryCalculator.calculateProportionalMassFromMass(O2, 64.0, CO2), DELTA);
        }

        @Test
        void shouldCalculateProportionalMolesFromMass() {
            assertEquals(1.0, StoichiometryCalculator.calculateProportionalMolesFromMass(CH4, 16.0, CO2), DELTA);
            assertEquals(2.0, StoichiometryCalculator.calculateProportionalMolesFromMass(CH4, 16.0, H2O), DELTA);
            assertEquals(1.0, StoichiometryCalculator.calculateProportionalMolesFromMass(O2, 64.0, CO2), DELTA);
        }
    }

    @Nested
    class NeutralizationTest {
        private Element H, O, Na, Cl;
        private MoleculeInReaction HCl, NaOH, NaCl, H2O;

        @BeforeEach
        void setup() throws Exception {
            H = ElementData.getElement("H");
            O = ElementData.getElement("O");
            Na = ElementData.getElement("Na");
            Cl = ElementData.getElement("Cl");

            // HCl + NaOH -> NaCl + H2O
            Molecule mHCl = new Molecule();
            mHCl.addElement(H, 1);
            mHCl.addElement(Cl, 1);
            HCl = new MoleculeInReaction(1, mHCl);

            Molecule mNaOH = new Molecule();
            mNaOH.addElement(Na, 1);
            mNaOH.addElement(O, 1);
            mNaOH.addElement(H, 1);
            NaOH = new MoleculeInReaction(1, mNaOH);

            Molecule mNaCl = new Molecule();
            mNaCl.addElement(Na, 1);
            mNaCl.addElement(Cl, 1);
            NaCl = new MoleculeInReaction(1, mNaCl);

            Molecule mH2O = new Molecule();
            mH2O.addElement(H, 2);
            mH2O.addElement(O, 1);
            H2O = new MoleculeInReaction(1, mH2O);
        }

        @Test
        void shouldCalculateProportionalMassFromMass() {
            assertEquals(58.5, StoichiometryCalculator.calculateProportionalMassFromMass(HCl, 36.5, NaCl), DELTA);
            assertEquals(18.0, StoichiometryCalculator.calculateProportionalMassFromMass(NaOH, 40.0, H2O), DELTA);
        }

        @Test
        void shouldCalculateProportionalMolesFromMass() {
            assertEquals(1.0, StoichiometryCalculator.calculateProportionalMolesFromMass(HCl, 36.5, NaCl), DELTA);
            assertEquals(1.0, StoichiometryCalculator.calculateProportionalMolesFromMass(NaOH, 40.0, H2O), DELTA);
        }
    }

    @Nested
    class HaberBoschTest {
        private Element H, N;
        private MoleculeInReaction H2, N2, NH3;

        @BeforeEach
        void setup() throws Exception {
            H = ElementData.getElement("H");
            N = ElementData.getElement("N");

            // N2 + 3 H2 -> 2 NH3
            Molecule mN2 = new Molecule();
            mN2.addElement(N, 2);
            N2 = new MoleculeInReaction(1, mN2);

            Molecule mH2 = new Molecule();
            mH2.addElement(H, 2);
            H2 = new MoleculeInReaction(3, mH2);

            Molecule mNH3 = new Molecule();
            mNH3.addElement(N, 1);
            mNH3.addElement(H, 3);
            NH3 = new MoleculeInReaction(2, mNH3);
        }

        @Test
        void shouldCalculateProportionalMassFromMass() {
            assertEquals(34.0, StoichiometryCalculator.calculateProportionalMassFromMass(N2, 28.0, NH3), DELTA);
            assertEquals(34.0, StoichiometryCalculator.calculateProportionalMassFromMass(H2, 6.0, NH3), DELTA);
        }

        @Test
        void shouldCalculateProportionalMolesFromMass() {
            assertEquals(2.0, StoichiometryCalculator.calculateProportionalMolesFromMass(N2, 28.0, NH3), DELTA);
            assertEquals(2.0, StoichiometryCalculator.calculateProportionalMolesFromMass(H2, 6.0, NH3), DELTA);
        }
    }
}
