

//class trừu tượng này để chấm điểm
public abstract class GradingStrategy {
    // Abstract method to calculate score
    public abstract int calculateScore(int correctAnswers, int totalQuestions);

    // Method to provide feedback on the score
    public String provideFeedback(int score, int totalQuestions) {
        double percentage = (double) score / totalQuestions * 100;
        if (percentage >= 90) {
            return "Excellent!";
        } else if (percentage >= 75) {
            return "Good job!";
        } else if (percentage >= 50) {
            return "Pass!";
        } else {
            return "Fail!";
        }
    }
}

