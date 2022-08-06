package views.tm;

import javafx.scene.control.Button;

public class SubjectTm {
    private String subId;
    private String schId;
    private String name;

    public SubjectTm(String subId, String schId, String name) {
        this.subId = subId;
        this.schId = schId;
        this.name = name;
    }

    public SubjectTm(String subId, String name, String schId, Button update) {
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSchId() {
        return schId;
    }

    public void setSchId(String schId) {
        this.schId = schId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SubjectTm{" +
                "subId='" + subId + '\'' +
                ", schId='" + schId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
