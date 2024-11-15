import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankDataLoader {
    public static Bank loadBankFromFile(String fileName) {
        Bank bank = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Subject currentSubject = null;
            Quiz currentQuiz = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("Bank:")) {
                    String date = line.split(":")[1].trim();
                    
                    bank = new Bank(null,date);
                } else if (line.startsWith("Subject:")) {
                    String subjectName = line.split(":")[1].trim();
                    currentSubject = new Subject(subjectName,null);
                    if (bank != null) bank.addSubject(currentSubject);
                } else if (line.startsWith("Quiz:")) {
                    String quizTitle = line.split(":")[1].trim();
                    currentQuiz = new Quiz(quizTitle, 0); // Time limit will be set later
                    if (currentSubject != null) currentSubject.addQuiz(currentQuiz);
                } else if (line.startsWith("TimeLimit:")) {
                    int timeLimit = Integer.parseInt(line.split(":")[1].trim());
                    if (currentQuiz != null) currentQuiz.setTimeLimit(timeLimit);
                } else if (line.startsWith("Question:")) {
                    String questionContent = line.split(":")[1].trim();
                    List<String> options = new ArrayList<>();
                    String correctAnswer = "";
                    String difficulty = "";

                    // Continue reading options, answer, and difficulty
                    while ((line = reader.readLine()) != null && !line.startsWith("Question:")) {
                        line = line.trim();
                        if (line.startsWith("Options:")) {
                            options = Arrays.asList(line.split(":")[1].trim().split(","));
                        } else if (line.startsWith("CorrectAnswer:")) {
                            correctAnswer = line.split(":")[1].trim();
                        } else if (line.startsWith("Difficulty:")) {
                            difficulty = line.split(":")[1].trim();
                            break; // End of this question data
                        }
                    }
                    Question question = new Question(questionContent, options, correctAnswer, difficulty);
                    if (currentQuiz != null) currentQuiz.addQuestion(question);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bank;
    }
    
    
    
    public static List<User> readFileAndParseUsers(String filePath) {
        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // Xử lý từng dòng theo loại người dùng
                String userType = data[0];
                switch (userType) {
	                case "Admin":
	                    Admin admin= new Admin(data[1], data[2]);
	                    users.add(admin);
	                    break;
                    case "Student":
                        Student student = new Student(data[1], data[2], data[3], data[4]);
                        users.add(student);
                        break;
                    case "Teacher":
                        Teacher teacher = new Teacher(data[1], data[2], data[3]);
                        users.add(teacher);
                        break;
                    default:
                        System.out.println("Unknown user type: " + userType);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
}
