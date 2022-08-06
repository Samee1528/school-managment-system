package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.Parent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ParentsManagementFormController implements Initializable {

    public JFXTextField txtParId;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;

    private TableLoadEvent event;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void saveParentOnAction(ActionEvent actionEvent) {
        try {

            Parent parent = new Parent(
                    txtParId.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    Integer.parseInt(txtContact.getText())
            );

            boolean isSave =new ParentController().saveParent(parent);
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
    public void setEvent( TableLoadEvent event){
        this.event = event;
    }

}





