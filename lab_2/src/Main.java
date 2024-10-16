import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Matrix m = new Matrix(new double[][]{{0,2,3,4},{5,6,2,5},{7,8,7,6},{9,8,8,8}});
        System.out.println(m);
        System.out.println(m.det()); //-238
        Matrix m2 = new Matrix(new double[][]{{1,2},{3,4}});
        m2.inv();
//        System.out.println("Old: " + m);
//        m.reshape(2,8);
//        System.out.println("New: " + m);
//        System.out.println("Shape: " + Arrays.toString(m.shape()));
//        Matrix m2 = new Matrix(new double[][]{{1,2,3,4},{5,6},{7,8},{9}});
//        m.reshape(4,4);
//        Matrix m3 = m.add(m2);
//        System.out.println("M: " + m);
//        System.out.println("M2: " + m2);
//        System.out.println("M3: " + m3);
//        Matrix m4 = m.dot(m2);
//        System.out.println("M4: " + m4);
//        System.out.println("frobenius M: " + m.frobenius());
//        Matrix r = Matrix.random(2,3);
//        System.out.println("r: " + r);
//        Matrix e = Matrix.eye(4);
//        System.out.println("e: " + e);
    }
}