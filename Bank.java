import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Bank {
    private List<Subject> subjects;
    private String date;

    public Bank(List<Subject> subjects, String date) {
        this.subjects = subjects != null ? subjects : new ArrayList<>();
        this.date = date;
    }
    
    public void writeDataToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Write Bank date
            writer.write("Bank: " + date);
            writer.newLine();

            // Iterate through subjects
            for (Subject subject : subjects) {
                writer.write("Subject: " + subject.getName());
                writer.newLine();

                // Iterate through quizzes
                for (Quiz quiz : subject.getQuizzes()) {
                    writer.write("  Quiz: " + quiz.getTitle());
                    writer.newLine();
                    writer.write("    TimeLimit: " + quiz.getTimeLimit());
                    writer.newLine();

                    // Iterate through questions
                    for (Question question : quiz.getQuestions()) {
                        writer.write("    Question: " + question.getContent());
                        writer.newLine();
                        writer.write("      Options: " + String.join(",", question.getOptions()));
                        writer.newLine();
                        writer.write("      CorrectAnswer: " + question.getCorrectAnswer());
                        writer.newLine();
                        writer.write("      Difficulty: " + question.getDifficulty());
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
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
