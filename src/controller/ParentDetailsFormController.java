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
import views.tm.ParentTm;
import views.tm.StudentTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ParentDetailsFormController extends TableLoadEvent implements Initializable {

    public TableView<ParentTm> tblParent;
    public TextField txtSearch;

    public TableColumn colParId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colUpdate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colParId.setCellValueFactory(new PropertyValueFactory<>("parId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("button"));

        tblParent.getColumns().setAll(colParId, colName, colAddress, colContact, colUpdate);

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

    private void showUpdateForm(StudentTm table) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/UpdateParent.fxml"));
            Parent parent = loader.load();
            StudentUpdateFormController controller = loader.getController();

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

    public ObservableList<ParentTm> loadTableData() throws SQLException, ClassNotFoundException {

        ArrayList<model.Parent> allParents =new ParentController().getAllParents();

        ObservableList<ParentTm> tableData = FXCollections.observableArrayList();
            for (model.Parent parent : allParents) {
                tableData.add(new ParentTm(
                        parent.getParId(),
                        parent.getName(),
                        parent.getAddress(),
                        parent.getContact(),
                        new Button("Update")
                ));

            }

          return tableData;
    }

    public void openParentManagementFormOnAction(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/ParentsManagementForm.fxml"));
            Parent parent = loader.load();
            ParentsManagementFormController controller = loader.getController();
            controller.setEvent(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void selectParentOnAction(ActionEvent actionEvent) {
        search(txtSearch.getText());
    }

    private void search(String value) {
        try {
            List<model.Parent> parents = ParentController.searchParent(value);
            ObservableList<ParentTm> tableData = FXCollections.observableArrayList();
            for (model.Parent parent : parents) {
                tableData.add(new ParentTm(
                        parent.getParId(),
                        parent.getName(),
                        parent.getAddress(),
                        parent.getContact(),
                        new Button("Update")
                ));
            }
            // Set Data to  table
            tblParent.getItems().setAll(tableData);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteParentOnAction(ActionEvent actionEvent) {
        try {
            String ParId = tblParent.getSelectionModel().getSelectedItem().getParId();
            boolean isDeleted = ParentController.deleteParent(ParId);
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
        tblParent.getItems().setAll(loadTableData());
    }

    public void openParentUpdateFormOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/ParentUpdateForm.fxml"));
            Parent parent = loader.load();
            ParentUpdateFormController controller = loader.getController();
            controller.setEvent(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

