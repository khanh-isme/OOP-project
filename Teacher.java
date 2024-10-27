import java.util.ArrayList;
import java.util.List;

public class Teacher extends User  implements Reviewable {
    private List<Quiz> quizzesCreated;

    public Teacher(String username, String password) {
        super(username, password, "Teacher");
        this.quizzesCreated = new ArrayList<>();
    }

    public Quiz createQuiz(String title, int timeLimit) {
        Quiz newQuiz = new Quiz(title, timeLimit);
        quizzesCreated.add(newQuiz);
        return newQuiz;
    }

    public void editQuiz(Quiz quiz) {
        // Code to edit quiz
    }

    public void viewResults(Quiz quiz) {
        // Code to view results of students
    }
    @Override
    public void reviewAnswers() {
        System.out.println("Reviewing answers for teacher: " + getUsername());
        //code để xem lại câu trả lời
    }
    // Getters and Setters
}
