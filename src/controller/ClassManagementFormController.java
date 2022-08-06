package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.Class;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClassManagementFormController implements Initializable {

    public JFXTextField txtClassId;
    public JFXTextField txtName;
    public JFXTextField txtTeaId;
    public JFXTextField txtSchId;

    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void saveClassOnAction(ActionEvent actionEvent) {
        try {

            Class classe = new Class(
                    txtClassId.getText(),
                    txtName.getText(),
                    txtTeaId.getText(),
                    String.valueOf(txtSchId.getText())
            );

            boolean isSave =new ClassController().saveClass(classe);
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
