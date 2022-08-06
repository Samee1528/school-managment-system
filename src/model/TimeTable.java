package model;

public class TimeTable {
    private String tableId;
    private String subId;
    private String classId;

    public TimeTable(String tableId, String subId, String classId) {
        this.tableId = tableId;
        this.subId = subId;
        this.classId = classId;
    }

    public TimeTable() {
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "TimeTable{" +
                "tableId='" + tableId + '\'' +
                ", subId='" + subId + '\'' +
                ", classId='" + classId + '\'' +
                '}';
    }
}
