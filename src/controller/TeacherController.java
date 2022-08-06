package controller;

import db.DbConnection;
import model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherController  {

    public static boolean saveTeacher(Teacher t) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Teacher VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,t.getTeaId());
        stm.setObject(2,t.getName());
        stm.setObject(3,t.getAddress());
        stm.setObject(4,t.getContact());
        return stm.executeUpdate()>0;
    }

    public static boolean updateTeacher(Teacher t) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Teacher SET  name =?,address =?,contact =? WHERE TeaId =?");
        stm.setObject(1,t.getTeaId());
        stm.setObject(2,t.getName());
        stm.setObject(3,t.getAddress());
        stm.setObject(4,t.getContact());

        return stm.executeUpdate()>0;
    }

    public static boolean deleteTeacher(String teaId) throws SQLException, ClassNotFoundException {

        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Teacher WHERE teaId='"+teaId+"'").executeUpdate()>0){
            return true;
        }else{

            return false;
        }
    }

    public static List<Teacher> searchTeacher(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Teacher WHERE Name LIKE '%" + value + "%'");
        ResultSet rs = pstm.executeQuery();

        List<Teacher> teachers = new ArrayList<>();

        while (rs.next()) {
            teachers.add(new Teacher(
                    rs.getString("TeaId"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getInt("Contact")
            ));
        }

        return teachers;
    }

    public Teacher getTeacher(String teaId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Teacher WHERE teaId=?");
        stm.setObject(1, teaId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Teacher(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            );

        } else {
            return null;
        }
    }

    public static List<Teacher> getAllTeachers() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Teacher");
        ResultSet rs = pstm.executeQuery();
        List<Teacher> teachers = new ArrayList<>();
        while (rs.next()) {
            teachers.add(new Teacher(
                    rs.getString("teaId"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getInt("contact")
            ));
        }
        return teachers;
    }

}
