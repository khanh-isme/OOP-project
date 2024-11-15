import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Subject> subjects;
    private String date;

    public Bank(List<Subject> subjects, String date) {
        this.subjects = subjects != null ? subjects : new ArrayList<>();
        this.date = date;
    }

    // Getter cho subjects
    public List<Subject> getSubjects() {
        return subjects;
    }

    // Setter cho subjects
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    // Getter cho date
    public String getDate() {
        return date;
    }

    // Setter cho date
    public void setDate(String date) {
        this.date = date;
    }

    // Phương thức thêm Subject vào danh sách
    public void addSubject(Subject subject) {
        if (subject != null) {
            this.subjects.add(subject);
        }
    }

    // Phương thức xoá Subject khỏi danh sách
    public void removeSubject(Subject subject) {
        this.subjects.remove(subject);
    }

    // Override phương thức toString() để dễ dàng in thông tin của Bank
    @Override
    public String toString() {
        return "Bank{subjects=" + subjects + ", date=" + date + "}";
    }
    
    public void printAllInfo() {
        System.out.println("Bank Date: " + date);
        for (Subject subject : subjects) {
            System.out.println("\nSubject: " + subject.getName());
            subject.printInfo();
        }
    }
}
