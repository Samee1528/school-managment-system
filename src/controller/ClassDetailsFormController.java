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
import views.tm.ClassTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ClassDetailsFormController extends TableLoadEvent implements Initializable {

    public TableView<ClassTm> tblClass;
    public TableColumn colClassId;
    public TableColumn colName;
    public TableColumn colTeaId;
    public TableColumn colSchId;
    public TextField txtSearch;

    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colClassId.setCellValueFactory(new PropertyValueFactory<>("classId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTeaId.setCellValueFactory(new PropertyValueFactory<>("teaId"));
        colSchId.setCellValueFactory(new PropertyValueFactory<>("schId"));

        tblClass.getColumns().setAll(colClassId, colName, colTeaId, colSchId );

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
    private void showUpdateForm(ClassTm table) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/ClassUpdate.fxml"));
            Parent parent = loader.load();
            ClassUpdateFormController controller = loader.<ClassUpdateFormController> getController();

            controller.txtClassId.setText(String.valueOf(table.getClassId()));
            controller.txtName.setText(String.valueOf(table.getName()));
            controller.txtTeaId.setText(String.valueOf(table.getTeaId()));
            controller.txtSchId.setText(String.valueOf(table.getSchId()));

            controller.setEvent(this.event);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<ClassTm> loadTableData() throws SQLException, ClassNotFoundException {

        List<Class> allClass =new ClassController().getAllClass();
        ObservableList<ClassTm> tableData = FXCollections.observableArrayList();
        for (Class classe : allClass) {
            tableData.add(new ClassTm(
                    classe.getClassId(),
                    classe.getName(),
                    classe.getTeaId(),
                    classe.getSchId(),
                    new Button("Update")
            ));

        }

        return tableData;
    }

    public void openClassManagementFormOnAction(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/ClassManagementForm.fxml"));
            Parent parent = loader.load();
            ClassManagementFormController controller = loader.<ClassManagementFormController>getController();
            controller.setEvent(this.event);
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteClassOnAction(ActionEvent actionEvent) {
        try {
            String ClassId = tblClass.getSelectionModel().getSelectedItem().getClassId();
            boolean isDeleted = ClassController.deleteClass(ClassId);
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

    public void loadData() throws SQLException, ClassNotFoundException {
        tblClass.getItems().setAll(loadTableData());
    }

    public void selectClassOnAction(ActionEvent actionEvent) { search(txtSearch.getText()); }

    private void search(String value) {
        try {
            List<Class> classes =new ClassController().searchClass(value);
            ObservableList<ClassTm> tableData = FXCollections.observableArrayList();
            for (Class classe : classes) {
                tableData.add(new ClassTm(
                        classe.getClassId(),
                        classe.getName(),
                        classe.getTeaId(),
                        classe.getSchId()
                ));
            }
            // Set Data to  table
            tblClass.getItems().setAll(tableData);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openClassUpdateFormOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/ClassUpdateForm.fxml"));
            Parent parent = loader.load();
            ClassUpdateFormController controller = loader.<ClassUpdateFormController>getController();
            controller.setEvent(this);
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
