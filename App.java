import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    private Ssystem system; // Hệ thống quản lý người dùng và bài thi
    private Bank bank;
    private Scanner sc;     // Để đọc đầu vào từ bàn phím

    public App(Bank bank,List<User> user) {
        system = new Ssystem(user);
        sc = new Scanner(System.in);
        //setupSampleData(); // Thiết lập dữ liệu mẫu ban đầu
        this.bank=bank;
        
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
            } else if (loggedInUser instanceof Admin) {
                runAdminMenu((Admin) loggedInUser);
            }else {
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
            Teacher teacher = new Teacher(username, password,"teacher");
            system.registerUser(teacher);
            System.out.println("Teacher account registered successfully.");
        } else if (choice == 2) {
            System.out.print("Enter Student ID: ");
            String studentId = sc.nextLine();
            Student student = new Student(username, password, "student",studentId);
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
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. Take Quiz");
            System.out.println("2. reviewInfo");
            System.out.println("3. view ExamSchedule");
            System.out.println("4. View Detailed Quiz History");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1 -> takeQuiz(student);
                case 2 -> student.reviewInfo();
                case 3 -> viewExamSchedule(bank);
                case 4 -> student.viewDetailedQuizHistory();
                case 5 -> {
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
            System.out.println("\n=== Teacher Menu ===");
            System.out.println("1. Create Quiz");
            System.out.println("2. infomation");
            System.out.println("3. view Bank");
            System.out.println("4. edit Bank");
            System.out.println("5. delete Bank");
            System.out.println("6. saveData");
            System.out.println("7. Logout");
            System.out.print("Choose an option: ");
            int option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1:
                    createQuiz(teacher);
                    break;
                case 2:
                	teacher.reviewInfo();
                    break;
                case 3:
                	bank.printAllInfo();
                	break;
                case 4:
                	editData(bank);
                	break;
                case 5:
                	deleteBank(bank);
                	break;
                case 6:
                	bank.writeDataToFile("data.txt");
                    break; 
                case 7:
                	return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    
    private void runAdminMenu(Admin admin) {
    	admin.addlistUser(system.getUsers());
    while (true) {
        // Hiển thị menu
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. Thêm người dùng");
        System.out.println("2. Xóa người dùng");
        System.out.println("3. Chỉnh sửa thông tin Student");
        System.out.println("4. Chỉnh sửa thông tin Teacher");
        System.out.println("5. Hiển thị danh sách người dùng");
        System.out.println("6. lưu");
        System.out.println("7. Thoát");
        System.out.print("Chọn chức năng: ");

        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1: // Thêm người dùng
                System.out.print("Loại người dùng (1-Student, 2-Teacher): ");
                int userType = Integer.parseInt(sc.nextLine());

                System.out.print("Nhập username: ");
                String username = sc.nextLine();
                System.out.print("Nhập password: ");
                String password = sc.nextLine();

                if (userType == 1) {
                    System.out.print("Nhập MSSV: ");
                    String mssv = sc.nextLine();
                    System.out.print("Nhập role: ");
                    String role = sc.nextLine();
                    Student student = new Student(username, password, role, mssv);
                    admin.addUser(student);
                } else if (userType == 2) {
                    Teacher teacher = new Teacher(username, password, username);
                    admin.addUser(teacher);
                } else {
                    System.out.println("Loại người dùng không hợp lệ.");
                }
                break;

            case 2: // Xóa người dùng
                System.out.print("Nhập username người dùng cần xóa: ");
                String usernameToRemove = sc.nextLine();
                User userToRemove = null;

                for (User user : system.getUsers()) {
                    if (user.getUsername().equals(usernameToRemove)) {
                        userToRemove = user;
                        break;
                    }
                }

                if (userToRemove != null) {
                    admin.removeUser(userToRemove);
                    System.out.println("Người dùng đã được xóa.");
                } else {
                    System.out.println("Không tìm thấy người dùng.");
                }
                break;

            case 3: // Chỉnh sửa Student
                System.out.print("Nhập username của Student cần chỉnh sửa: ");
                String studentUsername = sc.nextLine();
                Student studentToEdit = null;

                for (User user : system.getUsers()) {
                    if (user instanceof Student && user.getUsername().equals(studentUsername)) {
                        studentToEdit = (Student) user;
                        break;
                    }
                }

                if (studentToEdit != null) {
                    admin.editforStudent(studentToEdit);
                } else {
                    System.out.println("Không tìm thấy Student.");
                }
                break;

            case 4: // Chỉnh sửa Teacher
                System.out.print("Nhập username của Teacher cần chỉnh sửa: ");
                String teacherUsername = sc.nextLine();
                Teacher teacherToEdit = null;

                for (User user : system.getUsers()) {
                    if (user instanceof Teacher && user.getUsername().equals(teacherUsername)) {
                        teacherToEdit = (Teacher) user;
                        break;
                    }
                }

                if (teacherToEdit != null) {
                    admin.editforTeacher(teacherToEdit);
                } else {
                    System.out.println("Không tìm thấy Teacher.");
                }
                break;

            case 5: // Hiển thị danh sách người dùng
                System.out.println("Danh sách người dùng:");
                admin.showUser(system.getUsers());
                break;
            
            case 6:
            	system.writeUsersToFile(system.getUsers(), "data1.txt");
            case 7: // Thoát
                System.out.println("Thoát chương trình.");
                return;
             
            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
        }
    }
}
    
    
   
    
    // Học sinh làm bài thi	
    private void takeQuiz(Student student) {
        System.out.println("môn: ");
        for (Subject subject : bank.getSubjects()) {
            System.out.println(subject.getName());
        }
        System.out.print("Enter the name of the subject you want to take: ");
        String subjectname = sc.nextLine();
        
        // Khởi tạo biến subject1
        Subject subject1 = null;
        
        for (Subject subject : bank.getSubjects()) {
            if (subject.getName().equals(subjectname)) {
                subject1 = subject;
                break; // Thoát vòng lặp sau khi tìm thấy subject
            }
        }

        // Kiểm tra nếu không tìm thấy subject
        if (subject1 == null) {
            System.out.println("Subject not found.");
            return;
        }

        for (Quiz quiz : subject1.getQuizzes()) {
            System.out.println(quiz.getTitle());
        }

        System.out.print("Enter the title of the quiz you want to take: ");
        String quizTitle = sc.nextLine();

        Quiz quiz = subject1.getQuizByTitle(quizTitle);
        if (quiz == null) {
            System.out.println("Quiz not found.");
            return;
        }
        
     // Khởi tạo và bắt đầu bài thi có giới hạn thời gian
        TimedQuiz timedQuiz = new TimedQuiz(quiz, student, quiz.getTimeLimit(),subject1.getName());
       
        //quiz.startQuiz(student,subject1.getName());
        
        Thread quizThread = new Thread(() -> timedQuiz.startWithTimer());
        quizThread.start();
        
        try {
            quizThread.join();
        } catch (InterruptedException e) {
            System.out.println("Quiz interrupted");
        }

        // Sau khi quiz kết thúc, chương trình sẽ quay lại menu
        System.out.println("Returning to menu...");
    }


    

    // Giáo viên tạo bài thi
    private void createQuiz(Teacher teacher) {
    	System.out.print("Enter subject name: ");
    	String name =sc.nextLine();
    	Subject subjectnew= new Subject(name,null);
    	
        System.out.print("Enter quiz title: ");
        String title = sc.nextLine();
        System.out.print("Enter quiz time limit (in minutes): ");
        int timeLimit = Integer.parseInt(sc.nextLine());
        Quiz quiz = new Quiz(title,timeLimit);       
         

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
        
        teacher.addquizzesCreated(quiz);
        subjectnew.addQuiz(quiz);
        bank.addSubject(subjectnew);
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
    
    
    
    public void viewExamSchedule(Bank bank) {
        if (bank == null || bank.getSubjects() == null || bank.getSubjects().isEmpty()) {
            System.out.println("No exam schedule available.");
            return;
        }

        System.out.println("Exam Schedule (Date: " + bank.getDate() + "):");

        for (Subject subject : bank.getSubjects()) {
            System.out.println("Subject: " + subject.getName());

            if (subject.getQuizzes() == null || subject.getQuizzes().isEmpty()) {
                System.out.println("  No quizzes available for this subject.");
                continue;
            }

            for (Quiz quiz : subject.getQuizzes()) {
                System.out.println("  Quiz Title: " + quiz.getTitle());
                System.out.println("    Number of Questions: " + (quiz.getQuestions() != null ? quiz.getQuestions().size() : 0));
                System.out.println("    Time Limit: " + quiz.getTimeLimit() + " minutes");
            }
        }
    }
    
    

    // Thiết lập dữ liệu mẫu ban đầu cho hệ thống
    /*private void setupSampleData() {
        // Tạo giáo viên và thêm vào hệ thống
        Teacher teacher = new Teacher("b", "1");
        system.registerUser(teacher);

        // Tạo học sinh và thêm vào hệ thống
        Student student = new Student("a", "1", "S001");
        system.registerUser(student);
        }
      */  
       

        // Tạo một bài thi
        /*Quiz quiz = teacher.createQuiz("A", 10); // 10 phút

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
        
       system.addQuiz(quiz);*/
    
    
    //
    private void editData(Bank bank) {
        Editor editor = new Editor();
        System.out.print("You want to edit bank? (yes/no): ");
        if (sc.nextLine().equalsIgnoreCase("yes")) {
            editor.editBank(bank);
            System.out.println("--------");
        }        
    }
    
    private void deleteBank(Bank bank) {
    	Editor editor = new Editor();
        System.out.print("You want to delete bank? (yes/no): ");
        if (sc.nextLine().equalsIgnoreCase("yes")) {
            editor.deleteData(bank);
            System.out.println("--------");
        }  
    }
    
    
    
}
