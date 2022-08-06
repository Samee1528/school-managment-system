package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.Student;
import model.Teacher;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TeacherUpdateFormController implements Initializable {

    public JFXTextField txtTeaId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;

    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setEvent(TableLoadEvent event){
        this.event = event;
    }

    public void updateTeacherManagementFormOnAction(ActionEvent actionEvent) {
        try {
            boolean isUpdate = TeacherController.updateTeacher(new Teacher(
                    txtTeaId.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    Integer.parseInt(txtContact.getText())
            ));

            if(isUpdate){
                new Alert(Alert.AlertType.INFORMATION,"Success").show();

            }else {
                new Alert(Alert.AlertType.ERROR,"Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    public void selectTeacherOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String teacherId = txtTeaId.getText();

        Teacher t1= new TeacherController().getTeacher(teacherId);
        if (t1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(t1);
        }
    }
    private void setData(Teacher t) {
        txtTeaId.setText(t.getTeaId());
        txtName.setText(t.getName());
        txtAddress.setText(t.getAddress());
        txtContact.setText(String.valueOf(t.getContact()));
    }
}
