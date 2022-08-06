package model;

import java.util.ArrayList;

public class Subject {
    private String subId;
    private String schId;
    private String name;

    public Subject(String subId, String schId, String name) {
        this.subId = subId;
        this.schId = schId;
        this.name = name;
    }

    public Subject() {
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
        return "Subject{" +
                "subId='" + subId + '\'' +
                ", schId='" + schId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getBlocks() {
        return 0;
    }

}
