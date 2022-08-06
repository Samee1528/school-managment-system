package controller;

import db.DbConnection;
import model.Reader;
import model.Student;
import model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectController {

    public static boolean saveSubject(Subject s) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("INSERT INTO Subject VALUES(?,?,?)");
        pstm.setObject(1, s.getSubId());
        pstm.setObject(2, s.getSchId());
        pstm.setObject(3, s.getName());
        return pstm.executeUpdate() > 0;
    }
    public static boolean updateSubject(Subject s) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Subject SET schId =?,name =? WHERE subId =?");
        stm.setObject(1, s.getSubId());
        stm.setObject(2, s.getSchId());
        stm.setObject(3, s.getName());

        return stm.executeUpdate() > 0;
    }
    public static boolean deleteSubject(String subId) throws SQLException, ClassNotFoundException {

        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Subject WHERE subId='" + subId + "'").executeUpdate() > 0) {
            return true;
        } else {

            return false;
        }
    }
    public static List<Subject> searchSubject(String value) throws Exception, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Subject WHERE Name LIKE '%" + value + "%'");
        ResultSet rs = pstm.executeQuery();

        List<Subject> subjects = new ArrayList<>();

        while (rs.next()) {
            subjects.add(new Subject(
                    rs.getString("subId"),
                    rs.getString("schId"),
                    rs.getString("name")
            ));
        }

        return subjects;
    }

    public Subject getSubject(String subId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Subject WHERE subId=?");
        stm.setObject(1, subId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Subject(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );

        } else {
            return null;
        }
    }
    public static List<Subject> getAllSubjects() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Subject");
        ResultSet rs = pstm.executeQuery();
        List<Subject> subjects = new ArrayList<>();
        while (rs.next()) {
            subjects.add(new Subject(
                    rs.getString("subId"),
                    rs.getString("schId"),
                    rs.getString("name")
            ));
        }
        return subjects;
    }
}
