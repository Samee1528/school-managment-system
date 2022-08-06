package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TimeTableDetailsFormController implements Initializable {

    public TableView tblTimeTable;
    public TextField txtSearch;
    public TableColumn colSubId;
    public TableColumn colSchId;
    public TableColumn colName;
    public TableColumn colUpdate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void openTimeTableManagementFormOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../views/TimeTableManagementForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    public void deleteTimeTableOnAction(ActionEvent actionEvent) {
    }


    public void selectTimeTableOnAction(ActionEvent actionEvent) {
    }
}
