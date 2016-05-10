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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by User on 29.04.2016.
 */
public class FillCombobox implements Initializable
{
    public static String SelectCombo;
    public static String SelectForQuestion;
    @FXML
    ComboBox Combo_topic;
@FXML
    TextField Question;
    @FXML
            TextField FirstIncorrect;
    @FXML
            TextField SecondIncorrect;
    @FXML
            TextField ThirdIncorrect;
    @FXML
            TextField Correct;
@FXML
    Label post_question;
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
               // System.out.println(Combo_topic.getItems().addAll(rs.getString("TOPIC")));
            }
          /*  student_column.setCellValueFactory(new PropertyValueFactory("STUDENT_NAME"));
            number_column.setCellValueFactory(new PropertyValueFactory("GROUP_NAME"));
            tableUser.setItems(null);
            tableUser.setItems(data);*/






        }
        catch (Exception e)
        {

            e.printStackTrace();
            System.out.println("Error on Building Data");
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
    public void AddQuestion(ActionEvent event) {
if ((!Question.getText().isEmpty())||(!Correct.getText().isEmpty())||(!Combo_topic.getSelectionModel().getSelectedItem().toString().isEmpty())){
  //     ReadQuestion read=new ReadQuestion();
//    read.initialize(null,null);
   // if(read.questions.size()<100) {
        Admin admin = new Admin(Combo_topic.getSelectionModel().getSelectedItem().toString(), Question.getText(), Correct.getText());
        admin.getQuestion(Combo_topic.getSelectionModel().getSelectedItem().toString(), Question.getText(), Correct.getText());

        try {

            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddQuestion.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*else
    {
        post_question.setText("Вы записали максимум вопросов");
    }*/

        else
        {
post_question.setText("Проверьте тему материала, вопрос и ответ на вопрос");
        }
    }
    public void ReadMaterial(ActionEvent event) {
SelectCombo=Combo_topic.getSelectionModel().getSelectedItem().toString();
        try {

            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Read_Material.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ReadQuestion(ActionEvent event) {
        SelectForQuestion=Combo_topic.getSelectionModel().getSelectedItem().toString();

        try {

            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReadQuestion.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
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
    public void BacktoUserMenu(ActionEvent event) {


        try {

            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserMenu.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
