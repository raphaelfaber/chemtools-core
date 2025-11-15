package com.faber.chemtools.core.entities;

import com.faber.chemtools.core.exceptions.BalanceEquationFailException;
import com.faber.chemtools.core.exceptions.InvalidReactionException;

public class Equation {
    String strEquation;
    Reaction reaction;

    private Equation() {}

    public static Equation factory(String equation) throws InvalidReactionException {
        Equation object = new Equation();
        object.strEquation = equation;
        object.reaction = Reaction.factory(equation);
        return object;
    }

    public void balance() throws BalanceEquationFailException, InvalidReactionException {
        reaction.balance();
    }

    @Override
    public String toString(){
        return reaction.toString();
    }
}
