package controller;

import db.DbConnection;
import model.Book;
import model.Parent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookController  {

    private TableLoadEvent event;

    public  boolean saveBook(Book b) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("INSERT INTO Book VALUES(?,?,?,?,?)");
        pstm.setObject(1, b.getBookId());
        pstm.setObject(2, b.getReaId());
        pstm.setObject(3, b.getName());
        pstm.setObject(4, b.getCategory());
        pstm.setObject(5, b.getDate());
        return pstm.executeUpdate() > 0;
    }
    public static boolean updateBook(Book b) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Book SET reaId =?, name =?,category =?,date =? WHERE bookId =?");
        stm.setObject(1, b.getBookId());
        stm.setObject(2, b.getReaId());
        stm.setObject(3, b.getName());
        stm.setObject(4, b.getCategory());
        stm.setObject(5, b.getDate());

        return stm.executeUpdate() > 0;
    }
    public static boolean deleteBook(String bookId) throws SQLException, ClassNotFoundException {

        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Book WHERE bookId='" + bookId + "'").executeUpdate() > 0) {
            return true;
        } else {

            return false;
        }
    }
    public static List<Book> searchBook(String value) throws Exception, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Book WHERE Name LIKE '%" + value + "%'");
        ResultSet rs = pstm.executeQuery();

        List<Book> books = new ArrayList<>();

        while (rs.next()) {
            books.add(new Book(
                    rs.getString("bookId"),
                    rs.getString("reaId"),
                    rs.getString("Name"),
                    rs.getString("category"),
                    rs.getDate("date")
            ));
        }

        return books;
    }
    public Book getBook(String bookId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Book WHERE bookId=?");
        stm.setObject(1, bookId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Book(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(3),
                    Date.valueOf((rst.getString(4))
            ));

        } else {
            return null;
        }
    }
    public static List<Book> getAllBooks() throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Book");
        ResultSet rs = pstm.executeQuery();
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            books.add(new Book(
                    rs.getString("bookId"),
                    rs.getString("reaId"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDate("issueDate")
            ));
        }
        return books;
    }


    public void setEvent(TableLoadEvent event) {
        this.event = event;
    }

    public TableLoadEvent getEvent() {
        return event;
    }
}
