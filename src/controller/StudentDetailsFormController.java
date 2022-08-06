package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Student;
import views.tm.StudentTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class StudentDetailsFormController extends TableLoadEvent implements Initializable {

    public TableView<StudentTm> tblStudent;
    public TableColumn colStuId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colParId;
    public TableColumn colUpdate;

    public TextField txtSearch;
    public JFXButton btnStudent;
    public JFXButton btnDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colStuId.setCellValueFactory(new PropertyValueFactory<>("stuId"));
        colParId.setCellValueFactory(new PropertyValueFactory<>("parId"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("button"));

        tblStudent.getColumns().setAll(colStuId, colName, colAddress, colContact, colParId, colUpdate);

        tblStudent.getItems().setAll(loadTableData());
        loadData();
        loadTableData();
//        showUpdateForm();


        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(newValue);
            }
        });

    }

    private void showUpdateForm(StudentTm table) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/StudentUpdateForm.fxml"));
            Parent parent = loader.load();
            StudentUpdateFormController controller = loader.<StudentUpdateFormController> getController();

            controller.txtStuId.setText(String.valueOf(table.getStuId()));
            controller.txtParId.setText(String.valueOf(table.getParId()));
            controller.txtName.setText(String.valueOf(table.getName()));
            controller.txtAddress.setText(String.valueOf(table.getAddress()));
            controller.txtContact.setText(String.valueOf(table.getContact()));

            controller.setEvent(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StudentTm loadTableData() {
        try {

            List<Student> allStudents = StudentController.getAllStudents();
            ObservableList<StudentTm> tableData = FXCollections.observableArrayList();
            for (Student student : allStudents) {
                tableData.add(new StudentTm(
                        student.getStuId(),
                        student.getParId(),
                        student.getName(),
                        student.getAddress(),
                        student.getContact(),
                        new Button("Update")
                ));

            }
            // Set Data to table
            tblStudent.getItems().setAll(tableData);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void openStudentManagementFormOnAction(ActionEvent actionEvent)  {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/StudentManagementForm.fxml"));
            Parent parent = loader.load();
            StudentManagementFormController controller = loader.<StudentManagementFormController>getController();
            controller.setEvent(this);
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void selectStudentOnAction(ActionEvent actionEvent) { search(txtSearch.getText()); }

    private void search(String value) {
        try {
            List<Student> students = StudentController.searchStudent(value);
            ObservableList<StudentTm> tableData = FXCollections.observableArrayList();
            for (Student student : students) {
                tableData.add(new StudentTm(
                        student.getStuId(),
                        student.getParId(),
                        student.getName(),
                        student.getAddress(),
                        student.getContact(),
                        new Button("Update")
                ));
            }
            // Set Data to  table
            tblStudent.getItems().setAll(tableData);


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentOnAction() {
        try {
            String StuId = tblStudent.getSelectionModel().getSelectedItem().getStuId();
            boolean isDeleted = StudentController.deleteStudent(StuId);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Success").show();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void loadData() {
        tblStudent.getItems().setAll(loadTableData());
    }

    public void openStudentUpdateFormOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/StudentUpdateForm.fxml"));
            Parent parent = loader.load();
            StudentUpdateFormController controller = loader.<StudentUpdateFormController>getController();
            controller.setEvent(this);
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





