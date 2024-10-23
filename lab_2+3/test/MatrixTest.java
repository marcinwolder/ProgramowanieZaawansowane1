import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    static Matrix [] m;
    @BeforeEach
    void setUp() {
        m = new Matrix[]{
                new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9},{10,11,12}}),
                new Matrix(new double[][]{{1}}),
                new Matrix(new double[][]{{1},{2},{3}}),
                new Matrix(new double[][]{{0,7,0},{8,2,1},{6,3,7}}),
                new Matrix(new double[][]{{0,7,0,15},{8,2,1,2},{6,3,7,17},{1,4,5,7}})
        };
    }

    @Test
    void sizeConstructor(){
        for(int[] sizes : new int[][]{{1,2}, {3,5}, {1, 20}}){
            Matrix test = new Matrix(sizes[0], sizes[1]);
            assertEquals(sizes[0], test.rows);
            assertEquals(sizes[1], test.cols);
        }
    }

    @Test
    void valuesConstructor(){
        for(double[][] values : new double[][][]{
                {{1,2},{3,5}},
                {{1,2},{3,5,0,0}},
                {{1,2,3,4},{3,5},{0,1,2}},
        }){
            Matrix test = new Matrix(values);
            assertEquals(test.rows, values.length);
            int maxRowSize = 0;
            for(double[] row : values){
                if(row.length > maxRowSize){
                    maxRowSize = row.length;
                }
            }
            assertEquals(test.cols, maxRowSize);
            for(int row=0; row<test.rows; row++){
                for(int col=0; col<test.cols; col++){
                    if(col>values[row].length-1){
                        assertEquals(0, test.get(row, col));
                    } else {
                        assertEquals(values[row][col], test.get(row, col));
                    }
                }
            }
        }
    }

    @Test
    void testAsArray() {
        for(double[][] values : new double[][][]{
                {{1,2,0},{3,5,0}},
                {{1,2,0,0},{3,5,0,0}},
                {{1,2},{3,5},{1,2}},
        }){
            Matrix test = new Matrix(values);
            double[][] arr = test.asArray();
            for(int row=0; row<test.rows; row++){
                assertArrayEquals(arr[row], values[row]);
            }
        }
    }

    @Test
    void testGet() {
        for(Matrix m : m) {
            for(int i=0; i<m.rows; i++){
                for(int j=0; j<m.cols; j++){
                    assertEquals(m.data[i*m.cols + j], m.get(i, j), 1e-16);
                }
            }
        }
    }

    @Test
    void testSet() {
        for(Matrix m : m) {
            double temp = m.get(0, 0);
            m.set(0, 0, temp+1);
            assertEquals(temp+1, m.get(0, 0), 1e-16);
        }
    }

    @Test
    void testToString() {
        for(Matrix m : m) {
            String output = m.toString();
            int commaCounter = 0;
            int openBracketCounter = 0;
            int closeBracketCounter = 0;
            for(int i=0; i<output.length(); i++){
                switch (output.charAt(i)) {
                    case ',':
                        commaCounter++;
                        break;
                    case '[':
                        openBracketCounter++;
                        break;
                    case ']':
                        closeBracketCounter++;
                        break;
                    default:
                        break;
                }
            }
            assertEquals(m.rows*m.cols-1, commaCounter);
            assertEquals(1+m.rows, openBracketCounter);
            assertEquals(1+m.rows, closeBracketCounter);
        }
    }

    @Test
    void testReshape() {
        for(Matrix m : m) {
            m.reshape(m.cols, m.rows);
            try {
                m.reshape(m.cols, m.rows+1);
                fail("matrix shouldn't be reshaped");
            } catch (Exception _) {}
        }
    }

    @Test
    void testShape() {
        for(Matrix m : m) {
            int[] shape = {m.rows, m.cols};
            assertArrayEquals(shape, m.shape());
        }
    }

    @Test
    void testCopy() {
        for(Matrix m : m) {
            Matrix copy = m.copy();
            assertArrayEquals(m.data, copy.data);
        }
    }

    @Test
    void testAdd() {
        for(Matrix m : m) {
            // Testing adding scalar
            Matrix added = m.add(1);
            for(int row=0; row<m.rows; row++){
                for(int col=0; col<m.cols; col++){
                    assertEquals(m.get(row, col)+1, added.get(row, col));
                }
            }
            // Testing adding matrix
            Matrix added2 = m.add(m);
            for(int row=0; row<m.rows; row++){
                for(int col=0; col<m.cols; col++){
                    assertEquals(m.get(row, col)*2, added2.get(row, col));
                }
            }
            // Testing wrong shapes
            try {
                Matrix test = m.copy();
                test.reshape(m.cols, m.rows);
                if (!Arrays.equals(test.shape(), m.shape())) {
                    m.add(test);
                    fail("Adding two matrices with different shapes shouldn't be possible");
                }
            } catch (Exception _) {}
        }
    }

    @Test
    void testSub() {
        for(Matrix m : m) {
            // Testing subtracting scalar
            Matrix subtracted = m.sub(1);
            for(int row=0; row<m.rows; row++){
                for(int col=0; col<m.cols; col++){
                    assertEquals(m.get(row, col)-1, subtracted.get(row, col));
                }
            }
            // Testing subtracting matrix
            Matrix subtracted2 = m.sub(m);
            for(int row=0; row<m.rows; row++){
                for(int col=0; col<m.cols; col++){
                    assertEquals(0, subtracted2.get(row, col));
                }
            }
            // Testing wrong shapes
            try {
                Matrix test = m.copy();
                test.reshape(m.cols, m.rows);
                if (!Arrays.equals(test.shape(), m.shape())) {
                    m.sub(test);
                    fail("Subtracting two matrices with different shapes shouldn't be possible");
                }
            } catch (Exception _) {}
        }
    }

    @Test
    void testMul() {
        for(Matrix m : m){
            // Testing multiplying scalar
            Matrix multiplied = m.mul(2);
            for(int row=0; row<m.rows; row++){
                for(int col=0; col<m.cols; col++){
                    assertEquals(m.get(row, col)*2, multiplied.get(row, col));
                }
            }
            // Testing multiplying matrix
            Matrix multiplied2 = m.mul(m);
            for(int row=0; row<m.rows; row++){
                for(int col=0; col<m.cols; col++){
                    assertEquals(Math.pow(m.get(row, col), 2), multiplied2.get(row, col));
                }
            }
            // Testing wrong shapes
            try {
                Matrix test = m.copy();
                test.reshape(m.cols, m.rows);
                if (!Arrays.equals(test.shape(), m.shape())) {
                    m.mul(test);
                    fail("Multiplying two matrices with different shapes shouldn't be possible");
                }
            } catch (Exception _) {}
        }
    }

    @Test
    void testDiv() {
        for(Matrix m : m){
            // Testing dividing scalar
            try {
                m.div(0);
                fail("You shouldn't be able to divide by 0");
            } catch (Exception _) {}
            Matrix divided = m.div(2);
            for(int row=0; row<m.rows; row++){
                for(int col=0; col<m.cols; col++){
                    assertEquals(m.get(row, col)/2, divided.get(row, col));
                }
            }
            // Testing dividing matrix
            try {
                Matrix divided2 = m.div(m);
                for(int row=0; row<m.rows; row++){
                    for(int col=0; col<m.cols; col++){
                        assertEquals(1, divided2.get(row, col));
                    }
                }
            } catch (RuntimeException e) {
                if (!Objects.equals(e.getMessage(), "ERROR: Cannot divide by 0.")) {
                    throw new RuntimeException(e);
                }
            }
            // Testing wrong shapes
            try {
                Matrix test = m.copy();
                test.reshape(m.cols, m.rows);
                if (!Arrays.equals(test.shape(), m.shape())) {
                    m.div(test);
                    fail("Dividing two matrices with different shapes shouldn't be possible");
                }
            } catch (Exception _) {}
        }
    }

    @Test
    void testDot() {
        Matrix[] results = {
                new Matrix(new double[][]{{38,44,50,56},{83,98,113,128},{128,152,176,200},{173,206,239,272}}),
                new Matrix(new double[][]{{1}}),
                new Matrix(new double[][]{{1,2,3},{2,4,6},{3,6,9}}),
                new Matrix(new double[][]{{56,14,7},{22,63,9},{66,69,52}}),
                new Matrix(new double[][]{{71,74,82,119},{24,71,19,155},{83,137,137,334},{69,58,74,157}})
        };
        for (int i=0; i<results.length; i++) {
            Matrix reshaped = m[i].copy();
            reshaped.reshape(reshaped.cols, reshaped.rows);
            Matrix result = m[i].dot(reshaped);
            assertArrayEquals(results[i].data, result.data, 0.001);
        }
        // Testing wrong shapes
        try {
            Matrix m1 = new Matrix(new double[][]{{1,2},{3,4},{5,6}});
            Matrix m2 = new Matrix(new double[][]{{1,2,3},{4,5,6}, {7, 8, 9}});
            m1.dot(m2);
            fail("Dot multiplying two matrices with wrong shapes shouldn't be possible");
        } catch (Exception _) {}
    }

    @Test
    void testFrobenius() {
        double[] results = {5*Math.sqrt(26), 1, Math.sqrt(14)};
        for (int i=0; i<results.length; i++) {
            assertEquals(results[i], m[i].frobenius(), 0.001);
        }
    }

    @Test
    void testRandom() {
        double[] result = new double[]{0.9093242166682459, 0.6776434276556056, 0.1752512676132323, 0.03088032115235473, 0.508194338297567, 0.5917355482632037, 0.5605808563131096, 0.5323438438550077, 0.46650202984540645,
        0.2378206514541117, 0.9342095057424001, 0.854705076853461, 0.5629358891252244, 0.8814612867898558, 0.21511619850883412, 0.12087206450489618, 0.29906221519692333, 0.2085241083506103,
        0.30577821143590733, 0.14657092049022857, 0.8783481654274296, 0.35931804410356305, 0.5077429295467341, 0.8767957222905435, 0.7826856946350738, 0.30236717392676205, 0.06419570217169268,
        0.2667059121002914, 0.8015492799680509, 0.47741472452645606, 0.20272328845750043, 0.18353994486331449, 0.06911604302746222, 0.6577614145657327, 0.7047924646404745, 0.004746263520456373,
        0.5211215801004967, 0.4088001795462807, 0.33803403919803554, 0.592416239595785, 0.19592082249220566, 0.3526162437549264, 0.4532427417240452, 0.23523576700981952, 0.973168068439986,
        0.6877801621809052, 0.6700733660969732, 0.9665556889225198, 0.6687258484107955, 0.05896850576968682, 0.8537346334400933, 0.39081331045323, 0.8858575530429371, 0.9228051250240342};
        long seed = 2137;
        Matrix test = Matrix.random(6, 9, seed);
        assertArrayEquals(result, test.data, 0.001);
    }

    @Test
    void testEye() {
        Matrix test = new Matrix(new double[][]{{1, 0, 0, 0}, {0, 1, 0 ,0}, {0, 0, 1, 0}, {0, 0, 0, 1}});
        assertArrayEquals(test.data, Matrix.eye(4).data);
    }

    @Test
    void testUpperTriangular() {
        for (Matrix m : m) {
            Matrix copied = m.copy();
            TriangularMatrix test = m.upperTriangular(copied);
            for (int col = 0; col<test.m.cols; col++) {
                for (int row = col+1; row<test.m.rows; row++) {
                    assertEquals(0, test.m.get(row, col));
                }
            }
            // Testing also transform
            assertArrayEquals(test.m.data, copied.data);
        }
    }

    @Test
    void testLowerTriangular() {
        for (Matrix m : m) {
            Matrix copied = m.copy();
            TriangularMatrix test = m.lowerTriangular(copied);
            for (int col = 0; col<test.m.cols; col++) {
                for (int row = col-1; row>=0; row--) {
                    assertEquals(0, test.m.get(row, col));
                }
            }
            // Testing also transform
            assertArrayEquals(test.m.data, copied.data);
        }
    }

    @Test
    void testDet() {
        Double[] results = {null, 1., null, -350., 3804.};
        for (int i=0; i<m.length; i++) {
            try {
                assertEquals(results[i], m[i].det());
            } catch (RuntimeException e) {
                if (!(Objects.equals(e.getMessage(), "ERROR: Matrix is not square!") && m[i].rows != m[i].cols)) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Test
    void testInv() {
        Matrix[] results = {
                new Matrix(new double[][]{{1}}),
                new Matrix(new double[][]{{1}}),
                new Matrix(new double[][]{{1}}),
                new Matrix(new double[][]{{-11.0/350,7.0/50,-1.0/50},{1.0/7,0,0},{-6.0/175,-3.0/25,4.0/25}}),
                new Matrix(new double[][]{{-17./1268,149./1268,23./1268,-31./634},{89./1268,115./1268,-195./1268,125./634},{-32./317,-35./951,6./317,172./951},{43./1268,-161./3804,91./1268,-175./1902}})
        };
        for (int i=0; i<m.length; i++) {
            try {
                Matrix inverted = m[i].inv();
                assertArrayEquals(results[i].data, inverted.data, 0.001);
                assertArrayEquals(Matrix.eye(inverted.rows).data, inverted.dot(m[i]).data, 0.001);
            } catch (RuntimeException e) {
                if (!(Objects.equals(e.getMessage(), "ERROR: Matrix is not square!") && m[i].rows != m[i].cols)) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}