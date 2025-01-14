import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class AsyncMean {
    static double[] array;

    public static void main(String[] args) throws InterruptedException {

    }

    static void initArray(int size) {
        array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = Math.random() * size / (i + 1);
        }
    }

    static class MeanCalcSupplier implements Supplier<Double> {
        int start;
        int end;

        MeanCalcSupplier(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public Double get() {
            int skip = start != 0 ? start - 1 : 0;
            int len = end - start;
            double mean = Arrays.stream(array).skip(skip).limit(len).sum();
            mean /= len;

            return mean;
        }
    }

    public static void asyncMeanV1(int n) {
        ExecutorService executor = Executors.newFixedThreadPool(n);
        List<CompletableFuture<Double>> partialResults = new ArrayList<>();
        int threadsLen = array.length / n;

        double t1 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        for (int i = 0; i < n; i++) {
            CompletableFuture<Double> partialMean = CompletableFuture.supplyAsync(
                    new MeanCalcSupplier(threadsLen*i, threadsLen*(i+1)-1),
                    executor
            );
            partialResults.add(partialMean);
        }

        double t2 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        double mean = 0;
        for (var pr : partialResults) {
            mean += pr.join();
        }
        mean /= n;

        double t3 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        System.out.printf(Locale.US,"t2-t1=%f t3-t1=%f mean=%f\n", t2-t1, t3-t1, mean);

        executor.shutdown();
    }

    static void asyncMeanV2(int n) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(n);
        BlockingQueue<Double> queue = new ArrayBlockingQueue<>(n);

        int threadsLen = array.length / n;

        double t1 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        for (int i = 0; i < n; i++) {
            CompletableFuture.supplyAsync(
                new MeanCalcSupplier(threadsLen*i, threadsLen*(i+1)-1),
                executor
            ).thenApply(queue::offer);
        }

        double t2 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        double mean = 0;
        for (int i = 0; i < n; i++) {
            mean += queue.take();
        }
        mean /= n;

        double t3 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        queue.clear();

        System.out.printf(Locale.US,"t2-t1=%f t3-t1=%f mean=%f\n", t2-t1, t3-t1, mean);

        executor.shutdown();
    }
}
