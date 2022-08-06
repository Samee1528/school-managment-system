package model;

public class TeacherAttendance {
    private String teaId;
    private String date;
    private String reMark;

    public TeacherAttendance(String teaId, String date, String reMark) {
        this.teaId = teaId;
        this.date = date;
        this.reMark = reMark;
    }

    public TeacherAttendance() {
    }

    public String getTeaId() {
        return teaId;
    }

    public void setTeaId(String teaId) {
        this.teaId = teaId;
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
        return "TeacherAttendance{" +
                "teaId='" + teaId + '\'' +
                ", date='" + date + '\'' +
                ", reMark='" + reMark + '\'' +
                '}';
    }
}
