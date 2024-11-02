package MathExpr;

import static java.lang.Math.cos;

public class Cos extends Node {
    Variable value;

    Cos(Variable value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "cos(" + value + ")";
    }

    @Override
    double evaluate() {
        return cos(value.evaluate());
    }

    @Override
    Node diff(Variable var) {
        return new Prod(-1, new Sin(var));
    }

    @Override
    boolean isZero() {
        return false;
    }
}
