import java.util.ArrayList;
import java.util.List;
// chưa sử dụng class này 
public class Admin extends User {
    private List<User> users;

    public Admin(String username, String password) {
        super(username, password, "Admin");
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void manageQuiz(Quiz quiz) {
        // Code to manage quiz
    }

    // Getters and Setters
}
