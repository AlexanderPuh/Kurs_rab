package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Created by User on 26.04.2016.
 */

public class TableController implements Initializable{
@FXML
    TableColumn student_column;
    @FXML
   public TableColumn number_column;
    @FXML
    TableColumn login_time;
    @FXML
    TableView tableUser;
     ObservableList<UserData> data;
    Statement stat;
    Connection con;
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\Program Files\\Java\\Chumakov_kurs\\src\\sample\\students.sqlite");
            con.setAutoCommit(false);
                stat = con.createStatement();
            stat.executeUpdate("drop table if exists user");
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("select * from Students");
            while (rs.next()) {
                data.add(new UserData(rs.getString("STUDENT_NAME"), rs.getString("GROUP_NAME"),rs.getString("TIME")));
            }

            student_column.setCellValueFactory(new PropertyValueFactory("STUDENT_NAME"));
            number_column.setCellValueFactory(new PropertyValueFactory("GROUP_NAME"));
            login_time.setCellValueFactory(new PropertyValueFactory("TIME"));

            tableUser.setItems(null);
            tableUser.setItems(data);



        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public static class UserData {

        private StringProperty STUDENT_NAME;
        private StringProperty GROUP_NAME;
        private StringProperty TIME;

        private UserData(String STUDENT_NAME, String GROUP_NAME, String TIME) {
            this.STUDENT_NAME = new SimpleStringProperty(STUDENT_NAME);
            this.GROUP_NAME = new SimpleStringProperty(GROUP_NAME);
            this.TIME = new SimpleStringProperty(TIME);

        }

        public StringProperty STUDENT_NAMEProperty() {
            return STUDENT_NAME;
        }

        public StringProperty GROUP_NAMEProperty() {
            return GROUP_NAME;
        }

        public StringProperty TIMEProperty() {
            return TIME;
        }

        }
    public void BacktoMenu(ActionEvent event) {


        try {

            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminMenu.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }





