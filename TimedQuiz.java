import java.util.Timer;
import java.util.TimerTask;

public class TimedQuiz extends TimedTest  {
    private Quiz quiz;
    private Student student;
    private String subjectname;
    public TimedQuiz(Quiz quiz, Student student, int timeLimit,String subjectname) {
        super(timeLimit);
        this.quiz = quiz;
        this.student = student;
        this.subjectname= subjectname;
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
                        quiz.stopQuiz();
                        quiz.finishQuiz(student,subjectname);
                        timer.cancel();
                        
                    }
                }
            };
            // Cứ mỗi 1 giây, chạy task một lần
            timer.scheduleAtFixedRate(task, 0, 1000);
            
            //phải hàm start này ở sau cái chạy time mới đúng
            quiz.startQuiz(student,subjectname);
            
         // Nếu bài thi kết thúc (do hết thời gian hoặc hoàn thành)
            timer.cancel(); // Đảm bảo hủy bộ đếm nếu quiz tự kết thúc
        }


}
