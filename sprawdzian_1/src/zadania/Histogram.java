package zadania;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Histogram {
    static int ARRAY_SIZE = 256;

    static int[] array;
    static BlockingQueue<double[]> queue;

    static void initArray(int size) {
        array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(ARRAY_SIZE);
        }
    }

    static class HistogramThread extends Thread {
        int start, end;
        HistogramThread(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            double[] result = new double[ARRAY_SIZE];

            for (int i = start; i < end; i++) {
                result[array[i]]++;
            }

            try {
                queue.put(result);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    static double[] parallelHistogram(int cnt) {
        double[] result = new double[ARRAY_SIZE];
        queue = new ArrayBlockingQueue<double[]>(cnt);

        int len = array.length / cnt;
        int add = array.length % cnt;
        int index = 0;

        double t1 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        for (int i = 0; i < cnt; i++) {
            if (i == 0) {
                new HistogramThread(index, len+add).start();
                index += len+add;
            } else {
                new HistogramThread(index, index+len).start();
                index += len;
            }
        }

        double t2 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        for (int i = 0; i < cnt; i++) {
            try {
                double[] partialResult = queue.take();
                for (int j = 0; j < ARRAY_SIZE; j++) {
                    result[j] += partialResult[j];
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        double t3 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        System.out.printf("cnt: %d, t2-t1: %f, t3-t2: %f, t3-t1: %f\n", cnt, t2-t1, t3-t2, t3-t1);

        return result;
    }

    public static void main(String[] args) {
        initArray(100_000_000);
        for (int cnt : new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048}) {
            double[] result = parallelHistogram(cnt);
            System.out.println(Arrays.toString(result));
            System.out.printf("%.0f\n", Arrays.stream(result).sum());
        }
    }
}
