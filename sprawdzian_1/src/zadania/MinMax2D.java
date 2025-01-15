package zadania;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MinMax2D {
    static double[][] array;
    static BlockingQueue<Result> queue;

    static void initArray(int rows, int cols) {
        array = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = Math.random()*Math.sin(i*Math.PI/rows)*j;
            }
        }
    }

    static class Result {
        double min, max;
        Result(double min, double max) {
            this.min = min;
            this.max = max;
        }
    }

    static class MinMaxThread extends Thread {
        int start;
        int end;

        MinMaxThread(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            Double min = null;
            Double max = null;

            for (int k = start; k < end; k++) {
                int row = k / array[0].length;
                int col = k % array[0].length;

                if (min == null || array[row][col] < min) {
                    min = array[row][col];
                }
                if (max == null || array[row][col] > max) {
                    max = array[row][col];
                }
            }

            try {
                if (min != null) {
                    queue.put(new Result(min, max));
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static Result parallelMinMax(int cnt) {
        queue = new ArrayBlockingQueue<Result>(cnt);
        int len = array.length*array[0].length / cnt;
        int add = array.length*array[0].length % cnt;
        int index = 0;

        double t1 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        for (int i = 0; i < cnt; i++) {
            if (i == 0) {
                new MinMaxThread(index, len+add).start();
                index += len+add;
            } else {
                new MinMaxThread(index, index+len).start();
                index += len;
            }
        }

        double t2 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        Double min = null;
        Double max = null;

        for (int i = 0; i < cnt; i++) {
            try {
                Result res = queue.take();
                if (min == null || res.min < min) {
                    min = res.min;
                }
                if (max == null || res.max > max) {
                    max = res.max;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        double t3 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        System.out.printf("cnt: %d, t2-t1: %f, t3-t2: %f, t3-t1: %f\n", cnt, t2-t1, t3-t2, t3-t1);

        return new Result(min, max);
    }

    public static void main(String[] args) {
        initArray(10_000, 10_000);
        for (int cnt : new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048}) {
            Result res = parallelMinMax(cnt);
            System.out.printf("Min: %f, Max: %f\n", res.min, res.max);
        }
    }
}
