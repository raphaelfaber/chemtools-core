package com.faber.chemtools.core.entities;

import com.faber.chemtools.core.exceptions.BalanceEquationFailException;
import com.faber.chemtools.core.exceptions.InvalidReactionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquationTest {
    @Test
    void factory_ShouldCreateEquationCorrectly() throws InvalidReactionException {
        String input = "H2 + O2 => H2O";

        Equation eq = Equation.factory(input);

        assertNotNull(eq);
        assertEquals(input, eq.strEquation);
        assertNotNull(eq.reaction);
    }

    @Test
    void balance_ShouldNotThrow_ForSimpleEquation() {
        assertDoesNotThrow(() -> {
            Equation eq = Equation.factory("H2 + O2 => H2O");
            eq.balance();
        });
    }
    @Test
    void balance_ShouldThrow_ForWrongEquation() {
        assertThrows(Exception.class, () -> {
            Equation eq = Equation.factory("H2 => H2O");
            eq.balance();
        });
    }

    @Test
    public void testBalanceWaterFormation() throws InvalidReactionException, BalanceEquationFailException {
        Equation eq = Equation.factory("H2 + O2 => H2O");

        eq.balance();

        assertEquals("2H2 + O2 => 2H2O", eq.toString());
    }

    @Test
    public void testBalanceCO2Formation() throws Exception {
        Equation eq = Equation.factory("C + O2 => CO2");

        eq.balance();

        assertEquals("C + O2 => CO2", eq.toString());
    }
}