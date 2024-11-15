import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Reviewable {
    private List<Quiz> quizzesTaken;
    private List<Integer> scores;// coi lại phần điểm này hình như k cần thiết
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
    /*public void takeQuiz(Quiz quiz, List<String> answers) {
        int score = quiz.calculateScore(answers);
        quizzesTaken.add(quiz);
        scores.add(score);
    }*/

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
    public void reviewInfo() {
        System.out.println("MSSV: " + mssv);
        System.out.println("Quiz History:");
        
        if (quizHistory.isEmpty()) {
            System.out.println("No quiz history available.");
        } else {
            for (Result result : quizHistory) {
            	System.out.println("môn: " + result.getSubjectName());
                System.out.println("Quiz: " + result.getQuizName());
                System.out.println("Score: " + result.getScore());
                System.out.println("Date Taken: " + result.getDateTaken());
 
                System.out.println("-----------------------------");
            }
        }
    }

	

	public void addFeedback(Quiz quiz, String teacherFeedback) {
		// TODO Auto-generated method stub
		
	}	
	
	 // Getters and setters (optional)
    public List<Quiz> getQuizzesTaken() {
        return quizzesTaken;
    }

    public void setQuizzesTaken(List<Quiz> quizzesTaken) {
        this.quizzesTaken = quizzesTaken;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }


    public void setQuizHistory(List<Result> quizHistory) {
        this.quizHistory = quizHistory;
    }
}
