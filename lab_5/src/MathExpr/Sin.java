package MathExpr;

import static java.lang.Math.sin;

public class Sin extends Node {
    Variable value;

    Sin(Variable value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "sin(" + value + ")";
    }

    @Override
    double evaluate() {
        return sin(value.evaluate());
    }

    @Override
    Node diff(Variable var) {
        return new Cos(var);
    }

    @Override
    boolean isZero() {
        return false;
    }
}
