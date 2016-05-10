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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by User on 02.05.2016.
 */
public class StudentMaterial implements Initializable
{
    @FXML
    ComboBox Combo_topic;
@FXML
    Label MaterialGet;
    Connection c = null;
    Statement stmt = null;
    PreparedStatement pr;
    private ObservableList<TopicData> data;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            System.out.println("я в комбобоксе");
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Program Files\\Java\\Chumakov_kurs\\src\\sample\\Material.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            data = FXCollections.observableArrayList();
            ResultSet rs = c.createStatement().executeQuery("select * from Material");
            while (rs.next()) {
/*Заполнение комбобокс*/
                Combo_topic.getItems().addAll(rs.getString("TOPIC"));
            }

        }
        catch (Exception e)
        {

            e.printStackTrace();
            System.out.println("Проблема в комбобоксе");
        }
    }

    public static class TopicData
    {

        private StringProperty TOPIC;

        private TopicData(String TOPIC) {
            this.TOPIC = new SimpleStringProperty(TOPIC);
        }

        public StringProperty TOPICProperty() {
            return TOPIC;
        }

    }


}
