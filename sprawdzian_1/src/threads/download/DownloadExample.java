package threads.download;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadExample {

//    static int count = 0;
    static AtomicInteger count = new AtomicInteger(0);
    static Semaphore sem = new Semaphore(0);

    static String [] toDownload = {
            "https://home.agh.edu.pl/~pszwed/wyklad-c/01-jezyk-c-intro.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/02-jezyk-c-podstawy-skladni.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/03-jezyk-c-instrukcje.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/04-jezyk-c-funkcje.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/05-jezyk-c-deklaracje-typy.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/06-jezyk-c-wskazniki.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/07-jezyk-c-operatory.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/08-jezyk-c-lancuchy-znakow.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/09-jezyk-c-struktura-programow.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/10-jezyk-c-dynamiczna-alokacja-pamieci.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/11-jezyk-c-biblioteka-we-wy.pdf",
            "https://home.agh.edu.pl/~pszwed/wyklad-c/preprocesor-make-funkcje-biblioteczne.pdf",
    };

    static void sequentialDownload(){
        long t1 = System.nanoTime();
        for(String url:toDownload){
            new Downloader(url).run();
        }
        long t2 = System.nanoTime();
        System.out.printf(Locale.US,"t2-t1=%f\n", TimeUnit.MILLISECONDS.convert(t2-t1, TimeUnit.NANOSECONDS)/1000.0);
    }

    static void concurrentDownload(){
        long t1 = System.nanoTime();
        for(String url:toDownload){
            Thread.startVirtualThread(new Downloader(url));
        }
        long t2 = System.nanoTime();
        System.out.printf(Locale.US,"t2-t1=%f\n", TimeUnit.MILLISECONDS.convert(t2-t1, TimeUnit.NANOSECONDS)/1000.0);
    }

//    static void concurrentDownload2(){
//        long t1 = System.nanoTime();
//        for(String url:toDownload){
//            Thread.startVirtualThread(new Downloader(url));
//        }
//        while(count!=toDownload.length){
//            Thread.yield();
//        }
//        long t2 = System.nanoTime();
//        System.out.printf(Locale.US,"t2-t1=%f\n", TimeUnit.MILLISECONDS.convert(t2-t1, TimeUnit.NANOSECONDS)/1000.0);
//    }

    static void concurrentDownload25(){
        long t1 = System.nanoTime();
        for(String url:toDownload){
            Thread.startVirtualThread(new Downloader(url));
        }
        while(count.get()!=toDownload.length){
            Thread.yield();
        }
        long t2 = System.nanoTime();
        System.out.printf(Locale.US,"t2-t1=%f\n", TimeUnit.MILLISECONDS.convert(t2-t1, TimeUnit.NANOSECONDS)/1000.0);
    }

    static void concurrentDownload3(){
        long t1 = System.nanoTime();
        for(String url:toDownload){
            Thread.startVirtualThread(new Downloader(url));
        }
        try {
            sem.acquire(toDownload.length);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long t2 = System.nanoTime();
        System.out.printf(Locale.US,"t2-t1=%f\n", TimeUnit.MILLISECONDS.convert(t2-t1, TimeUnit.NANOSECONDS)/1000.0);
    }

    static class Downloader implements Runnable{
        private final String url;

        Downloader(String url){
            this.url = url;
        }

        public void run(){
            String fileName = Arrays.stream(url.split("/")).toList().getLast();

            try(
                    InputStream in = new URL(url).openStream();
                    FileOutputStream out = new FileOutputStream(fileName)
            ){
                while (true){
                    int data = in.read();
                    if (data < 0) break;
                    out.write(data);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Done:" + fileName);
//            count++;
            count.incrementAndGet();
            sem.release();
        }

    }

    public static void main(String[] args) {
        System.out.println("Pobieranie sekwencyjne");
        sequentialDownload();

//        RESET
        count.set(0);
        sem = new Semaphore(0);

//        System.out.println("Pobieranie współbieżne v.1");
//        concurrentDownload();
//        System.out.println("Pobieranie współbieżne v.2");
//        concurrentDownload2();
//        System.out.println("Pobieranie współbieżne v.2.5");
//        concurrentDownload25();

        System.out.println("\n");
        System.out.println("Pobieranie współbieżne v.3");
        concurrentDownload3();
    }
}