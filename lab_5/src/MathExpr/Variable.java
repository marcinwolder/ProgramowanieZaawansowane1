package MathExpr;

public class Variable extends Node {
    String name;
    Double value;
    Variable(String name){
        this.name = name;
    }
    void setValue(double d){
        value = d;
        // value = d<0?-value:value;
        // sign = d<0?-1:1;
    }

    @Override
    double evaluate() {
        //return sign*value;
        return value;
    }

    @Override
    public String toString() {
        String sgn=sign<0?"-":"";
        return sgn+name;
    }

    Node diff(Variable var) {
        if(var.name.equals(name))return new Constant(sign);
        else return new Constant(0);
    }

    @Override
    boolean isZero() {
        return false;
    }

    public double getValue() {
        return value;
    }
}
