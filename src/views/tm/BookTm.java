package views.tm;

import javafx.scene.control.Button;

import java.sql.Date;

public class BookTm {
    private String bookId;
    private String reaId;
    private String name;
    private String category;
    private Date date;

    public BookTm(String bookId, String reaId, String name, String category, Date date, Button update) {
        this.bookId = bookId;
        this.reaId = reaId;
        this.name = name;
        this.category = category;
        this.date = date;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getReaId() {
        return reaId;
    }

    public void setReaId(String reaId) {
        this.reaId = reaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BookTm{" +
                "bookId='" + bookId + '\'' +
                ", reaId='" + reaId + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
