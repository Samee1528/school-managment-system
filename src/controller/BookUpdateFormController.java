package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.Book;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookUpdateFormController implements Initializable {

    public JFXTextField txtBookId;
    public JFXTextField txtName;
    public JFXTextField txtDate;
    public JFXTextField txtCategory;
    public JFXTextField txtReaId;

    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void updateBookOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Book b1= new Book(
                txtBookId.getText(),
                txtReaId.getText(),
                txtName.getText(),
                txtCategory.getText(),
                Date.valueOf(txtDate.getText())
        );


        if (new BookController().updateBook(b1))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();

    }
    public void setEvent(TableLoadEvent event){
        this.event = event;
    }

    public void selectBookOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String bookId = txtBookId.getText();

        Book b1= new BookController().getBook(bookId);
        if (b1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(b1);
        }

    }

     void setData(Book b) {
         txtBookId.setText(b.getBookId());
         txtReaId.setText(b.getReaId());
         txtName.setText(b.getName());
         txtCategory.setText(b.getCategory());
         txtDate.setText(String.valueOf(b.getDate()));
    }

}
