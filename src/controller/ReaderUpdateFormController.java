package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.Reader;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReaderUpdateFormController implements Initializable {


    public JFXTextField txtReaId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;

    private TableLoadEvent event;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void updateReaderOnAction(ActionEvent actionEvent) {
        try {
            boolean isUpdate = ReaderController.updateReader(new Reader(
                    txtReaId.getText(),
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
    public void setEvent(ReaderDetailsFormController event){ this.event = event; }

}
