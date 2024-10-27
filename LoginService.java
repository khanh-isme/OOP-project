import java.util.Scanner;
import java.util.List;
public class LoginService {
	//đăng nhập từ bàn phím
	private List<User> users;
	
	public LoginService( List<User> users) {
		this.users=users;
	}
	
	public User LoginUser() {
		// dang nhap
		
		System.out.println("đăng nhập đi lũ ngu ");
		Scanner sc = new Scanner(System.in);
		System.out.println("user: ");
		String username = sc.nextLine();
		System.out.println("pass: ");
		String pass= sc.nextLine();
		
		//xac thuc
		for(User user : users ) {
			if(user.getUsername().equals(username) && user.getPassword().equals(pass)) {
				System.out.println("làm bài đi lũ óc chó");
				return user;
			}
		}
		System.out.println(" đéo có");
		return null;
	}
}
