
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Result {
    private String quizName;
    private int score;
    private int totalQuestions;
    private String dateTaken;
    private List<Answer> answers;

 // Constructor
    public Result(String quizName, int score, int totalQuestions, String dateTaken, List<Answer> answer) {
        this.quizName = quizName;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.dateTaken = dateTaken;
        this.answers = answer;
    }

 // Hiển thị chi tiết kết quả, bao gồm danh sách câu hỏi
    public void displayDetailedResult() {
        System.out.println("Quiz Name: " + quizName);
        System.out.println("Score: " + score + " / " + totalQuestions);
        System.out.println("Date Taken: " + dateTaken);
        System.out.println("Questions and Answers:");
        
        for (Answer answer : answers) {
            answer.displayQuestionDetails();
        }
        
        System.out.println("-------------------------");
    }

    // Getters và setters nếu cần
    public String getQuizName() {
        return quizName;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public String getDateTaken() {
        return dateTaken;
    }
}
