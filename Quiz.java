import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;


public class Quiz {
    private String title;
    private List<Question> questions;
    private List<Question> skippedQuestions;
    private List<Answer> answers;
    private Integer timeLimit; // in minutes
    private boolean isOngoing; // Cờ hiệu để kiểm tra xem bài thi có đang diễn ra không


    public Quiz(String title, Integer timeLimit) {
        this.title = title;
        this.questions = new ArrayList<>();
        this.skippedQuestions= new ArrayList();
        this.answers = new ArrayList();
        this.timeLimit = timeLimit;
        
        this.isOngoing = false; // Bắt đầu là không hoạt động
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    
    public void startQuiz(Student student, String subject) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quiz: " + title);
        System.out.println("Starting the quiz...");
        isOngoing = true; // Đặt cờ hiệu là đang diễn ra

        for (int i = 0; i < questions.size(); i++) {
            if (!isOngoing) { // Kiểm tra nếu bài thi đã bị dừng
                System.out.println("Quiz stopped due to time up or user interruption.");
                break;
            }
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getContent());

            List<String> options = question.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((char) ('A' + j) + ". " + options.get(j));
            }

            String answer;
            while (isOngoing) {
                System.out.print("Your answer (A, B, C, D) or type S to skip: ");
                answer = scanner.nextLine().toUpperCase();
                
                if (!isOngoing) { // Dừng lại nếu quiz đã dừng khi nhập xong
                    System.out.println("Quiz stopped due to time up.");
                    break;
                }

                if (answer.equals("S")) {
                    skippedQuestions.add(question);
                    answers.add(null);
                    break;
                } else if (isValidAnswer(answer, options.size())) {
                    Answer savedAnswer = new Answer(question.getContent(), options, question.getCorrectAnswer(), answer);
                    answers.add(savedAnswer);
                    break;
                } else {
                    System.out.println("Invalid input. Please enter A, B, C, or D.");
                }
            }
        }

        // Xử lý câu hỏi bị bỏ qua
        if (isOngoing) { // Chỉ xử lý nếu chưa dừng bài thi
            handleSkippedQuestions(scanner);
        }
        if (isOngoing) {
        // Tính điểm và hiển thị kết quả nếu chưa bị dừng
        finishQuiz(student,subject);
        }
    }

    public void stopQuiz() {
        isOngoing = false; // Đặt cờ hiệu là không hoạt động để dừng bài thi
    }
   
    private void handleSkippedQuestions(Scanner scanner) {
        while (!skippedQuestions.isEmpty() && isOngoing) {
            System.out.println("\nReviewing skipped questions.");
            Iterator<Question> iterator = skippedQuestions.iterator();

            while (iterator.hasNext()) {
                if (!isOngoing) { // Kiểm tra nếu bài thi đã bị dừng
                    break;
                }
                Question skippedQuestion = iterator.next();
                int index = questions.indexOf(skippedQuestion);
                System.out.println("Question " + (index + 1) + ": " + skippedQuestion.getContent());

                List<String> options = skippedQuestion.getOptions();
                for (int j = 0; j < options.size(); j++) {
                    System.out.println((char) ('A' + j) + ". " + options.get(j));
                }

                String answer;
                while (true) {
                    System.out.print("Your answer (A, B, C, D) or type S to skip: ");
                    answer = scanner.nextLine().toUpperCase();

                    if (answer.equals("S")) {
                        break;
                    } else if (isValidAnswer(answer, options.size())) {
                        Answer savedAnswer = new Answer(skippedQuestion.getContent(), options, skippedQuestion.getCorrectAnswer(), answer);
                        answers.set(index, savedAnswer);
                        iterator.remove();
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter A, B, C, or D.");
                    }
                }
            }
        }
    }
    
 // Phương thức tính điểm và lưu kết quả
    public void finishQuiz(Student student ,String subject) {
        int answeredQuestions = answers.size(); // Số lượng câu đã trả lời
        int totalQuestions = questions.size(); // Tổng số câu hỏi trong quiz

        // Nếu số câu trả lời ít hơn tổng số câu hỏi, bổ sung giá trị null cho các câu chưa trả lời
        while (answers.size() < totalQuestions) {
            answers.add(null);
        }

        int score = calculateScore(answers);
        System.out.println("Quiz completed! Your score: " + score + "/" + questions.size());

        // Lưu lại kết quả cho student
        student.addResult(new Result(subject,title, score, answeredQuestions, totalQuestions, "20th", answers));
    }


    
    //Kiểm tra xem thông tin đầu vào của người dùng có hợp lệ không (A, B, C hoặc D)
    private boolean isValidAnswer(String answer, int numOptions) {
        char answerChar = answer.charAt(0);
        return answer.length() == 1 && answerChar >= 'A' && answerChar < 'A' + numOptions;
    }

    // tính điểm 
    private int calculateScore(List<Answer> answers) {
        int score = 0;
        for (Answer answer : answers) {
            if (answer != null && answer.isCorrect()) {
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
    
    public int getTimeLimit() { return timeLimit; }
    public void setTimeLimit(Integer timeLimit) { this.timeLimit = timeLimit; }
    public List<Question> getQuestions() { return questions; }
    
    
    
    public void printInfo() {
        for (Question question : questions) {
            System.out.println("\n    Question: " + question.getContent());
            System.out.println("    Difficulty: " + question.getDifficulty());
            System.out.println("    Options: " + question.getOptions());
            System.out.println("    Correct Answer: " + question.getCorrectAnswer());
        }
    }
    
}
