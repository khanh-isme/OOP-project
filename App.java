import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class App {

    private Ssystem system; // Hệ thống quản lý người dùng và bài thi
    private Bank bank;
    private Scanner sc;     

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
            System.out.print("Choose an option (1/2/3): ");
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

        // Kiểm tra xem username đã tồn tại chưa
        if (system.isUsernameExists(username)) { // Thêm hàm kiểm tra vào hệ thống
            System.out.println("Error: Username already exists. Please try a different username.");
            return; // Dừng việc đăng ký nếu username bị trùng
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.println("Are you a Teacher or a Student?");
        System.out.print("Enter 1 for Teacher, 2 for Student: ");
        int choice = Integer.parseInt(sc.nextLine());

        if (choice == 1) {
            System.out.print("Enter teacher role: ");
            String teacherRole = sc.nextLine();
            Teacher teacher = new Teacher(username, password, teacherRole, null);
            system.registerUser(teacher);
            System.out.println("Teacher account registered successfully.");
        } else if (choice == 2) {
            System.out.print("Enter Student ID: ");
            String studentId = sc.nextLine();
            System.out.print("Enter Student of year: ");
            String year = sc.nextLine();
            Student student = new Student(username, password, year, studentId, null, null);
            system.registerUser(student);
            System.out.println("Student account registered successfully.");
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

 // Menu cho học sinh
    private void runStudentMenu(Student student) {
        while (true) {
            System.out.println("\n Welcome, " + student.getUsername());
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. Take Quiz");
            System.out.println("2. review Info");// xem thành tích đầy đủ luôn r
            System.out.println("3. view ExamSchedule");
            System.out.println("4. View Detailed Quiz History");
            System.out.println("5. registered Subjectst");
            System.out.println("6. edit account");
            System.out.println("7. Logout");
            System.out.print("Choose an option: ");
            int option = Integer.parseInt(sc.nextLine());
            Editor editor = new Editor();

            switch (option) {
                case 1 :
                	takeQuiz(student);
                	system.writeUsersToFile3(system.getUsers(),"data1.txt");//auto save
                	break;
                case 2 :
                	student.reviewInfo();
                	break;
                case 3 : 
                	viewExamSchedule(bank);
                	break;
                case 4 : 
                	student.viewDetailedQuizHistory();
                	break;
                case 5:
                	registerSubject(student);
                	system.writeUsersToFile3(system.getUsers(),"data1.txt");
                	break;
                case 6 : 
                	editor.updateStudent(student,system);
                	system.writeUsersToFile3(system.getUsers(),"data1.txt");
                	break;
                case 7 : {
                    system.logoutUser(student);
                    return;
                }
                default : System.out.println("Invalid option. Try again.");
            }
        }
    }
    
    
   

    // Menu cho giáo viên
    private void runTeacherMenu(Teacher teacher) {
        while (true) {
            System.out.println("\n Welcome, " + teacher.getUsername());
            System.out.println("\n=== Teacher Menu ===");
            System.out.println("1. Create Quiz");
            System.out.println("2. infomation");
            System.out.println("3. view Bank");
            System.out.println("4. edit Bank");
            System.out.println("5. delete Bank");
            System.out.println("6. edit account");
            System.out.println("7. saveData");
            System.out.println("8. Logout");
            System.out.print("Choose an option: ");
            int option = Integer.parseInt(sc.nextLine());
            Editor editor = new Editor();
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
                	editor.updateTeacher(teacher,system);
                	system.writeUsersToFile3(system.getUsers(),"data1.txt");//auto save
                	break;
                case 7:
                	bank.writeDataToFile("data.txt");
                    break;
                case 8:
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
                
                // Kiểm tra xem username đã tồn tại chưa
                if (system.isUsernameExists(username)) { // Thêm hàm kiểm tra vào hệ thống
                    System.out.println("Error: Username already exists. Please try a different username.");
                    break; // Dừng việc đăng ký nếu username bị trùng
                }
                
                System.out.print("Nhập password: ");
                String password = sc.nextLine();

                if ( userType == 1) {
                    System.out.print("Nhập MSSV: ");
                    String mssv = sc.nextLine();
                    System.out.print("Nhập role: ");
                    String role = sc.nextLine();
                    Student student = new Student(username, password, role, mssv,null,null);
                    admin.addUser(student);
                } else if (userType == 2) {
                    Teacher teacher = new Teacher(username, password, username,null);
                    admin.addUser(teacher);
                } else {
                    System.out.println("Loại người dùng không hợp lệ.");
                }
                break;

            case 2: // Xóa người dùng
            	for (User user : system.getUsers()) {
            		System.out.println(user.getUsername());
            	}
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
            	// Hiển thị danh sách các Student
            	System.out.println("Danh sách các Student hiện tại:");
            	boolean hasStudents = false;
            	for (User user : system.getUsers()) {
            	    if (user instanceof Student) {
            	        System.out.println("- " + user.getUsername());
            	        hasStudents = true; // Đánh dấu rằng có ít nhất một Student
            	    }
            	}

            	// Kiểm tra nếu không có Student nào trong danh sách
            	if (!hasStudents) {
            	    System.out.println("Không có Student nào trong hệ thống.");
            	    return; // Kết thúc nếu không có Student
            	}

            	// Yêu cầu nhập username của Student cần chỉnh sửa
            	System.out.print("Nhập username của Student cần chỉnh sửa: ");
            	String studentUsername = sc.nextLine();

            	// Tìm kiếm Student cần chỉnh sửa
            	Student studentToEdit = null;
            	for (User user : system.getUsers()) {
            	    if (user instanceof Student && user.getUsername().equalsIgnoreCase(studentUsername)) {
            	        studentToEdit = (Student) user;
            	        break; // Dừng tìm kiếm khi tìm thấy
            	    }
            	}

            	// Kiểm tra và xử lý kết quả tìm kiếm
            	if (studentToEdit != null) {
            	    // Gọi hàm chỉnh sửa từ admin
            	    admin.editforStudent(studentToEdit, system);
            	    System.out.println("Thông tin của Student đã được chỉnh sửa thành công.");
            	} else {
            	    System.out.println("Không tìm thấy Student có username: " + studentUsername);
            	}

            	
            	break;
            case 4: // Chỉnh sửa Teacher
            	// Hiển thị danh sách các Teacher hiện tại
            	System.out.println("Danh sách các Teacher hiện tại:");
            	boolean hasTeachers = false;
            	for (User user : system.getUsers()) {
            	    if (user instanceof Teacher) {
            	        System.out.println("- " + user.getUsername());
            	        hasTeachers = true; // Đánh dấu rằng có ít nhất một Teacher
            	    }
            	}

            	// Kiểm tra nếu không có Teacher nào
            	if (!hasTeachers) {
            	    System.out.println("Không có Teacher nào trong hệ thống.");
            	    return; // Kết thúc nếu không có Teacher
            	}

            	// Yêu cầu nhập username của Teacher cần chỉnh sửa
            	System.out.print("Nhập username của Teacher cần chỉnh sửa: ");
            	String teacherUsername = sc.nextLine();

            	// Tìm kiếm Teacher cần chỉnh sửa
            	Teacher teacherToEdit = null;
            	for (User user : system.getUsers()) {
            	    if (user instanceof Teacher && user.getUsername().equalsIgnoreCase(teacherUsername)) {
            	        teacherToEdit = (Teacher) user;
            	        break; // Dừng tìm kiếm khi tìm thấy
            	    }
            	}

            	// Xử lý kết quả tìm kiếm
            	if (teacherToEdit != null) {
            	    // Gọi hàm chỉnh sửa từ admin
            	    admin.editforTeacher(teacherToEdit, system);
            	    System.out.println("Thông tin của Teacher đã được chỉnh sửa thành công.");
            	} else {
            	    System.out.println("Không tìm thấy Teacher có username: " + teacherUsername);
            	}

                break;

            case 5: // Hiển thị danh sách người dùng
                System.out.println("Danh sách người dùng:");
                admin.showUser(system.getUsers());
                break;
            
            case 6:
            	system.writeUsersToFile3(system.getUsers(),"data1.txt");
            	break;
            	
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
        System.out.println("Available Subjects: ");
        for (Subject subject : bank.getSubjects()) {
            System.out.println(subject.getName());
        }

        System.out.print("Enter the name of the subject you want to take: ");
        String subjectName = sc.nextLine();

        // Tìm subject theo tên
        Subject selectedSubject = null;
        for (Subject subject : bank.getSubjects()) {
            if (subject.getName().equalsIgnoreCase(subjectName)) {
                selectedSubject = subject;
                break;
            }
        }

        // Kiểm tra nếu không tìm thấy subject
        if (selectedSubject == null) {
            System.out.println("Subject not found.");
            return;
        }

        String pattern = "yyyy-MM-dd HH:mm";
        String ngayThi = selectedSubject.getNgayThi();
        String currentTime = student.getCurrentTimeAsString();

        // So sánh thời gian hiện tại với ngày thi và thời gian kết thúc
        int startComparison = compareDates(ngayThi, currentTime, pattern);
        String endTime = addTime(ngayThi, pattern, selectedSubject.getTime());
        int endComparison = compareDates(endTime, currentTime, pattern);

        if (startComparison > 0) {
            System.out.println("The exam date has not arrived yet: " + ngayThi);
            return;
        }

        if (endComparison < 0) {
            System.out.println("The time to take the quiz has passed: " + endTime);
            return;
        }

        // Lấy danh sách các bài quiz trong môn học
        List<Quiz> quizzes = selectedSubject.getQuizzes();
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available for this subject.");
            return;
        }

        // Chọn một bài quiz ngẫu nhiên
        Random random = new Random();
        Quiz selectedQuiz = quizzes.get(random.nextInt(quizzes.size()));

        System.out.println("Randomly selected quiz: " + selectedQuiz.getTitle());

        // Khởi tạo và bắt đầu bài thi có giới hạn thời gian
        TimedQuiz timedQuiz = new TimedQuiz(selectedQuiz, student, selectedQuiz.getTimeLimit(), selectedSubject);
        Thread quizThread = new Thread(timedQuiz::startWithTimer);
        quizThread.start();

        try {
            quizThread.join();
        } catch (InterruptedException e) {
            System.out.println("Quiz interrupted.");
        }

        // Sau khi quiz kết thúc, quay lại menu
        System.out.println("Returning to menu...");
    }


    // Giáo viên tạo bài thi
    private void createQuiz(Teacher teacher) {
        System.out.println("Do you want to create a quiz for an existing subject or a new subject? (Enter 'existing' or 'new'):");
        String choice = sc.nextLine();
        Subject subject;
       
        if (choice.equalsIgnoreCase("existing")) {
            System.out.println("Enter the name of the existing subject:");
            String subjectName = sc.nextLine();
            subject = bank.findSubjectByName(subjectName);

            if (subject == null) {
                System.out.println("Subject not found. Please try again or create a new subject.");
                return;
            }
        } else if (choice.equalsIgnoreCase("new")) {
            System.out.print("Enter new subject name: ");
            String name = sc.nextLine();
            System.out.print("Enter exam day: ");
            String day = sc.nextLine();
            System.out.print("Enter time the exam opens: ");
            String time = sc.nextLine();
            
            subject = new Subject(name, null, day, Integer.parseInt(time));
            bank.addSubject(subject);
        } else {
            System.out.println("Invalid choice. Please enter 'existing' or 'new'.");
            return;
        }

        System.out.print("Enter quiz title: ");
        String title = sc.nextLine();
        System.out.print("Enter quiz time limit (in minutes): ");
        int timeLimit = Integer.parseInt(sc.nextLine());
        Quiz quiz = new Quiz(title, timeLimit);

        while (true) {
            System.out.println("Add a question to the quiz");
            System.out.print("Enter question text: ");
            String questionText = sc.nextLine();
            System.out.print("Enter difficulty (Easy/Medium/Hard): ");
            String difficulty = sc.nextLine();
            System.out.println("Enter the answer options (comma separated): ");
            String[] options = sc.nextLine().split(",");
            System.out.print("Enter correct answer: ");
            String correctAnswer = sc.nextLine();
            

            Question question = new Question(
                questionText,
                Arrays.asList(options),
                correctAnswer,
                difficulty
            );
            quiz.addQuestion(question);

            System.out.print("Do you want to add another question? (yes/no): ");
            if (!sc.nextLine().equalsIgnoreCase("yes")) {
                break;
            }
        }

        teacher.addquizzesCreated(quiz);
        subject.addQuiz(quiz);

        System.out.println("Quiz successfully created and added to the subject.");
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
            System.out.println("Subject: " + subject.getName()+ " "+subject.getNgayThi() +" time open exam: "+ subject.getTime());

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
    
    
    public void registerSubject(Student student) {
        // Hiển thị danh sách môn học
        System.out.println("Available Subjects:");
        for (Subject subject : bank.getSubjects()) {
            System.out.println(subject.getName());
        }

        System.out.print("Enter the name of the subject you want to register for: ");
        String subjectName = sc.nextLine();

        // Tìm môn học theo tên
        Subject selectedSubject = null;
        for (Subject subject : bank.getSubjects()) {
            if (subject.getName().equalsIgnoreCase(subjectName)) {
                selectedSubject = subject;
                break;
            }
        }

        // Kiểm tra nếu môn học không tồn tại
        if (selectedSubject == null) {
            System.out.println("Subject not found. Please try again.");
            return;
        }

        // Kiểm tra nếu học sinh đã đăng ký môn học này
        if (student.getRegisteredSubjects().contains(selectedSubject)) {
            System.out.println("You have already registered for this subject.");
            return;
        }

        // Đăng ký môn học
        student.getRegisteredSubjects().add(selectedSubject);
        System.out.println("Successfully registered for: " + selectedSubject.getName());
    } 
    
    
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
    
    
    public static int compareDates(String dateStr1, String dateStr2, String pattern) {
        try {
            // Định dạng thời gian
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

            // Chuyển chuỗi thành LocalDateTime
            LocalDateTime date1 = LocalDateTime.parse(dateStr1, formatter);
            LocalDateTime date2 = LocalDateTime.parse(dateStr2, formatter);

            // So sánh hai ngày
            return date1.compareTo(date2); // Trả về -1, 0 hoặc 1
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + e.getMessage());
            return 0; // Lỗi định dạng
        }
    }
    
    public static String addTime(String dateTimeStr, String pattern,  int minutes ) {
        try {
            // Định dạng thời gian
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

            // Chuyển chuỗi thành LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);

            // Cộng thêm thời gian
            dateTime = dateTime.plusMinutes(minutes);

            // Chuyển lại thành chuỗi và trả về
            return dateTime.format(formatter);
        } catch (Exception e) {
            System.out.println("Error processing date: " + e.getMessage());
            return null;
        }
    }
}
