package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.Subject;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SubjectManagementFormController implements Initializable {
    
    public JFXTextField txtSubId;
    public JFXTextField txtSchId;
    public JFXTextField txtName;

    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void saveSubjectOnAction(ActionEvent actionEvent) {
        try {

            Subject subject = new Subject(
                    txtSubId.getText(),
                    txtName.getText(),
                    String.valueOf(txtSchId.getText())
            );

            boolean isSave = SubjectController.saveSubject(subject);
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

    public void setEvent(TableLoadEvent event) { this.event = event;}
}
