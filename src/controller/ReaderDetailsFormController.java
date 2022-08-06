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
import model.Reader;
import views.tm.ReaderTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ReaderDetailsFormController extends TableLoadEvent implements Initializable {

    public TableView<ReaderTm> tblReader;
    public TableColumn colReaId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;

    public TableColumn colUpdate;
    public TextField txtSearch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colReaId.setCellValueFactory(new PropertyValueFactory<>("reaId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("button"));

        tblReader.getColumns().setAll(colReaId, colName, colAddress, colContact, colUpdate);

        tblReader.getItems().setAll(loadTableData());
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


    private void showUpdateForm(ReaderTm table) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/ReaderUpdateForm.fxml"));
            Parent parent = loader.load();
            ReaderUpdateFormController controller = loader.getController();

            controller.txtReaId.setText(String.valueOf(table.getReaId()));
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

    public ReaderTm loadTableData() {
        try {

            List<Reader> allReaders = ReaderController.getAllReaders();
            ObservableList<ReaderTm> tableData = FXCollections.observableArrayList();
            for (Reader reader : allReaders) {
                tableData.add(new ReaderTm(
                        reader.getReaId(),
                        reader.getName(),
                        reader.getAddress(),
                        reader.getContact(),
                        new Button("Update")
                ));

            }
            // Set Data to table
            tblReader.getItems().setAll(tableData);

            // Set OnAction to table button
//            for (StudentTm item : tblStudent.getItems()) {
//                item.getButton().setOnAction(new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        showUpdateForm(item);
//                    }
//
//
//                });
//            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void openReaderManagementFormOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../views/ReaderManagementForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public void deleteReaderOnAction(ActionEvent actionEvent) {
        try {
            String ReaId = tblReader.getSelectionModel().getSelectedItem().getReaId();
            boolean isDeleted = ReaderController.deleteReader(ReaId);
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
        tblReader.getItems().setAll(loadTableData());
    }

    public void selectParentOnAction(ActionEvent actionEvent) {
        search(txtSearch.getText());
    }

    private void search(String value) {
        try {
            List<Reader> readers = ReaderController.searchReader(value);
            ObservableList<ReaderTm> tableData = FXCollections.observableArrayList();
            for (Reader reader : readers) {
                tableData.add(new ReaderTm(
                        reader.getReaId(),
                        reader.getName(),
                        reader.getAddress(),
                        reader.getContact(),
                        new Button("Update")
                ));
            }
            // Set Data to  table
            tblReader.getItems().setAll(tableData);

            // Set OnAction to table button
//            for (StudentTm item : tblStudent.getItems()) {
//                item.getButton().setOnAction(new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        showUpdateForm(item);
//                    }
//                });
//            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
