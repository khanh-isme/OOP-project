import java.util.ArrayList;
import java.util.List;


public class Subject {
    private String name;
    private List<Quiz> quizzes;

    public Subject(String name, List<Quiz> quizzes) {
        this.name = name;
        this.quizzes = quizzes != null ? quizzes : new ArrayList<>();
    }

    // Getter cho name
    public String getName() {
        return name;
    }

    // Setter cho name
    public void setName(String name) {
        this.name = name;
    }

    // Getter cho quizzes
    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    // Setter cho quizzes
    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    // Phương thức thêm Quiz vào danh sách
    public void addQuiz(Quiz quiz) {
        if (quiz != null) {
            this.quizzes.add(quiz);
        }
    }

    // Phương thức xoá Quiz khỏi danh sách
    public void removeQuiz(Quiz quiz) {
        this.quizzes.remove(quiz);
    }
    

    // Phương thức lấy bài thi theo tiêu đề
    public Quiz getQuizByTitle(String title) {
        for (Quiz quiz : quizzes) {
            if (quiz.getTitle().equalsIgnoreCase(title)) {
                return quiz;
            }
        }
        return null; // Trả về null nếu không tìm thấy bài thi
    }

    // Override phương thức toString() để dễ dàng in thông tin của Subject
    @Override
    public String toString() {
        return "Subject{name='" + name + "', quizzes=" + quizzes + "}";
    }
    
    public void printInfo() {
        for (Quiz quiz : quizzes) {
            System.out.println("\n  Quiz Title: " + quiz.getTitle());
            System.out.println("  Time Limit: " + quiz.getTimeLimit() + " minutes");
            quiz.printInfo();
        }
    }
}
