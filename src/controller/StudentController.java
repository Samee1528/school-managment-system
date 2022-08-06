package controller;

import db.DbConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentController {


    public static boolean saveStudent(Student s) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("INSERT INTO Student VALUES(?,?,?,?,?)");
        pstm.setObject(1, s.getStuId());
        pstm.setObject(2, s.getParId());
        pstm.setObject(3, s.getName());
        pstm.setObject(4, s.getAddress());
        pstm.setObject(5, s.getContact());
        return pstm.executeUpdate() > 0;
    }

    public static boolean updateStudent(Student s) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Student SET parId =?, name =?,address =?,contact =? WHERE stuId =?");
        stm.setObject(1, s.getStuId());
        stm.setObject(2, s.getParId());
        stm.setObject(3, s.getName());
        stm.setObject(4, s.getAddress());
        stm.setObject(5, s.getContact());

        return stm.executeUpdate() > 0;
    }

    public static boolean deleteStudent(String stuId) throws SQLException, ClassNotFoundException {

        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Student WHERE stuId='" + stuId + "'").executeUpdate() > 0) {
            return true;
        } else {

            return false;
        }
    }

    public static List<Student> searchStudent(String value) throws Exception, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Student WHERE Name LIKE '%" + value + "%'");
        ResultSet rs = pstm.executeQuery();

        List<Student> students = new ArrayList<>();

        while (rs.next()) {
            students.add(new Student(
                    rs.getString("stuId"),
                    rs.getString("parId"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getInt("Contact")
            ));
        }

        return students;
    }
    public Student getStudent(String stuId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Student WHERE stuId=?");
        stm.setObject(1, stuId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Student(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5)

            );

        } else {
            return null;
        }
    }

    public static List<Student> getAllStudents() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Student");
        ResultSet rs = pstm.executeQuery();
        List<Student> students = new ArrayList<>();
        while (rs.next()) {
            students.add(new Student(
                    rs.getString("stuId"),
                    rs.getString("parId"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getInt("contact")
            ));
        }
        return students;
    }

}
