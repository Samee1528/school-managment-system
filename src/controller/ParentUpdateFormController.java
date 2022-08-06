package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.Parent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ParentUpdateFormController implements Initializable {
    public JFXTextField txtParId;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;

    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void updateParentOnAction(ActionEvent actionEvent) {
        try {
            boolean isUpdate = ParentController.updateParent(new Parent(
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


    public void setEvent(TableLoadEvent event){
        this.event = event;
    }

    public void selectParentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String parentId = txtParId.getText();

        Parent p1= new ParentController().getParent(parentId);
        if (p1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(p1);
        }

    }

    private void setData(Parent p) {
        txtParId.setText(p.getParId());
        txtName.setText(p.getName());
        txtAddress.setText(p.getAddress());
        txtContact.setText(String.valueOf(p.getContact()));
    }
}


