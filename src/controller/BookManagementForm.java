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

public class BookManagementForm implements Initializable {
    
    public JFXTextField txtBookId;
    public JFXTextField txtReaId;
    public JFXTextField txtName;
    public JFXTextField txtCategory;
    public JFXTextField txtDate;

    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void saveBookOnAction(ActionEvent actionEvent) {
        try {

            Book book = new Book(
                    txtBookId.getText(),
                    txtReaId.getText(),
                    txtName.getText(),
                    txtCategory.getText(),
                    Date.valueOf(txtDate.getText())
            );

            boolean isSave =new BookController().saveBook(book);
            if (isSave) {
                new Alert(Alert.AlertType.INFORMATION, "Success").show();
                event.loadData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    public void setEvent(TableLoadEvent event){
        this.event = event;
    }

}
