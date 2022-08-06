package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.Reader;
import model.Student;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReaderManagementFormController implements Initializable {


    public JFXTextField txtReaId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;

    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void saveReaderOnAction(ActionEvent actionEvent) {
        try {

            Reader reader = new Reader(
                    txtReaId.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    Integer.parseInt(txtContact.getText())
            );

            boolean isSave = ReaderController.saveReader(reader);
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
    public void setEvent(StudentDetailsFormController event){
        this.event = event;
    }

}
