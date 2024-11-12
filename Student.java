import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Reviewable {
    private List<Quiz> quizzesTaken;
    private List<Integer> scores;
    private String mssv;
    private List<Result> quizHistory;// Lưu trữ lịch sử bài thi

    public Student(String username, String password, String mssv) {
        super(username, password, "Student");
        this.mssv=mssv;
        this.quizzesTaken = new ArrayList<>();
        this.scores = new ArrayList<>();
        this.quizHistory= new ArrayList();
    }
    
    // cần xem xét lại hình như bị trùng chức năng 
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
    
    // Thêm kết quả mới vào lịch sử làm bài
    public void addResult(Result result) {
        quizHistory.add(result);
    }
    
    public List<Result> getQuizHistory() {
        return quizHistory;
    }
    
    
    
    
 // Hiển thị lịch sử làm bài chi tiết, bao gồm các câu hỏi đã chọn
    public void viewDetailedQuizHistory() {
        System.out.println("Detailed Quiz History for: " + username);
        
        if (quizHistory.isEmpty()) {
            System.out.println("No quiz history available.");
            return;
        }
        
        for (Result result : quizHistory) {
            result.displayDetailedResult();
        }
    }
    @Override
    public void reviewAnswers() {
        System.out.println("Reviewing answers for student: " + getUsername());
        // code để xem lại câu trả lời
    }
    // Getters and Setters

	

	public void addFeedback(Quiz quiz, String teacherFeedback) {
		// TODO Auto-generated method stub
		
	}
}
