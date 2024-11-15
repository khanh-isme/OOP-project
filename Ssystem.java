import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ssystem {
    private List<User> users;
    

    public Ssystem() {
        this.users = new ArrayList<>();
      
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
}
