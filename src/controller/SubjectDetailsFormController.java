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
import model.Subject;
import model.Teacher;
import views.tm.SubjectTm;
import views.tm.TeacherTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SubjectDetailsFormController extends TableLoadEvent implements Initializable {

    public TableView<SubjectTm> tblSubject;
    public TextField txtSearch;

    public TableColumn colSubId;
    public TableColumn colName;
    public TableColumn colSchId;
    public TableColumn colUpdate;

    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colSubId.setCellValueFactory(new PropertyValueFactory<>("subId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSchId.setCellValueFactory(new PropertyValueFactory<>("schId"));

        tblSubject.getColumns().setAll(colSubId, colName, colSchId);

        try {
            loadData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(newValue);
            }
        });

    }
    private void showUpdateForm(SubjectTm table) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/SubjectUpdateForm.fxml"));
            Parent parent = loader.load();
            SubjectUpdateFormController controller = loader.<SubjectUpdateFormController> getController();

            controller.txtSubId.setText(String.valueOf(table.getSubId()));
            controller.txtName.setText(String.valueOf(table.getName()));
            controller.txtSchId.setText(String.valueOf(table.getSchId()));

            controller.setEvent(this.event);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<SubjectTm> loadTableData() throws SQLException, ClassNotFoundException {

        List<Subject> allSubject =new SubjectController().getAllSubjects();
        ObservableList<SubjectTm> tableData = FXCollections.observableArrayList();
        for (Subject subject : allSubject) {
            tableData.add(new SubjectTm(
                    subject.getSubId(),
                    subject.getName(),
                    subject.getSchId(),
                    new Button("Update")
            ));

        }

        return tableData;
    }

    public void openSubjectManagementFormOnAction(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/SubjectManagementForm.fxml"));
            Parent parent = loader.load();
            SubjectManagementFormController controller = loader.<SubjectManagementFormController>getController();
            controller.setEvent(this);
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() throws SQLException, ClassNotFoundException {
        tblSubject.getItems().setAll(loadTableData());
    }

    public void deleteSubjectOnAction(ActionEvent actionEvent) {
        try {
            String SubId = tblSubject.getSelectionModel().getSelectedItem().getSubId();
            boolean isDeleted = SubjectController.deleteSubject(SubId);
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


    public void selectSubjectOnAction(ActionEvent actionEvent) {search(txtSearch.getText());}

    private void search(String value) {
        try {
            List<Subject> subjects =new SubjectController().searchSubject(value);
            ObservableList<SubjectTm> tableData = FXCollections.observableArrayList();
            for (Subject subject : subjects) {
                tableData.add(new SubjectTm(
                        subject.getSubId(),
                        subject.getName(),
                        subject.getSubId(),
                        new Button("Update")
                ));
            }
            // Set Data to  table
            tblSubject.getItems().setAll(tableData);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openSubjectUpdateFormOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/SubjectUpdateForm.fxml"));
            Parent parent = loader.load();
            SubjectUpdateFormController controller = loader.<SubjectUpdateFormController>getController();
            controller.setEvent(this.event);
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
