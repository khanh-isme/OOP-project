

//chấm điểm dựa trên tỷ lệ phần trăm số câu trả lời đúng.
//cần phát triển thêm
public class PercentageGradingStrategy extends GradingStrategy {
    @Override
    public int calculateScore(int correctAnswers, int totalQuestions) {
        return (correctAnswers * 100) / totalQuestions;
    }
}
