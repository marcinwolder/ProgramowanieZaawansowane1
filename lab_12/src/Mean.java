import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.*;

public class Mean {
    static double[] array;
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);

    static void initArray(int size) {
        array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = Math.random()*size/(i+1);
        }
    }

    static class MeanCalc extends Thread{
        private final int start;
        private final int end;
        double mean = 0;

        MeanCalc(int start, int end){
            this.start = start;
            this.end = end;
        }
        public void run(){
            int skip = start != 0 ? start - 1 : 0;
            int len = end - start;
            mean = Arrays.stream(array).skip(skip).limit(len).sum();
            mean /= len;

            try {
                results.put(mean);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

//            System.out.printf(Locale.US,"%d-%d mean=%f\n", start, end, mean);
        }
    }

    /**
     * Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
     * Wypisuje czasy operacji
     * @param cnt - liczba wątków
     */
    static void parallelMeanV1(int cnt) throws InterruptedException {
        MeanCalc[] threads = new MeanCalc[cnt];
        int threadsLen = array.length / cnt;
        for (int i = 0; i < cnt; i++) {
            threads[i] = new MeanCalc(threadsLen*i, threadsLen*(i+1)-1);
        }
        double t1 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        for (MeanCalc thread : threads) {
            thread.start();
        }

        double t2 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        for (MeanCalc thread : threads) {
            thread.join();
        }

        double mean = 0;

        for (MeanCalc thread : threads) {
            mean += thread.mean;
        }
        mean /= cnt;
        results.clear();

        double t3 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }

    /**
     * Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
     * Wypisuje czasy operacji
     * @param cnt - liczba wątków
     */
    static void parallelMeanV2(int cnt) throws InterruptedException {
        int threadsLen = array.length / cnt;
        double t1 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        for (int i = 0; i < cnt; i++) {
            MeanCalc thread = new MeanCalc(threadsLen*i, threadsLen*(i+1)-1);
            thread.start();
        }

        double t2 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        double mean = 0;
        for (int i = 0; i < cnt; i++) {
            mean += results.take();
        }
        mean /= cnt;
        results.clear();

        double t3 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }

    /**
     * Oblicza średnią wartości elementów tablicy array uruchamiając równolegle działające wątki.
     * Wypisuje czasy operacji
     * @param cnt - liczba wątków
     */
    static void parallelMeanV3(int cnt) throws InterruptedException {
        int threadsLen = array.length / cnt;

        double t1 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        ExecutorService executor = Executors.newFixedThreadPool(cnt);
        for(int i = 0; i < cnt; i++){
            executor.execute(new MeanCalc(threadsLen*i, threadsLen*(i+1)-1));
        }
        executor.shutdown();

        double t2 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        double mean = 0;
        for (int i = 0; i < cnt; i++) {
            mean += results.take();
        }
        mean /= cnt;
        results.clear();

        double t3 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())/1000.0;

        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }
}