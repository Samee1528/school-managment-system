package model;

public class ClassTeacher {
    private String classId;
    private String teaId;

    public ClassTeacher(String classId, String teaId) {
        this.classId = classId;
        this.teaId = teaId;
    }

    public ClassTeacher() {
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTeaId() {
        return teaId;
    }

    public void setTeaId(String teaId) {
        this.teaId = teaId;
    }

    @Override
    public String toString() {
        return "ClassTeacher{" +
                "classId='" + classId + '\'' +
                ", teaId='" + teaId + '\'' +
                '}';
    }
}
