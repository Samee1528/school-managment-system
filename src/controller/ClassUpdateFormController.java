package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.Class;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClassUpdateFormController implements Initializable {

    public JFXTextField txtClassId;
    public JFXTextField txtName;
    public JFXTextField txtTeaId;
    public JFXTextField txtSchId;

    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void updateClassOnAction(ActionEvent actionEvent) {
        try {
            boolean isUpdate = ClassController.updateClass(new Class(
                    txtClassId.getText(),
                    txtName.getText(),
                    txtTeaId.getText(),
                    String.valueOf(txtSchId.getText())
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
    public void selectClassOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String classId = txtClassId.getText();

        Class c1= new ClassController().getClass(classId);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }
    }

    private void setData(Class c) {
        txtClassId.setText(c.getClassId());
        txtName.setText(c.getName());
        txtTeaId.setText(c.getTeaId());
        txtSchId.setText(String.valueOf(c.getSchId()));
    }

    public void setEvent(TableLoadEvent event) {this.event = event; }

}

