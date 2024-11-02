package MathExpr;

import java.util.ArrayList;
import java.util.List;

public class Prod extends Node {
    List<Node> args = new ArrayList<>();

    Prod(){}

    Prod(Node n1){
        args.add(n1);
    }
    Prod(double c){
        this(new Constant(c));
    }

    Prod(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }
    Prod(double c, Node n){
        this(new Constant(c), n);
    }

    Prod mul(Node n){
        args.add(n);
        return this;
    }

    Prod mul(double c){
        args.add(new Constant(c));
        return this;
    }

    @Override
    double evaluate() {
        double result =1;
        for (Node node : this.args) {
            result *= node.evaluate();
        }
        return sign*result;
    }
    int getArgumentsCount(){return args.size();}

    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-");
        for (int i = 0; i < this.args.size(); i++) {
            b.append(this.args.get(i).toString());
            if (!(i == this.args.size() - 1)) {
                b.append("*");
            }
        }
        return b.toString();
    }

    Node diff(Variable var) {
        Sum r = new Sum();
        if (this.isZero()) {
            return new Constant(0);
        }
        for (int i = 0; i < args.size(); i++) {
            Prod m = new Prod();
            if (!args.get(i).diff(var).isZero()) {
                for (int j = 0; j < args.size(); j++) {
                    Node f = args.get(j);
                    if (j == i) {
                        Node temp = f.diff(var);
                        if (!(temp instanceof Constant && ((Constant) temp).value == 1)) {
                            m.mul(temp);
                        }
                    }
                    else m.mul(f);
                }
                m = m.simplify();
                r.add(m);
            }
        }
        return r;
    }

    Prod simplify() {
        double val = 1;
        Prod p = new Prod();
        for (Node node : args) {
            if (node instanceof Constant) {
                val *= ((Constant) node).value * node.sign;
            } else {
                p.mul(node);
            }
        }
        p.mul(new Constant(val));
        return p;
    }

    @Override
    boolean isZero() {
        return args.stream().anyMatch(Node::isZero);
    }
}