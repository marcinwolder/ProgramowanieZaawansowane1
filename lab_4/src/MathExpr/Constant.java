package MathExpr;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static java.lang.Math.abs;

public class Constant extends Node {
    double value;
    Constant(double value){
        this.sign = value<0?-1:1;
        this.value = value<0?-value:value;
    }


    @Override
    double evaluate() {
        return sign*value;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if (sign<0) b.append("(-");
        DecimalFormat format = new DecimalFormat("0.#####",new DecimalFormatSymbols(Locale.US));
        b.append(format.format(value));
        if (sign<0) b.append(")");
//        return sgn+Double.toString(value);

        return b.toString();
    }

    @Override
    Node diff(Variable var) {
        return new Constant(0);
    }

    @Override
    boolean isZero() {
        return abs(value) < 0.00001;
    }
}