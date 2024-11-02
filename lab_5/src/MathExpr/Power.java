package MathExpr;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Power extends Node {
    double p;
    Node arg;
    Power(Node n,double p){
        arg = n;
        this.p = p;
    }

    @Override
    double evaluate() {
        double argVal = arg.evaluate();
        return Math.pow(argVal,p);
    }


    int getArgumentsCount(){return 1;}


    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        if(sign<0)b.append("-");
        int argSign = arg.getSign();
        int cnt = arg.getArgumentsCount();
        boolean useBracket = false;
        if (argSign<0 || cnt>1)
            useBracket = true;
        String argString = arg.toString();
        if(useBracket)b.append("(");
        b.append(argString);
        if(useBracket)b.append(")");
        b.append("^");
        DecimalFormat format = new DecimalFormat("0.#####",new DecimalFormatSymbols(Locale.US));
        b.append(format.format(p));
        return b.toString();
    }

    Node diff(Variable var) {
        Node nextPower;
        if (p-1 == 1) nextPower = arg;
        else nextPower = new Power(arg,p-1);
        Prod r = new Prod(sign*p, nextPower);
        r.mul(arg.diff(var));
        r = r.simplify();
        return r;
    }

    Node expand() {
        if (arg instanceof Sum){
            int len = ((Sum) arg).args.size();
            List<double[]> powersList = powers(len, this.p);

            Sum sum = new Sum();
            for (double[] powers : powersList) {
                Prod p = new Prod();
                int scalar = scalar(this.p, powers);
                p.mul(scalar);

                for (int i = 0; i < len; i++) {
                    if (powers[i]!=0){
                        if (powers[i]!=1) {
                            p.mul(new Power(((Sum) arg).args.get(i), powers[i]));
                        } else p.mul(((Sum) arg).args.get(i));
                    }
                }
                sum.add(p);
            }

            return sum;
        } else return this;
    }

    private List<double[]> powers(int length, double exponent) {
        List<double[]> powers = new ArrayList<>();
        powersRecursive(length, exponent, new double[length], 0, powers);
        return powers;
    }

    private void powersRecursive(int length, double exponentLeft, double[] powers, int powersIndex, List<double[]> result) {
        if (powersIndex == length - 1) {
            powers[powersIndex] = exponentLeft;
            result.add(powers.clone());
        } else {
            for (int i = 0; i <= exponentLeft; i++) {
                powers[powersIndex] = i;
                powersRecursive(length, exponentLeft - i, powers, powersIndex + 1, result);
            }
        }
    }

    private int scalar(double n, double[] ks) {
        int result = factorial((int) n);
        for (double k : ks) {
            result /= factorial((int) k);
        }
        return result;
    }

    private int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    @Override
    boolean isZero() {
        return false;
    }
}
