
//Interface này sẽ định nghĩa một số hành vi chung cho các loại bài thi khác nhau (quiz, exam, v.v.).
public interface Testable {
    void startTest();   
    void endTest();     
    void calculateScore();  // cần thiết kế lại hàm này ở các class
}
