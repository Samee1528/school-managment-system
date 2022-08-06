package controller;

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
import model.Class;
import model.Teacher;
import views.tm.TeacherTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TeacherDetailsFormController implements Initializable {
    public TextField txtSearch;
    public TableView<TeacherTm> tblTeacher;

    public TableColumn colTeaId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colUpdate;

    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colTeaId.setCellValueFactory(new PropertyValueFactory<>("teaId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("button"));

        tblTeacher.getColumns().setAll(colTeaId, colName, colAddress, colContact, colUpdate);


        try {
            loadData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        showUpdateForm();

        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(newValue);
            }
        });

    }

    private void showUpdateForm(TeacherTm table) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/TeacherUpdateForm.fxml"));
            Parent parent = loader.load();
            TeacherUpdateFormController controller = loader.<TeacherUpdateFormController> getController();

            controller.txtTeaId.setText(String.valueOf(table.getTeaId()));
            controller.txtName.setText(String.valueOf(table.getName()));
            controller.txtAddress.setText(String.valueOf(table.getAddress()));
            controller.txtContact.setText(String.valueOf(table.getContact()));

            controller.setEvent(this.event);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<TeacherTm> loadTableData() throws SQLException, ClassNotFoundException {

            List<Teacher> allTeacher =new TeacherController().getAllTeachers();
            ObservableList<TeacherTm> tableData = FXCollections.observableArrayList();
            for (Teacher teacher : allTeacher) {
                tableData.add(new TeacherTm(
                        teacher.getTeaId(),
                        teacher.getName(),
                        teacher.getAddress(),
                        teacher.getContact(),
                        new Button("Update")
                ));

            }

        return tableData;
    }

    public void openTeacherManagementFormOnAction(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/TeacherManagementForm.fxml"));
            Parent parent = loader.load();
            TeacherManagementFormController controller = loader.<TeacherManagementFormController>getController();
            controller.setEvent(this.event);
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() throws SQLException, ClassNotFoundException {
            tblTeacher.getItems().setAll(loadTableData());
    }

    public void deleteTeacherOnAction(ActionEvent actionEvent) {
        try {
            String TeaId = tblTeacher.getSelectionModel().getSelectedItem().getTeaId();
            boolean isDeleted = TeacherController.deleteTeacher(TeaId);
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

    public void selectTeacherOnAction(ActionEvent actionEvent) { search(txtSearch.getText());}

    private void search(String value) {
        try {
            List<Teacher> teachers = TeacherController.searchTeacher(value);
            ObservableList<TeacherTm> tableData = FXCollections.observableArrayList();
            for (Teacher teacher : teachers) {
                tableData.add(new TeacherTm(
                        teacher.getTeaId(),
                        teacher.getName(),
                        teacher.getAddress(),
                        teacher.getContact(),
                        new Button("Update")
                ));
            }
            // Set Data to  table
            tblTeacher.getItems().setAll(tableData);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void openTeacherUpdateFormOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/TeacherUpdateForm.fxml"));
            Parent parent = loader.load();
            TeacherUpdateFormController controller = loader.<TeacherUpdateFormController>getController();
            controller.setEvent(this.event);
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
