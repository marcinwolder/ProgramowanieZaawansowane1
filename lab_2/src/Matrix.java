import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Matrix {
    double[] data;
    int rows;
    int cols;
    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }
    Matrix(double[][] d){
        this.rows = d.length;
        for (int i=0; i<rows; i++){
            if (this.cols < d[i].length) {
                this.cols = d[i].length;
            }
        }
        data = new double[rows*cols];
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                if (j < d[i].length) {
                    data[i*cols + j] = d[i][j];
                } else {
                    data[i*cols + j] = 0;
                }
            }
        }
    }
    double[][] asArray() {
        double[][] result = new double[rows][cols];
        for (int i=0; i<rows; i++){
            System.arraycopy(data, i * cols, result[i], 0, cols);
        }
        return result;
    }
    double get(int r,int c) {
        return data[r*cols + c];
    }
    void set (int r,int c, double value) {
        data[r*cols + c] = value;
    }
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for(int i=0;i<rows;i++){
            buf.append("[");
            for (int j=0; j<cols; j++){
                buf.append(get(i,j));
                buf.append(", ");
            }
            buf.delete(buf.length()-2, buf.length());
            buf.append("], ");
        }
        buf.delete(buf.length()-2, buf.length());
        buf.append("]");
        return buf.toString();
    }
    void reshape(int newRows,int newCols) {
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));
        this.rows = newRows;
        this.cols = newCols;
    }
    int[] shape() {
        int[] result = new int[2];
        result[0] = rows;
        result[1] = cols;
        return result;
    }
    Matrix copy() {
        return new Matrix(this.asArray());
    }
    Matrix add(double w) {
        Matrix result = this.copy();
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                result.set(i, j, get(i,j) + w);
            }
        }
        return result;
    }
    Matrix sub(double w) {
        return this.add(-w);
    }
    Matrix mul(double w) {
        Matrix result = this.copy();
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                result.set(i, j, get(i,j) * w);
            }
        }
        return result;
    }
    Matrix div(double w) {
        if (w==0) {
            throw new RuntimeException("ERROR: Cannot divide by 0.");
        }
        return this.mul(1.0/w);
    }
    Matrix add(Matrix m) {
        if (!Arrays.equals(this.shape(), m.shape()))
            throw new RuntimeException("ERROR: Matrices are different shapes.");

        Matrix result = this.copy();
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                result.set(i, j, get(i,j) + m.get(i, j));
            }
        }
        return result;
    }
    Matrix sub(Matrix m) {
        return this.add(m.mul(-1));
    }
    Matrix mul(Matrix m) {
        if (!Arrays.equals(this.shape(), m.shape()))
            throw new RuntimeException("ERROR: Matrices are different shapes.");

        Matrix result = this.copy();
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                result.set(i, j, get(i,j) * m.get(i, j));
            }
        }
        return result;
    }
    Matrix div(Matrix m) {
        if (!Arrays.equals(this.shape(), m.shape()))
            throw new RuntimeException("ERROR: Matrices are different shapes.");

        Matrix result = this.copy();
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                if (m.get(i, j)==0) {
                    throw new RuntimeException("ERROR: Cannot divide by 0.");
                }
                result.set(i, j, get(i,j) / m.get(i, j));
            }
        }
        return result;
    }
    Matrix dot(Matrix m) {
        if (this.cols != m.rows)
            throw new RuntimeException("ERROR: Matrices cannot be dot multiplied.");
        Matrix result = new Matrix(this.rows, m.cols);
        for (int i=0; i<result.rows; i++) {
            for (int j=0; j<result.cols; j++) {
                for (int k=0; k<this.cols; k++) {
                    result.set(i, j, result.get(i, j) + (this.get(i, k) * m.get(k, j)));
                }
            }
        }
        return result;
    }
    double frobenius() {
        double result = 0;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                result += pow(get(i,j), 2);
            }
        }
        return sqrt(result);
    }

    public static Matrix random(int rows, int cols){
        return random(rows, cols, null);
    }
    public static Matrix random(int rows, int cols, Long seed) {
        Matrix m = new Matrix(rows,cols);
        Random r;
        if (seed == null)
            r = new Random();
        else
            r = new Random(seed);
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                m.set(i, j, r.nextDouble());
            }
        }
        return m;
    }
    public static Matrix eye(int n){
        Matrix m = new Matrix(n,n);
        for (int i = 0; i < n; i++) {
            m.set(i, i, 1);
        }
        return m;
    }
    TriangularMatrix upperTriangular(){
        return upperTriangular(null);
    }
    TriangularMatrix upperTriangular(Matrix alsoTransform){
        TriangularMatrix result = new TriangularMatrix();

        double scalar = 1;
        Matrix buf = new Matrix(1,cols);
        Matrix rel_row_buf = new Matrix(1, cols);
        double[][] data = this.asArray();
        double[][] also_transform_data = null;
        if (alsoTransform != null) {
            also_transform_data = alsoTransform.asArray();
        }
        for(int i=0; i<cols; i++){
            // Check for 0 in cell
            if(data[i][i]==0){
                for(int row=i+1; row<rows; row++){
                    if(data[row][i]!=0){
                        System.arraycopy(data[row], 0, buf.data, 0 ,cols);
                        System.arraycopy(data[i], 0, data[row], 0 ,cols);
                        System.arraycopy(buf.data, 0, data[i], 0 ,cols);
                        if(alsoTransform!=null){
                            System.arraycopy(also_transform_data[row], 0, buf.data, 0 ,cols);
                            System.arraycopy(also_transform_data[i], 0, also_transform_data[row], 0 ,cols);
                            System.arraycopy(buf.data, 0, also_transform_data[i], 0 ,cols);
                        }
                        scalar *= -1;
                        break;
                    }
                }
            }
            // Subtracting rows
            for(int row=i+1; row<rows; row++){
                if(data[row][i]!=0){
                    double upper_scalar = data[i][i];
                    double lower_scalar = data[row][i];

                    System.arraycopy(data[i], 0, rel_row_buf.data, 0 ,cols);
                    System.arraycopy(data[row], 0, buf.data, 0 ,cols);
                    buf = buf.mul(upper_scalar);
                    scalar *= upper_scalar;
                    buf = buf.sub(rel_row_buf.mul(lower_scalar));
                    System.arraycopy(buf.data, 0, data[row], 0 ,cols);
                    if(alsoTransform!=null){
                        System.arraycopy(also_transform_data[i], 0, rel_row_buf.data, 0 ,cols);
                        System.arraycopy(also_transform_data[row], 0, buf.data, 0 ,cols);
                        buf = buf.mul(upper_scalar);
                        buf = buf.sub(rel_row_buf.mul(lower_scalar));
                        System.arraycopy(buf.data, 0, also_transform_data[row], 0 ,cols);
                    }
                }
            }
        }
        result.scalar = scalar;
        result.m = new Matrix(data);
        if(alsoTransform!=null){
            alsoTransform.data = new Matrix(also_transform_data).data;
        }

        return result;
    }
    TriangularMatrix lowerTriangular(){
        return lowerTriangular(null);
    }
    TriangularMatrix lowerTriangular(Matrix alsoTransform){
        TriangularMatrix result = new TriangularMatrix();

        double scalar = 1;
        Matrix buf = new Matrix(1,cols);
        Matrix rel_row_buf = new Matrix(1, cols);
        double[][] data = this.asArray();
        double[][] also_transform_data = null;
        if (alsoTransform != null) {
            also_transform_data = alsoTransform.asArray();
        }
        for(int i=cols-1; i>=0; i--){
            // Check for 0 in cell
            if(data[i][i]==0){
                for(int row=i-1; row>=0; row--){
                    if(data[row][i]!=0){
                        System.arraycopy(data[row], 0, buf.data, 0 ,cols);
                        System.arraycopy(data[i], 0, data[row], 0 ,cols);
                        System.arraycopy(buf.data, 0, data[i], 0 ,cols);
                        if(alsoTransform!=null){
                            System.arraycopy(also_transform_data[row], 0, buf.data, 0 ,cols);
                            System.arraycopy(also_transform_data[i], 0, also_transform_data[row], 0 ,cols);
                            System.arraycopy(buf.data, 0, also_transform_data[i], 0 ,cols);
                        }
                        scalar *= -1;
                        break;
                    }
                }
            }
            // Subtracting rows
            for(int row=i-1; row>=0; row--){
                if(data[row][i]!=0){
                    double lower_scalar = data[i][i];
                    double upper_scalar = data[row][i];

                    System.arraycopy(data[i], 0, rel_row_buf.data, 0 ,cols);
                    System.arraycopy(data[row], 0, buf.data, 0 ,cols);
                    buf = buf.mul(lower_scalar);
                    scalar *= lower_scalar;
                    buf = buf.sub(rel_row_buf.mul(upper_scalar));
                    System.arraycopy(buf.data, 0, data[row], 0 ,cols);
                    if(alsoTransform!=null){
                        System.arraycopy(also_transform_data[i], 0, rel_row_buf.data, 0 ,cols);
                        System.arraycopy(also_transform_data[row], 0, buf.data, 0 ,cols);
                        buf = buf.mul(lower_scalar);
                        buf = buf.sub(rel_row_buf.mul(upper_scalar));
                        System.arraycopy(buf.data, 0, also_transform_data[row], 0 ,cols);
                    }
                }
            }
        }
        result.scalar = scalar;
        result.m = new Matrix(data);
        if(alsoTransform!=null){
            alsoTransform.data = new Matrix(also_transform_data).data;
        }

        return result;
    }
    double det(){
        if (cols != rows)
            throw new RuntimeException("ERROR: Matrix is not square!");
        TriangularMatrix result = this.upperTriangular();
        double product = 1;
        for(int i=0; i<result.m.rows; i++){
            product *= result.m.get(i, i);
        }
        return product/result.scalar;
    }
    Matrix inv(){
        if (cols != rows)
            throw new RuntimeException("ERROR: Matrix is not square!");
        Matrix result = eye(this.rows);
        TriangularMatrix m = this.upperTriangular(result);
        TriangularMatrix m2 = m.m.lowerTriangular(result);
        for (int i=0; i<rows; i++){
            double scalar = m2.m.get(i, i);
            if (scalar==0)
                throw new RuntimeException("ERROR: Matrix cannot be inverted");
            for (int col=0; col<cols; col++){
                result.set(i, col, result.get(i, col) / scalar);
            }
        }
        return result;
    }
}

class TriangularMatrix {
    Matrix m;
    double scalar;
}