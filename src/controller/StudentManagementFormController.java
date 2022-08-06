package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.Student;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentManagementFormController implements Initializable {

    public JFXTextField txtStuId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtParId;

    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void saveStudentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        try {

            Student student = new Student(
                    txtStuId.getText(),
                    txtParId.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    Integer.parseInt(txtContact.getText())
            );

            boolean isSave = StudentController.saveStudent(student);
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


