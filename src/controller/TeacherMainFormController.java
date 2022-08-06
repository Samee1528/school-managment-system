package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class TeacherMainFormController implements Initializable {

    public StackPane panlRoot;
    public Label lblDate;
    public Label lblTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDateAndTime();

    }
    private void loadDateAndTime() {
        // Date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        // Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void studentAttendanceOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../views/StudentAttendanceDetailsForm.fxml"));
            pane = fxmlLoader.load();
            panlRoot.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void classOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../views/ClassDetailsForm.fxml"));
            pane = fxmlLoader.load();
            panlRoot.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void subjectOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../views/SubjectDetailsForm.fxml"));
            pane = fxmlLoader.load();
            panlRoot.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void timeTableOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../views/TimeTableDetailsForm.fxml"));
            pane = fxmlLoader.load();
            panlRoot.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void examOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../views/ExamDetailsForm.fxml"));
            pane = fxmlLoader.load();
            panlRoot.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resultOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane pane;
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../views/ResultDetailsForm.fxml"));
            pane = fxmlLoader.load();
            panlRoot.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
