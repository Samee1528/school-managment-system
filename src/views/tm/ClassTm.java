package views.tm;

import javafx.scene.control.Button;

public class ClassTm {
    private String classId;
    private String name;
    private String teaId;
    private String schId;

    public ClassTm(String classId, String name, String teaId, String schId) {
        this.classId = classId;
        this.name = name;
        this.teaId = teaId;
        this.schId = schId;
    }

    public ClassTm(String classId, String name, String teaId, String schId, Button update) {
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
        return "ClassTm{" +
                "classId='" + classId + '\'' +
                ", name='" + name + '\'' +
                ", teaId='" + teaId + '\'' +
                ", schId='" + schId + '\'' +
                '}';
    }
}
