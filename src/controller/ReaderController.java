package controller;

import db.DbConnection;
import model.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReaderController {

    public static boolean saveReader(Reader r) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("INSERT INTO Reader VALUES(?,?,?,?)");
        pstm.setObject(1, r.getReaId());
        pstm.setObject(2, r.getName());
        pstm.setObject(3, r.getAddress());
        pstm.setObject(4, r.getContact());
        return pstm.executeUpdate() > 0;
    }
    public static boolean updateReader(Reader r) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Reader SET  name =?,address =?,contact =? WHERE reaId =?");
        stm.setObject(1, r.getReaId());
        stm.setObject(2, r.getName());
        stm.setObject(3, r.getAddress());
        stm.setObject(4, r.getContact());

        return stm.executeUpdate() > 0;
    }
    public static boolean deleteReader(String reaId) throws SQLException, ClassNotFoundException {

        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Reader WHERE reaId='" + reaId + "'").executeUpdate() > 0) {
            return true;
        } else {

            return false;
        }
    }
    public static List<Reader> searchReader(String value) throws Exception, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Reader WHERE Name LIKE '%" + value + "%'");
        ResultSet rs = pstm.executeQuery();

        List<Reader> readers = new ArrayList<>();

        while (rs.next()) {
            readers.add(new Reader(
                    rs.getString("reaId"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getInt("Contact")
            ));
        }

        return readers;
    }
    public static List<Reader> getAllReaders() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Reader");
        ResultSet rs = pstm.executeQuery();
        List<Reader> readers = new ArrayList<>();
        while (rs.next()) {
            readers.add(new Reader(
                    rs.getString("reaId"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getInt("contact")
            ));
        }
        return readers;
    }

}
