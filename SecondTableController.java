package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

public class SecondTableController implements Initializable{

    @FXML
    TableView tableStudent;
    @FXML
    TableColumn student;
    @FXML
    TableColumn group;
    @FXML
    TableColumn lesson;
    @FXML
    TableColumn mark;
    @FXML
    TableColumn time_complete;
    private ObservableList<StudentData>  studentDatas;
    Statement second;
    Connection second_con;
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("org.sqlite.JDBC");
            second_con=DriverManager.getConnection("jdbc:sqlite:C:\\Program Files\\Java\\Chumakov_kurs\\src\\sample\\Exams.sqlite");
            second_con.setAutoCommit(false);
            second=second_con.createStatement();
            second.executeUpdate("drop table if exists user");
            studentDatas=FXCollections.observableArrayList();
            ResultSet second_res=second_con.createStatement().executeQuery("select * from Exams");

            while (second_res.next())
            {
                studentDatas.add(new StudentData(second_res.getString("STUDENT"),second_res.getString("TOPIC"),second_res.getInt("MARK"),second_res.getInt("EXAM_TIME")));
            }


            student.setCellValueFactory(new PropertyValueFactory("STUDENT"));
           // group.setCellValueFactory(new PropertyValueFactory("GROUP"));
            lesson.setCellValueFactory(new PropertyValueFactory("TOPIC"));
            mark.setCellValueFactory(new PropertyValueFactory("MARK"));
            time_complete.setCellValueFactory(new PropertyValueFactory("EXAM_TIME"));

            tableStudent.setItems(null);
            tableStudent.setItems(studentDatas);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }


    public static class StudentData {

        private StringProperty STUDENT;
        private StringProperty GROUP;
        private StringProperty TOPIC;
        private IntegerProperty MARK;
        private IntegerProperty EXAM_TIME;

        private StudentData(String STUDENT, String TOPIC, int MARK, int EXAM_TIME) {
            this.STUDENT = new SimpleStringProperty(STUDENT);
           // this.GROUP = new SimpleStringProperty(GROUP);
            this.TOPIC = new SimpleStringProperty(TOPIC);
            this.MARK = new SimpleIntegerProperty(MARK);
            this.EXAM_TIME = new SimpleIntegerProperty(EXAM_TIME);

        }

        public StringProperty STUDENTProperty() {
            return STUDENT;
        }

       // public StringProperty GROUPProperty() {
      //      return GROUP;
        //}

        public StringProperty TOPICProperty() {return TOPIC;}

        public IntegerProperty MARKProperty() {
            return MARK;
        }

        public IntegerProperty EXAM_TIMEProperty() {
            return EXAM_TIME;
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

