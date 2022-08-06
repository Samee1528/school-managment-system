package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import model.Parent;
import model.Subject;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SubjectUpdateFormController implements Initializable {

    public JFXTextField txtSubId;
    public JFXTextField txtSchId;
    public JFXTextField txtName;

    private TableLoadEvent event;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void updateSubjectOnAction(ActionEvent actionEvent) {
        try {
            boolean isUpdate = SubjectController.updateSubject(new Subject(
                    txtSubId.getText(),
                    txtName.getText(),
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

    public void setEvent(TableLoadEvent event) {this.event = event;}

    public void selectSubjectOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String subjectId = txtSubId.getText();

        Subject s1= new SubjectController().getSubject(subjectId);
        if (s1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(s1);
        }
    }

    private void setData(Subject s) {
        txtSubId.setText(s.getSubId());
        txtName.setText(s.getName());
        txtSchId.setText(String.valueOf(s.getSchId()));
    }
}
