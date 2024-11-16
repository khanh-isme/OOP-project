import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Reviewable {
   
    
    private String mssv;
    private List<Result> quizHistory;// Lưu trữ lịch sử bài thi

    public Student(String username, String password, String role, String mssv,List<Result> quizHistory) {
        super(username, password, role);
        this.mssv=mssv;  
        this.quizHistory= quizHistory!= null ? quizHistory  : new ArrayList<>();
    }
    
    
    // Thêm kết quả mới vào lịch sử làm bài
    public void addResult(Result result) {
        quizHistory.add(result);
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
	public List<Result> getQuizHistory() {
        return quizHistory;
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
       
    
    public void updateUserAttributes(String username, String password) {
        if (username != null && !username.isEmpty()) {
            this.username = username;
        }
        if (password != null && !password.isEmpty()) {
            this.password = password;
        }
    }
}
