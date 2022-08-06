package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.Teacher;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TeacherManagementFormController implements Initializable {

    public JFXTextField txtTeaId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;


    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void saveTeacherFormOnAction(ActionEvent actionEvent) {
        try {

            Teacher teacher = new Teacher(
                    txtTeaId.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    Integer.parseInt(txtContact.getText())
            );

            boolean isSave = TeacherController.saveTeacher(teacher);
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
