package model;

public class Class {
    private String classId;
    private String name;
    private String teaId;
    private String schId;

    public Class(String classId, String name, String teaId, String schId) {
        this.classId = classId;
        this.name = name;
        this.teaId = teaId;
        this.schId = schId;
    }

    public Class() {
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeaId() {
        return teaId;
    }

    public void setTeaId(String teaId) {
        this.teaId = teaId;
    }

    public String getSchId() {
        return schId;
    }

    public void setSchId(String schId) {
        this.schId = schId;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classId='" + classId + '\'' +
                ", name='" + name + '\'' +
                ", teaId='" + teaId + '\'' +
                ", schId='" + schId + '\'' +
                '}';
    }
}
