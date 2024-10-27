import java.util.List;
//class này đang vô dụng 
public class ExamSession {
    private Student student;
    private Quiz quiz;
    private long startTime;
    private long endTime;

    public ExamSession(Student student, Quiz quiz) {
        this.student = student;
        this.quiz = quiz;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public void submit(List<String> answers) {
        this.endTime = System.currentTimeMillis();
        int score = quiz.calculateScore(answers);
        long timeTaken = (endTime - startTime) / (1000 * 60); // chuyển đổi mili giây sang phút
        Result result = new Result(student, quiz, score, (int) timeTaken);
        result.display();
    }

    // Getters and Setters
}
