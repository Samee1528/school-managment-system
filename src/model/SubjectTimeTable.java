package model;

public class SubjectTimeTable {
    private String subId;
    private String tableId;

    public SubjectTimeTable(String subId, String tableId) {
        this.subId = subId;
        this.tableId = tableId;
    }

    public SubjectTimeTable() {
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        return "SubjectTimeTable{" +
                "subId='" + subId + '\'' +
                ", tableId='" + tableId + '\'' +
                '}';
    }
}
