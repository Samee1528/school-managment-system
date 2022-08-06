package controller;

import db.DbConnection;
import model.Parent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParentController {


    public  boolean saveParent(Parent p) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Parent VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,p.getParId());
        stm.setObject(2,p.getName());
        stm.setObject(3,p.getAddress());
        stm.setObject(4,p.getContact());
        return stm.executeUpdate()>0;
    }

    public static boolean updateParent(Parent p) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Parent SET name =?,address =?,contact =? WHERE parId =?");
        stm.setObject(1,p.getParId());
        stm.setObject(2,p.getName());
        stm.setObject(3,p.getAddress());
        stm.setObject(4,p.getContact());

        return stm.executeUpdate()>0;
    }

    public static boolean deleteParent(String parId) throws SQLException, ClassNotFoundException {

        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Parent WHERE parId='"+parId+"'").executeUpdate()>0){
            return true;
        }else{

            return false;
        }
    }

    public static List<Parent> searchParent(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement stm = con.prepareStatement("SELECT * FROM Parent WHERE Name LIKE '%" + value + "%'");
        ResultSet rs = stm.executeQuery();

        List<Parent> parents = new ArrayList<>();

        while (rs.next()) {
            parents.add(new Parent(
                    rs.getString("parId"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getInt("Contact")
            ));
        }

        return parents;
    }

    public Parent getParent(String parId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Parent WHERE parId=?");
        stm.setObject(1, parId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Parent(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    Integer.valueOf(rst.getString(4))
            );

        } else {
            return null;
        }
    }

    public static ArrayList<Parent> getAllParents() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Parent");
        ResultSet rst = stm.executeQuery();
        ArrayList<Parent> parents = new ArrayList<>();
        while (rst.next()) {
            parents.add(new Parent(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    Integer.valueOf( rst.getString(4))
            ));
        }
        return parents;
    }

}
