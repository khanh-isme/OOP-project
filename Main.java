import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        // Tạo một đối tượng System để quản lý người dùng và bài thi
        Ssystem system1 = new Ssystem();

        // Tạo một giáo viên và thêm vào hệ thống
        Teacher teacher = new Teacher("hieu", "password123");
        system1.registerUser(teacher);

        // Tạo một bài thi
        Quiz quiz = teacher.createQuiz("Java Basics", 10); // 10 phút

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
        CountdownTimer countdown = new CountdownTimer(10); // đếm ngược 10 giây (đang k xài cái này) 
        TimedQuiz ab= new TimedQuiz(quiz,10);
     
        // Tạo học sinh và thêm vào hệ thống
        
        Student a =new Student("chó lương", "1", "1001");
        Student b= new Student("student2", "password2", "1002");
        system1.registerUser(b);
        system1.registerUser(a);
        
        LoginService loginService = new LoginService(system1.getUsers());
        User loggedInUser = loginService.LoginUser();
        
        // instanceof là một toán tử được sử dụng để kiểm tra xem một đối tượng có phải là một thể hiện (instance) của một lớp hoặc một giao diện (interface) cụ thể hay không
        if (loggedInUser != null) {
        	if (loggedInUser instanceof Student) {
            Student loggedInStudent = (Student) loggedInUser;// ép kiểu từ kiểu (User) thành kiểu (Student)
            // đang ép kiểu cho vui chứ chưa có xài 
            
            // Bắt đầu thi
            System.out.println("Starting the quiz for student...");
            ab.startTest();
            // Gọi phương thức startQuiz() để học sinh làm bài
        	}
        } 
        // đa hình ghi đè user teacher và student theo kiểu to_string để khi đăng nhập xong ta in ra 1 số thông tin của người đó 

        // Đăng xuất
        }
}
