package controller;

import db.DbConnection;
import model.Class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassController {

    public static boolean saveClass(Class c) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("INSERT INTO Class VALUES(?,?,?,?)");
        pstm.setObject(1, c.getClassId());
        pstm.setObject(2, c.getName());
        pstm.setObject(3, c.getTeaId());
        pstm.setObject(4, c.getSchId());

        return pstm.executeUpdate() > 0;
    }
    public static boolean updateClass(Class c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Class SET  name =?,teaId =?,schId =? WHERE classId =?");
        stm.setObject(1, c.getClassId());
        stm.setObject(2, c.getName());
        stm.setObject(3, c.getTeaId());
        stm.setObject(4, c.getSchId());

        return stm.executeUpdate() > 0;
    }
    public static boolean deleteClass(String classId) throws SQLException, ClassNotFoundException {

        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Class WHERE classId='" + classId + "'").executeUpdate() > 0) {
            return true;
        } else {

            return false;
        }
    }
    public static List<Class> searchClass(String value) throws Exception, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Class WHERE Name LIKE '%" + value + "%'");
        ResultSet rs = pstm.executeQuery();

        List<Class> classes = new ArrayList<>();

        while (rs.next()) {
            classes.add(new Class(
                    rs.getString("classId"),
                    rs.getString("name"),
                    rs.getString("teaId"),
                    rs.getString("schId")
            ));
        }

        return classes;
    }
    public Class getClass(String classId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Class WHERE classId=?");
        stm.setObject(1, classId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Class(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );

        } else {
            return null;
        }
    }
    public static List<Class> getAllClass() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Class");
        ResultSet rs = pstm.executeQuery();
        List<Class> classes = new ArrayList<>();
        while (rs.next()) {
            classes.add(new Class(
                    rs.getString("classId"),
                    rs.getString("name"),
                    rs.getString("teaId"),
                    rs.getString("schId")
            ));
        }
        return classes;
    }
}
