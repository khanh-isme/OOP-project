import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private String title;
    private List<Question> questions;
    private List<Question> skippedQuestions;
    private List<String> answers;
    private int timeLimit; // in minutes

    public Quiz(String title, int timeLimit) {
        this.title = title;
        this.questions = new ArrayList<>();
        this.skippedQuestions= new ArrayList();
        this.answers = new ArrayList();
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
        //tạo 1 danh sách câu trả lời của người dùng
        
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
                System.out.print("Your answer (A, B, C, D) or type 0 to skip");
                answer = scanner.nextLine().toUpperCase();//chuyển đổi tất cả các ký tự trong chuỗi thành chữ hoa.
                if (isValidAnswer(answer, options.size())) {
                    break;
                }
                if (answer.equals("S")) {
                    skippedQuestions.add(question);
                    answers.add(null); // Để lại câu trả lời là null cho câu hỏi bị bỏ qua
                    break;
                }
                else {
                    System.out.println("Invalid input. Please enter A, B, C, or D.");
                }
            }
            answers.add(answer);//lưu câu trả lời lại 
            
            
        }
        while(!skippedQuestions.isEmpty()) {
        	String answer;
            System.out.println("\nQuay lại các câu hỏi bị bỏ qua.");
            for (Question skippedQuestion : skippedQuestions) {
                int index = questions.indexOf(skippedQuestion);
                System.out.println("Câu " + (index + 1) + ": " + skippedQuestion.getContent());
                for (int j = 0; j < skippedQuestion.getOptions().size(); j++) {
                	System.out.println((char) ('A' + j) + ". " +skippedQuestion.getOptions().get(j));
                }
                
                System.out.print("Your answer (A, B, C, D) or type 0 to skip");
                answer = scanner.nextLine().toUpperCase();//chuyển đổi tất cả các ký tự trong chuỗi thành chữ hoa.
                if (isValidAnswer(answer, skippedQuestion.getOptions().size())) {
                	skippedQuestions.remove(skippedQuestion);
                    break;
                }
                if (answer.equals("S")) {
                	continue;
                }
                else {
                    System.out.println("Invalid input. Please enter A, B, C, or D.");
                }
                answers.set(index, answer); // Cập nhật câu trả lời
            }
        }

        // Calculate score after all questions have been answered
        int score = calculateScore(answers);
        System.out.println("Quiz completed!"+"score: " + score + "/" + questions.size());

        
        
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
