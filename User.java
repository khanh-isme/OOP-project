

public class User {
    protected String username;
    protected String password;
    protected String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
        //hàm equal dùng để so sánh 2 chuỗi với nhau
    }

    public void logout() {
        
    }

    // Getters and Setters
    public String getUsername() {
    	return this.username;
    }
    public String getPassword() {
    	return this.password;
    }
  
    	
}