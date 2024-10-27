import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
    private int seconds;

    public CountdownTimer(int seconds) {
        this.seconds = seconds;
    }

    public void start() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                if (seconds > 0) {
                    seconds--;
                } else {
                    System.out.println("Time's up!");
                    timer.cancel();
                }
            }
        };
        // Cứ mỗi 1 giây, chạy task một lần
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
 }
