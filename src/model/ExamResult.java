package model;

public class ExamResult {
    private String examId;
    private String stuId;
    private String marks;

    public ExamResult(String examId, String stuId, String marks) {
        this.examId = examId;
        this.stuId = stuId;
        this.marks = marks;
    }

    public ExamResult() {
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "ExamResult{" +
                "examId='" + examId + '\'' +
                ", stuId='" + stuId + '\'' +
                ", marks='" + marks + '\'' +
                '}';
    }
}
