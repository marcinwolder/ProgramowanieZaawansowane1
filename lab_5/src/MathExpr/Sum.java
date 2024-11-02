package MathExpr;

import java.util.ArrayList;
import java.util.List;

public class Sum extends Node {

    List<Node> args = new ArrayList<>();

    Sum(){}

    Sum(Node n1, Node n2){
        args.add(n1);
        args.add(n2);
    }


    Sum add(Node n){
        args.add(n);
        return this;
    }

    Sum add(double c){
        args.add(new Constant(c));
        return this;
    }

    Sum add(double c, Node n) {
        Node mul = new Prod(c,n);
        args.add(mul);
        return this;
    }

    @Override
    double evaluate() {
        double result = 0;
        for (Node node : this.args) {
            result += node.evaluate();
        }
        return sign * result;
    }

    int getArgumentsCount(){return args.size();}

    public String toString(){
        StringBuilder b =  new StringBuilder();
        if(sign<0)b.append("-(");

        for (int i = 0; i < this.args.size(); i++) {
            b.append(this.args.get(i).toString());
            if (!(i == this.args.size() - 1)) {
                b.append("+");
            }
        }

        if(sign<0)b.append(")");
        return b.toString();
    }

    @Override
    Node diff(Variable var) {
        Sum r = new Sum();
        for(Node n:args){
            Node temp = n.diff(var);
            if(!temp.isZero()) {
                r.add(temp);
            }
        }
        return r;
    }

    @Override
    boolean isZero() {
        return args.stream().allMatch(Node::isZero);
    }
}