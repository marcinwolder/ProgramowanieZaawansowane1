package clock;

import java.time.LocalTime;

public class Clock extends Thread{
    @Override
    public void run() {
        while (true){
            try {
                sleep(100);
                LocalTime time = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n", time.getHour(), time.getMinute(), time.getSecond());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
