package MathExpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static java.lang.Math.abs;

public class Tests {
    public static void buildAndPrint(){
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2.1,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.println(exp.toString());
    }
    public static void buildAndEvaluate() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(new Power(x, 3))
                .add(-2, new Power(x, 2))
                .add(-1, x)
                .add(2);
        List<Double> roots = new ArrayList<Double>();
        for (double v = -5; v < 5; v += 0.1) {
            x.setValue(v);
            double y =  exp.evaluate();
            if (abs(y - 0) < 0.00001) {
                roots.add(v);
            }
        }
        for (Double root : roots) {
            System.out.printf("f(%f)=%f\n", root, 0.0);
        }
    }
    public static void defineCircle(){
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.println(circle.toString());

        List<Point> points = new ArrayList<Point>();
        while (points.size() != 100) {
            double xv = 100*(Math.random()-.5);
            double yv = 100*(Math.random()-.5);
            x.setValue(xv);
            y.setValue(yv);
            double fv = circle.evaluate();
            if (fv<0) {
                points.add(new Point(xv,yv));
            }
        }
        System.out.println(points);
    }

    public static void diffPoly() {
        Variable x = new Variable("x");
        Node exp = new Sum()
                .add(2,new Power(x,3))
                .add(new Power(x,2))
                .add(-2,x)
                .add(7);
        System.out.print("exp=");
        System.out.println(exp.toString());

        Node d = exp.diff(x);
        System.out.print("d(exp)/dx=");
        System.out.println(d.toString());

    }

    public static void diffCircle() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Node circle = new Sum()
                .add(new Power(x,2))
                .add(new Power(y,2))
                .add(8,x)
                .add(4,y)
                .add(16);
        System.out.print("f(x,y)=");
        System.out.println(circle.toString());

        Node dx = circle.diff(x);
        System.out.print("d f(x,y)/dx=");
        System.out.println(dx.toString());
        System.out.print("d f(x,y)/dy=");
        Node dy = circle.diff(y);
        System.out.println(dy.toString());
    }

    public static void newFunctions() {
        Variable x = new Variable("x");
        Node expr = new Sin(x);
        System.out.print("f(x)=");
        System.out.println(expr);
        System.out.print("d f(x)/dx=");
        System.out.println(expr.diff(x));
        System.out.print("d f(x)/d^2x=");
        System.out.println(expr.diff(x).diff(x));
    }

    public static void multinomialTheorem() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Variable z = new Variable("z");
        Power expr = new Power(new Sum(x, y).add(z), 3);
        System.out.print("(x+y+z)^3=");
        System.out.println(expr.expand().toString());
    }

    public static void testOptimization(){
        // rozmiar problemu
        int n=10;
        // hiperparametry
        int maxIter=1000;
        double learningRate=0.1;

        Random rand = new Random();
        // Utwórz zmienne
        List<Variable> vars= new ArrayList<>();
        for(int i=0;i<n;i++){
            vars.add(new Variable("var"+i));
        }

        // Zdefiniuj funkcję celu jako sum(a_i*x_i^2)
        Sum goal = new Sum();
        for(int i=0;i<n;i++){
            double a = rand.nextDouble();
            Node b = new Power(vars.get(i),2);
            goal.add(new Prod(new Constant(a),b));
        }

        // Wyznacz symbolicznie gradienty funkcji celu względem zmiennych x_i
        ArrayList<Node> grads = new ArrayList<>();
        for(int i=0;i<vars.size();i++){
            grads.add(goal.diff(vars.get(i)));
        }

        // Wylosuj poczatkowe wartosci zmiennych z przedziału [-500,500]
        for(int i=0;i<vars.size();i++){
            vars.get(i).setValue(rand.nextDouble()*1000-500);
        }

        // iteracja
        double[]grad_values = new double[n];
        for(int iter=0;iter<maxIter;iter++){

            // oblicz wartosc funkcji celu
            double goal_value = goal.evaluate();
            System.out.printf(Locale.US,"[%d] goal:%f\n",iter,goal_value);

            // oblicz wartosci gradientów
            // symboliczne gradienty korzystają z tych samych zmiennych, co funkcja celu
            for(int i=0;i<n;i++){
                grad_values[i] = grads.get(i).evaluate();
            }

            // uaktualnij wartosci zmiennych wg. wzoru x_i = x_i-lr*gradient_i
            for(int i=0;i<n;i++){
                Variable v = vars.get(i);
                v.setValue(v.getValue()-learningRate*grad_values[i]);
            }
        }
        double goal_value = goal.evaluate();
        System.out.printf(Locale.US,"[at end] goal:%f",goal_value);
    }
}
