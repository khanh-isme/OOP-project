import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private String title;
    private List<Question> questions;
    private int timeLimit; // in minutes

    public Quiz(String title, int timeLimit) {
        this.title = title;
        this.questions = new ArrayList<>();
        this.timeLimit = timeLimit;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    
    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        List<String> answers = new ArrayList<>();//tạo 1 danh sách câu trả lời của người dùng
        
        System.out.println("Quiz: " + title);
        
        System.out.println("Starting the quiz...");

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);// hàm get Trả về phần tử ở vị trí đã chỉ định trong danh sách 
            System.out.println("Question " + (i + 1) + ": " + question.getContent());
            
            // Display options
            List<String> options = question.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((char) ('A' + j) + ". " + options.get(j));//options.get(i) là để lưu số lượng kết quả
            }

            // Get the answer from user and validate input
            String answer;
            while (true) {
                System.out.print("Your answer (A, B, C, D): ");
                answer = scanner.nextLine().toUpperCase();//chuyển đổi tất cả các ký tự trong chuỗi thành chữ hoa.
                if (isValidAnswer(answer, options.size())) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter A, B, C, or D.");
                }
            }
            answers.add(answer);//lưu câu trả lời lại 
        }

        // Calculate score after all questions have been answered
        int score = calculateScore(answers);
        System.out.println("Quiz completed!"+"score: " + score + "/" + questions.size());

        scanner.close(); // Close the scanner to avoid resource leak
    }

    //Kiểm tra xem thông tin đầu vào của người dùng có hợp lệ không (A, B, C hoặc D)
    private boolean isValidAnswer(String answer, int numOptions) {
        char answerChar = answer.charAt(0);
        return answer.length() == 1 && answerChar >= 'A' && answerChar < 'A' + numOptions;
    }

    // tính điểm 
    public int calculateScore(List<String> answers) {
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).isCorrect(answers.get(i))) {
                score++;
            }
        }
        return score;
    }

    // Getters and Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
