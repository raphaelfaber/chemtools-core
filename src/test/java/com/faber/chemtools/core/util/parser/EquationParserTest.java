package com.faber.chemtools.core.util.parser;

import com.faber.chemtools.core.exceptions.BalanceEquationFailException;
import com.faber.chemtools.core.exceptions.InvalidReactionException;
import com.faber.chemtools.core.reactions.business.BalanceEquation;
import com.faber.chemtools.core.reactions.entities.Reaction;
import org.junit.jupiter.api.Test;

class EquationParserTest {

    @Test
    void extractTest() throws InvalidReactionException {
        String expected ="1 H2 + 1 O2 -> 1 H2O";
        Reaction reaction = EquationParser.extract(expected);
        String result = reaction.toString();
        assert expected.equals(result) : "Expected:"+expected+", actual:"+result;

        expected ="1 HCl + 1 NaOH -> 1 NaCl + 1 H2O";
        reaction = EquationParser.extract(expected);
        result = reaction.toString();
        assert expected.equals(result) : "Expected:"+expected+", actual:"+result;

        expected ="1 Ca(OH)2 + 2 HCl -> 1 CaCl2 + 2 H2O";
        reaction = EquationParser.extract(expected);
        result = reaction.toString();
        assert expected.equals(result) : "Expected:"+expected+", actual:"+result;
    }

    @Test
    void extractTestAndBalance() throws InvalidReactionException, BalanceEquationFailException {
        String expected ="1 Ca(OH)2 + 2 HCl -> 1 CaCl2 + 2 H2O";
        String testCase ="Ca(OH)2 + HCl -> CaCl2 + H2O";
        Reaction reaction = EquationParser.extract(testCase);
        BalanceEquation.balance(reaction);
        String result = reaction.toString();
        assert expected.equals(result) : "Expected:"+expected+", actual:"+result;
    }

    @Test
    void extractTestAndBalance1() throws InvalidReactionException, BalanceEquationFailException {
        String expected = "2 H2 + 1 O2 -> 2 H2O";
        String testCase = "H2 + O2 -> H2O";
        Reaction reaction = EquationParser.extract(testCase);
        BalanceEquation.balance(reaction);
        String result = reaction.toString();
        assert expected.equals(result) : "Expected:" + expected + ", actual:" + result;
    }

    @Test
    void extractTestAndBalance2() throws InvalidReactionException, BalanceEquationFailException {
        String expected = "1 N2 + 3 H2 -> 2 NH3";
        String testCase = "N2 + H2 -> NH3";
        Reaction reaction = EquationParser.extract(testCase);
        BalanceEquation.balance(reaction);
        String result = reaction.toString();
        assert expected.equals(result) : "Expected:" + expected + ", actual:" + result;
    }

    @Test
    void extractTestAndBalance3() throws InvalidReactionException, BalanceEquationFailException {
        String expected = "1 CaCO3 -> 1 CaO + 1 CO2";
        String testCase = "CaCO3 -> CaO + CO2";
        Reaction reaction = EquationParser.extract(testCase);
        BalanceEquation.balance(reaction);
        String result = reaction.toString();
        assert expected.equals(result) : "Expected:" + expected + ", actual:" + result;
    }

    @Test
    void extractTestAndBalance4() throws InvalidReactionException, BalanceEquationFailException {
        String expected = "2 KClO3 -> 2 KCl + 3 O2";
        String testCase = "KClO3 -> KCl + O2";
        Reaction reaction = EquationParser.extract(testCase);
        BalanceEquation.balance(reaction);
        String result = reaction.toString();
        assert expected.equals(result) : "Expected:" + expected + ", actual:" + result;
    }

    @Test
    void extractTestAndBalance5() throws InvalidReactionException, BalanceEquationFailException {
        String expected = "2 Al + 6 HCl -> 2 AlCl3 + 3 H2";
        String testCase = "Al + HCl -> AlCl3 + H2";
        Reaction reaction = EquationParser.extract(testCase);
        BalanceEquation.balance(reaction);
        String result = reaction.toString();
        assert expected.equals(result) : "Expected:" + expected + ", actual:" + result;
    }

    @Test
    void extractTestAndBalanceComplex1() throws InvalidReactionException, BalanceEquationFailException {
        String expected = "2 KMnO4 + 16 HCl -> 2 KCl + 2 MnCl2 + 5 Cl2 + 8 H2O";
        String testCase = "KMnO4 + HCl -> KCl + MnCl2 + Cl2 + H2O";
        Reaction reaction = EquationParser.extract(testCase);
        BalanceEquation.balance(reaction);
        String result = reaction.toString();
        assert expected.equals(result) : "Expected:" + expected + ", actual:" + result;
    }

    @Test
    void extractTestAndBalanceComplex2() throws InvalidReactionException, BalanceEquationFailException {
        String expected = "4 FeCl3 + 3 K2Cr2O7 + 9 H2SO4 -> 2 Fe2(SO4)3 + 3 K2SO4 + 6 CrO2Cl2 + 9 H2O";
        String testCase = "FeCl3 + K2Cr2O7 + H2SO4 -> Fe2(SO4)3 + K2SO4 + CrO2Cl2 + H2O";
        Reaction reaction = EquationParser.extract(testCase);
        BalanceEquation.balance(reaction);
        String result = reaction.toString();
        assert expected.equals(result) : "Expected:" + expected + ", actual:" + result;
    }

    @Test
    void extractTestAndBalanceComplex3() throws InvalidReactionException, BalanceEquationFailException {
        String expected = "2 Fe2O3 + 3 C -> 4 Fe + 3 CO2";
        String testCase = "Fe2O3 + C -> Fe + CO2";
        Reaction reaction = EquationParser.extract(testCase);
        BalanceEquation.balance(reaction);
        String result = reaction.toString();
        assert expected.equals(result) : "Expected:" + expected + ", actual:" + result;
    }

    @Test
    void extractTestAndBalanceComplex4() throws InvalidReactionException, BalanceEquationFailException {
        String expected = "1 Ba(OH)2 + 1 H3PO4 -> 1 BaHPO4 + 2 H2O";
        String testCase = "Ba(OH)2 + H3PO4 = BaHPO4 + H2O";
        Reaction reaction = EquationParser.extract(testCase);
        BalanceEquation.balance(reaction);
        String result = reaction.toString();
        assert expected.equals(result) : "Expected:" + expected + ", actual:" + result;
    }

    @Test
    void extractTestAndBalanceComplex5() throws InvalidReactionException, BalanceEquationFailException {
        String expected = "4 Al + 3 O2 -> 2 Al2O3";
        String testCase = "Al + O2 -> Al2O3";
        Reaction reaction = EquationParser.extract(testCase);
        BalanceEquation.balance(reaction);
        String result = reaction.toString();
        assert expected.equals(result) : "Expected:" + expected + ", actual:" + result;
    }


}