package controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.Parent;
import model.Student;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentUpdateFormController implements Initializable {
    
    public JFXTextField txtStuId;
    public JFXTextField txtParId;
    public JFXTextField txtName;
    public JFXTextArea txtAddress;
    public JFXTextField txtContact;

    private TableLoadEvent event;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void updateStudentOnAction(ActionEvent actionEvent) {
        try {
            boolean isUpdate = StudentController.updateStudent(new Student(
                    txtStuId.getText(),
                    txtParId.getText(),
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


    public void selectStudentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String studentId = txtStuId.getText();

        Student s1= new StudentController().getStudent(studentId);
        if (s1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(s1);
        }
    }
    private void setData(Student s) {
        txtStuId.setText(s.getStuId());
        txtParId.setText(s.getParId());
        txtName.setText(s.getName());
        txtAddress.setText(s.getAddress());
        txtContact.setText(String.valueOf(s.getContact()));
    }

    public void setEvent(TableLoadEvent event) {
    }
}

