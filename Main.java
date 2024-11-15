



// thiếu lớp trừu tượng hàm trừu tượng và interface
public class Main {

    public static void main(String[] args) {
        // Đường dẫn đến file chứa dữ liệu
        String fileName = "C:\\Users\\MSI-PC\\eclipse-workspace\\project-in-school\\bin\\data.txt"; // Đảm bảo rằng file này tồn tại và có dữ liệu đúng định dạng

        // Tạo Bank từ dữ liệu trong file
        Bank bank = BankDataLoader.loadBankFromFile(fileName);

        // Kiểm tra nếu bank đã được tải thành công
        if (bank != null) {
            System.out.println("Dữ liệu đã được tải thành công từ file:");
            System.out.println(bank);
        } else {
            System.out.println("Không thể tải dữ liệu từ file.");
        }

        // Khởi tạo App và chạy chương trình
        App app = new App(bank);
        app.run();
    }
}
