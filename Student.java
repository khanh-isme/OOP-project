import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Reviewable {
    private List<Quiz> quizzesTaken;
    private List<Integer> scores;
    private String mssv;

    public Student(String username, String password, String mssv) {
        super(username, password, "Student");
        this.mssv=mssv;
        this.quizzesTaken = new ArrayList<>();
        this.scores = new ArrayList<>();
    }

    public void takeQuiz(Quiz quiz, List<String> answers) {
        int score = quiz.calculateScore(answers);
        quizzesTaken.add(quiz);
        scores.add(score);
    }

    public int viewScore(Quiz quiz) {
        int index = quizzesTaken.indexOf(quiz);
        if (index != -1) {
            return scores.get(index);
        }
        return -1; // Quiz not found
    }
    @Override
    public void reviewAnswers() {
        System.out.println("Reviewing answers for student: " + getUsername());
        // code để xem lại câu trả lời
    }
    // Getters and Setters
}
