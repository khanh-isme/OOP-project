import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Ssystem {
    private List<User> users;
    

    public Ssystem(List<User> users) {
        this.users = users;
      
    }

 // Phương thức đăng ký người dùng mới (học sinh hoặc giáo viên)
    public void registerUser(User user) {
        users.add(user);
        System.out.println("User " + user.getUsername() + " registered successfully.");
    }

 // Phương thức đăng nhập người dùng dựa trên tên đăng nhập và mật khẩu
    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return user;
            }
        }
        System.out.println("Login failed. Incorrect username or password.");
        return null; // Trả về null nếu không đăng nhập được
    }
    
   
    

 // Phương thức lấy tất cả học sinh trong hệ thống (dùng để giáo viên xem kết quả)
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Student) {
                students.add((Student) user);
            }
        }
        return students;
    }
    

    
    public void logoutUser(User user) {
        user.logout();
    }

    // Getters and Setters
    public List<User> getUsers(){
    	return this.users;
    }
    public void addUser(User user) {
    	this.users.add(user);
    }
    
    
    
    
    public static void writeUsersToFile(List<User> users, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (User user : users) {
                if (user instanceof Admin) {
                    writer.write(formatAdmin((Admin) user) + "\n");
                } else if (user instanceof Student) {
                    writer.write(formatStudent((Student) user) + "\n");
                } else if (user instanceof Teacher) {
                    writer.write(formatTeacher((Teacher) user) + "\n");
                }
            }
            System.out.println("Dữ liệu đã được ghi vào file: " + fileName);
        } catch (IOException e) {
            System.err.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    // Định dạng chuỗi cho Admin
    private static String formatAdmin(Admin admin) {
        return "Admin," + admin.getUsername() + "," + admin.getPassword();
    }

    // Định dạng chuỗi cho Student
    private static String formatStudent(Student student) {
        return "Student," + student.getUsername() + "," + student.getPassword() + 
               ",student," + student.getMssv();
    }

    // Định dạng chuỗi cho Teacher
    private static String formatTeacher(Teacher teacher) {
        return "Teacher," + teacher.getUsername() + "," + teacher.getPassword() + 
               ",teacher";
    }
    
}
