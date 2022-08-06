package model;

public class StudentAttendance {
    private String stuId;
    private String date;
    private String reMark;

    public StudentAttendance(String stuId, String date, String reMark) {
        this.stuId = stuId;
        this.date = date;
        this.reMark = reMark;
    }

    public StudentAttendance() {
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReMark() {
        return reMark;
    }

    public void setReMark(String reMark) {
        this.reMark = reMark;
    }

    @Override
    public String toString() {
        return "StudentAttendance{" +
                "stuId='" + stuId + '\'' +
                ", date='" + date + '\'' +
                ", reMark='" + reMark + '\'' +
                '}';
    }
}
