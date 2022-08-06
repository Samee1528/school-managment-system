package model;

public class ExamType {
    private String examTypeId;
    private String name;

    public ExamType(String examTypeId, String name) {
        this.examTypeId = examTypeId;
        this.name = name;
    }

    public ExamType() {
    }

    public String getExamTypeId() {
        return examTypeId;
    }

    public void setExamTypeId(String examTypeId) {
        this.examTypeId = examTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExamType{" +
                "examTypeId='" + examTypeId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
