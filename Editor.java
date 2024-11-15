import java.util.List;
import java.util.Scanner;

public class Editor {
    private  Scanner scanner = new Scanner(System.in);

    // Hàm để chỉnh sửa các thuộc tính của lớp Bank
    public void editBank(Bank bank) {
        System.out.println("Select attribute to edit in Bank:");
        System.out.println("1. Date");
        System.out.println("2. Subjects");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng

        switch (choice) {
            case 1:
                System.out.print("Enter new date: ");
                String newDate = scanner.nextLine();
                bank.setDate(newDate);
                break;
            case 2:
                System.out.println("Editing subjects...");
                for (int i = 0; i < bank.getSubjects().size(); i++) {
                    System.out.println((i + 1) + ". " + bank.getSubjects().get(i).getName());
                }
                System.out.print("Select subject to edit: ");
                int subjectIndex = scanner.nextInt() - 1;
                scanner.nextLine(); // Đọc ký tự xuống dòng
                editSubject(bank.getSubjects().get(subjectIndex));
                break;
            default:
                System.out.println("Invalid choice.");
        }
        
        System.out.println("Do you want to continue? (yes/no): ");
        if (!scanner.nextLine().equalsIgnoreCase("yes")) {
            return;
        }
        
        System.out.println("Editing subjects...");
        for (int i = 0; i < bank.getSubjects().size(); i++) {
            System.out.println((i + 1) + ". " + bank.getSubjects().get(i).getName());
        }
        System.out.print("Select subject to edit: ");
        int subjectIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Đọc ký tự xuống dòng
        editSubject(bank.getSubjects().get(subjectIndex));
        
    }

    // Hàm để chỉnh sửa các thuộc tính của lớp Subject
    public void editSubject(Subject subject) {
        System.out.println("Select attribute to edit in Subject:");
        System.out.println("1. Name");
        System.out.println("2. Quizzes");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng

        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                subject.setName(newName);
                break;
            case 2:
                System.out.println("Editing quizzes...");
                for (int i = 0; i < subject.getQuizzes().size(); i++) {
                    System.out.println((i + 1) + ". " + subject.getQuizzes().get(i).getTitle());
                }
                System.out.print("Select quiz to edit: ");
                int quizIndex = scanner.nextInt() - 1;
                scanner.nextLine(); // Đọc ký tự xuống dòng
                editQuiz(subject.getQuizzes().get(quizIndex));
                break;
            default:
                System.out.println("Invalid choice.");
        }
        
        System.out.println("Do you want to continue? (yes/no): ");
        if (!scanner.nextLine().equalsIgnoreCase("yes")) {
            return;
        }
        
        System.out.println("Editing quizzes...");
        for (int i = 0; i < subject.getQuizzes().size(); i++) {
            System.out.println((i + 1) + ". " + subject.getQuizzes().get(i).getTitle());
        }
        System.out.print("Select quiz to edit: ");
        int quizIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Đọc ký tự xuống dòng
        editQuiz(subject.getQuizzes().get(quizIndex));
        
    }

    // Hàm để chỉnh sửa các thuộc tính của lớp Quiz
    public void editQuiz(Quiz quiz) {
        System.out.println("Select attribute to edit in Quiz:");
        System.out.println("1. Title");
        System.out.println("2. Time Limit");
        System.out.println("3. Questions");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng

        switch (choice) {
            case 1:
                System.out.print("Enter new title: ");
                String newTitle = scanner.nextLine();
                quiz.setTitle(newTitle);
                break;
            case 2:
                System.out.print("Enter new time limit: ");
                int newTimeLimit = scanner.nextInt();
                quiz.setTimeLimit(newTimeLimit);
                break;
            case 3:
                System.out.println("Editing questions...");
                for (int i = 0; i < quiz.getQuestions().size(); i++) {
                    System.out.println((i + 1) + ". " + quiz.getQuestions().get(i).getContent());
                }
                System.out.print("Select question to edit: ");
                int questionIndex = scanner.nextInt() - 1;
                scanner.nextLine(); // Đọc ký tự xuống dòng
                editQuestion(quiz.getQuestions().get(questionIndex));
                break;
            default:
                System.out.println("Invalid choice.");
        }
        
        System.out.println("Do you want to continue? (yes/no): ");
        if (!scanner.nextLine().equalsIgnoreCase("yes")) {
            return;
        }
        System.out.println("Editing questions...");
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            System.out.println((i + 1) + ". " + quiz.getQuestions().get(i).getContent());
        }
        System.out.print("Select question to edit: ");
        int questionIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Đọc ký tự xuống dòng
        editQuestion(quiz.getQuestions().get(questionIndex));
        
    }

    // Hàm để chỉnh sửa các thuộc tính của lớp Question
    public void editQuestion(Question question) {
        System.out.println("Select attribute to edit in Question:");
        System.out.println("1. Content");
        System.out.println("2. Options");
        System.out.println("3. Correct Answer");
        System.out.println("4. Difficulty");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng

        switch (choice) {
            case 1:
                System.out.print("Enter new content: ");
                String newContent = scanner.nextLine();
                question.setContent(newContent);
                break;
            case 2:
                System.out.println("Enter new options (comma-separated): ");
                String newOptions = scanner.nextLine();
                question.setOptions(List.of(newOptions.split(",")));
                break;
            case 3:
                System.out.print("Enter new correct answer: ");
                String newCorrectAnswer = scanner.nextLine();
                question.setCorrectAnswer(newCorrectAnswer);
                break;
            case 4:
                System.out.print("Enter new difficulty: ");
                String newDifficulty = scanner.nextLine();
                question.setDifficulty(newDifficulty);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
