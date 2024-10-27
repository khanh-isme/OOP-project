import java.util.Timer;
import java.util.TimerTask;

public class TimedQuiz extends TimedTest implements Testable {
    private Quiz quiz;

    public TimedQuiz(Quiz quiz, int timeLimit) {
        super(timeLimit);
        this.quiz = quiz;
    }
    int i = getTimeLimit();
    @Override
    public void startWithTimer() {
    	
        System.out.println("You have " + getTimeLimit() + " minutes to complete the quiz.");
        
        // Logic để quản lý thời gian 
     
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    if (i> 0) {
                    	i--;
                    } else {
                        System.out.println("Time's up!");
                        timer.cancel();
                    }
                }
            };
            // Cứ mỗi 1 giây, chạy task một lần
            timer.scheduleAtFixedRate(task, 0, 1000);
            
            //phải hàm start này ở sau cái chạy time mới đúng
            quiz.startQuiz();
        }


    @Override
    public void startTest() {
        startWithTimer();
    }

    @Override
    public void endTest() {
        System.out.println("Quiz has ended.");
    }

    @Override
    public void calculateScore() {
        System.out.println("Calculating score for timed quiz...");
        // code để tính điểm bài thi có thời gian
        
    }
}
