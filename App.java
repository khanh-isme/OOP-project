import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    private Ssystem system; // Hệ thống quản lý người dùng và bài thi
    private Scanner sc;     // Để đọc đầu vào từ bàn phím

    public App() {
        system = new Ssystem();
        sc = new Scanner(System.in);
        setupSampleData(); // Thiết lập dữ liệu mẫu ban đầu
    }

    // Phương thức để khởi chạy ứng dụng
    public void run() {
        while (true) {
            System.out.println("Welcome to the Quiz System");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> login();
                case 2 -> register();
                case 3 -> {
                    System.out.println("Exiting the system.");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
    
    private void login() {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        User loggedInUser = system.loginUser(username, password);
        if (loggedInUser == null) {
            System.out.println("Invalid username or password. Please try again.");
        } else {
            if (loggedInUser instanceof Student) {
                runStudentMenu((Student) loggedInUser);
            } else if (loggedInUser instanceof Teacher) {
                runTeacherMenu((Teacher) loggedInUser);
            } else {
                System.out.println("Unknown user type.");
            }
        }
    }
    
    private void register() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.println("Are you a Teacher or a Student?");
        System.out.print("Enter 1 for Teacher, 2 for Student: ");
        int choice = Integer.parseInt(sc.nextLine());

        if (choice == 1) {
            Teacher teacher = new Teacher(username, password);
            system.registerUser(teacher);
            System.out.println("Teacher account registered successfully.");
        } else if (choice == 2) {
            System.out.print("Enter Student ID: ");
            String studentId = sc.nextLine();
            Student student = new Student(username, password, studentId);
            system.registerUser(student);
            System.out.println("Student account registered successfully.");
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }
 // Menu cho học sinh
    private void runStudentMenu(Student student) {
        while (true) {
            System.out.println("Welcome, " + student.getUsername());
            System.out.println("1. Take Quiz");
            System.out.println("2. View Quiz History");
            System.out.println("3. View Detailed Quiz History");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1 -> takeQuiz(student);
                case 2 -> viewQuizHistory(student);
                case 3 -> student.viewDetailedQuizHistory();
                case 4 -> {
                    system.logoutUser(student);
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }


    // Menu cho giáo viên
    private void runTeacherMenu(Teacher teacher) {
        while (true) {
            System.out.println("Welcome, " + teacher.getUsername());
            System.out.println("1. Create Quiz");
            System.out.println("2. Grade Quiz");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1:
                    createQuiz(teacher);
                    break;
                case 2:
                   // gradeQuiz(teacher);
                    break;
                case 3:
                    return; // Quay lại màn hình đăng nhập
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    
    
    
    
  
    
    
    
    
    
    // Học sinh làm bài thi	
    private void takeQuiz(Student student) {
        System.out.println("Available quizzes:");
        for (Quiz quiz : system.getAllQuizzes()) {
            System.out.println(quiz.getTitle());
        }

        System.out.print("Enter the title of the quiz you want to take: ");
        String quizTitle = sc.nextLine();

        Quiz quiz = system.getQuizByTitle(quizTitle);
        if (quiz == null) {
            System.out.println("Quiz not found.");
            return;
        }
       
        quiz.startQuiz();
    }

    // Xem lại câu trả lời của học sinh
    private void reviewAnswers(Student student) {
        // Hiển thị các câu hỏi và câu trả lời của học sinh
        System.out.println("Reviewing answers...");
        // Logic để review bài làm của học sinh
    }

    // Giáo viên tạo bài thi
    private void createQuiz(Teacher teacher) {
        System.out.print("Enter quiz title: ");
        String title = sc.nextLine();
        System.out.print("Enter quiz time limit (in minutes): ");
        int timeLimit = Integer.parseInt(sc.nextLine());
        Ssystem system1= new Ssystem();
        Quiz quiz = new Quiz(title,timeLimit);
        system1.addQuiz(quiz); 

        while (true) {
            System.out.println("Add a question to the quiz");
            System.out.print("Enter question text: ");
            String questionText = sc.nextLine();
            System.out.print("Enter difficulty (Easy/Medium/Hard): ");
            String difficulty = sc.nextLine();
            System.out.print("Enter correct answer: ");
            String correctAnswer = sc.nextLine();
            // Thêm lựa chọn vào câu hỏi
            System.out.println("Enter the answer options (comma separated): ");
            String[] options = sc.nextLine().split(",");

            Question question1 = new Question(
            		 questionText,
                    Arrays.asList(options),
                    correctAnswer,
                    difficulty
            );
            quiz.addQuestion(question1);

            System.out.print("Do you want to add another question? (yes/no): ");
            if (!sc.nextLine().equalsIgnoreCase("yes")) {
                break;
            }
        }
    }

    // Giáo viên chấm bài thi
   
    // chức năng thêm đánh giá phản hồi 
    public void addFeedback(Student student, Quiz quiz, String feedback) {
        System.out.println("Enter feedback for student: ");
        String teacherFeedback = sc.nextLine();
        
        student.addFeedback(quiz, teacherFeedback);
    }
    
   
    
    //
    private void viewQuizHistory(Student student) {
        System.out.println("Quiz History:");
        for (Result result : student.getQuizHistory()) {
            System.out.println("Quiz: " + result.getQuizName() + " - Score: " + result.getScore());
        }
    }

    // Thiết lập dữ liệu mẫu ban đầu cho hệ thống
    private void setupSampleData() {
        // Tạo giáo viên và thêm vào hệ thống
        Teacher teacher = new Teacher("teacher1", "pass123");
        system.registerUser(teacher);

        // Tạo học sinh và thêm vào hệ thống
        Student student = new Student("a", "1", "S001");
        system.registerUser(student);
        
        
        
    


        // Tạo một bài thi
        Quiz quiz = teacher.createQuiz("A", 10); // 10 phút

        // Thêm câu hỏi vào bài thi
        Question question1 = new Question(
                "What is the size of int in Java?",
                Arrays.asList("4 bytes", "8 bytes", "16 bytes", "32 bytes"),
                "A",
                "Easy"
        );
        Question question2 = new Question(
                "What keyword is used to define a class in Java?",
                Arrays.asList("struct", "class", "define", "void"),
                "B",
                "Easy"
        );

        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        
       system.addQuiz(quiz);
    }
}
