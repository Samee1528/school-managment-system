package model;

public class Exam {
    private String examId;
    private String examType;
    private String name;
    private String startDate;

    public Exam(String examId, String examType, String name, String startDate) {
        this.examId = examId;
        this.examType = examType;
        this.name = name;
        this.startDate = startDate;
    }

    public Exam() {
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examId='" + examId + '\'' +
                ", examType='" + examType + '\'' +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                '}';
    }
}
