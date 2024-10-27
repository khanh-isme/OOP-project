import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ssystem {
    private List<User> users;
    private List<Quiz> quizzes;

    public Ssystem() {
        this.users = new ArrayList<>();
        this.quizzes = new ArrayList<>();
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public User loginUser(String username,String password) { 	
        for (User user : users) {
            if (user.login(username, password)) {
                return user;
            }
        }
        return null;
    }

    public void logoutUser(User user) {
        user.logout();
    }

    // Getters and Setters
    public List<User> getUsers(){
    	return this.users;
    }
}
