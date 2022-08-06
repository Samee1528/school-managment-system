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
import model.Book;
import views.tm.BookTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class BookDetailsFormController extends TableLoadEvent implements Initializable {

    public TableView<BookTm> tblBook;
    public TableColumn colBookId;
    public TableColumn colReaId;
    public TableColumn colName;
    public TableColumn colCategory;
    public TableColumn colDate;

    public TableColumn colUpdate;
    public TextField txtSearch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colReaId.setCellValueFactory(new PropertyValueFactory<>("reaId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("button"));

        tblBook.getColumns().setAll(colBookId, colReaId, colName, colCategory, colDate, colUpdate);

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
    private void showUpdateForm(BookTm table) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/UpdateBook.fxml"));
            Parent parent = loader.load();
            BookUpdateFormController controller = loader.<BookUpdateFormController> getController();

            controller.txtBookId.setText(String.valueOf(table.getBookId()));
            controller.txtReaId.setText(String.valueOf(table.getReaId()));
            controller.txtName.setText(String.valueOf(table.getName()));
            controller.txtCategory.setText(String.valueOf(table.getCategory()));
            controller.txtDate.setText(String.valueOf(table.getDate()));

            controller.setEvent(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<BookTm> loadTableData() {
        try {

            List<Book> allBook =new BookController().getAllBooks();

            ObservableList<BookTm> tableData = FXCollections.observableArrayList();
            for (Book book : allBook) {
                tableData.add(new BookTm(
                        book.getBookId(),
                        book.getReaId(),
                        book.getName(),
                        book.getCategory(),
                        book.getDate(),
                        new Button("Update")
                ));

            }
            // Set Data to table
            tblBook.getItems().setAll(tableData);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void openBookManagementFormOnAction(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/BookManagementForm.fxml"));
            Parent parent = loader.load();
            BookManagementForm controller = loader.getController();
            controller.setEvent(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void selectBookOnAction(ActionEvent actionEvent) {{ search(txtSearch.getText());}}

    private void search(String value) {
        try {
            List<Book> books =new BookController().searchBook(value);
            ObservableList<BookTm> tableData = FXCollections.observableArrayList();
            for (Book book : books) {
                tableData.add(new BookTm(
                        book.getBookId(),
                        book.getReaId(),
                        book.getName(),
                        book.getCategory(),
                        book.getDate(),
                        new Button("Update")
                ));
            }
            // Set Data to  table
            tblBook.getItems().setAll(tableData);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteBookOnAction(ActionEvent actionEvent) {
        try {
            String BookId = tblBook.getSelectionModel().getSelectedItem().getBookId();
            boolean isDeleted = BookController.deleteBook(BookId);
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
        tblBook.getItems().setAll(loadTableData());
    }

    public void openBookUpdateFormOnAction(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../views/BookUpdateForm.fxml"));
            Parent parent = loader.load();
            BookController controller = loader.<BookController>getController();
            controller.setEvent(this);
            Stage stage=new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
