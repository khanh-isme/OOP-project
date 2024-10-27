
import java.util.Scanner;
public class Result {
	// class để coi kết quả 
    private Student student;
    private Quiz quiz;
    private int score;
    private int timeTaken;
    
    //class này cũng chưa sử dụng 
    public Result(Student student, Quiz quiz, int score, int timeTaken) {
        this.student = student;
        this.quiz = quiz;
        this.score = score;
        this.timeTaken = timeTaken;
    }

    public void display() {
        System.out.println("Student: " + student.username);
        System.out.println("Quiz: " + quiz.getTitle());
        System.out.println("Score: " + score);
        System.out.println("Time Taken: " + timeTaken + " minutes");
    }

    // Getters and Setters
}
