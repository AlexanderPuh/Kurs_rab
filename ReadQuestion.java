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
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by User on 03.05.2016.
 */
public class ReadQuestion implements Initializable {
    @FXML
    Text NameOfMaterial;
    @FXML
    Text QuestionContent;
    @FXML
    TextField Answer;
public static int startTime=0;

    public static String SelectForQuestion;

    private static int Searching_index;
    private ObservableList<MaterialData> data;
public static int ball=0;
    public static int timeSpent=0;
    private static int quetion_count = 0;
    ArrayList<String> student_answer = new ArrayList<String>();
    ArrayList<String> answers = new ArrayList<String>();
  public static ArrayList<Integer> topicindex = new ArrayList<Integer>();
  public static ArrayList<String> topics = new ArrayList<String>();
    ArrayList<String> questions = new ArrayList<String>();
    ArrayList<String> correct = new ArrayList<String>();
    Statement stat;
    Connection con;

    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\Program Files\\Java\\Chumakov_kurs\\src\\sample\\Question.sqlite");
            con.setAutoCommit(false);
            stat = con.createStatement();
            //stat.executeUpdate("drop table if exists user");
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("select * from Question");
            while (rs.next()) {
                topics.add(rs.getString("TOPIC"));
                questions.add(rs.getString("QUESTION"));
                correct.add(rs.getString("CORRECT"));
                // Create an array


            }
            for (int i = 0; i < topics.size(); i++) {

                if (topics.get(i).equals(FillCombobox.SelectForQuestion)) {
                    topicindex.add(i);
                }
            }
            NameOfMaterial.setText(topics.get(topicindex.get(0)).toString());


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public void NewQuestion(ActionEvent event) throws Exception {

        try {
            if (quetion_count==0) {
                startTime = (int)System.currentTimeMillis();
            }
QuestionContent.setText(questions.get(topicindex.get(quetion_count)));
if(quetion_count>0){
            student_answer.add(Answer.getText());
            System.out.println(student_answer);}
            quetion_count=quetion_count+1;

        }
        catch (Exception e)
        {
            student_answer.add(Answer.getText());
            System.out.println(student_answer);
            if(student_answer.size()==topicindex.size())
            {
                for (int i=0; i<student_answer.size();i++)
                {
                    if(student_answer.get(i).equals(correct.get(topicindex.get(i))))
                    {
                        ball++;
                    }
                }

                       timeSpent= ((int)System.currentTimeMillis() - startTime)/1000;

                QuestionContent.setText("Вы ответили правильно на " +ball+"ответа.За " +timeSpent+"(сек)");
                Answer.clear();
            }
        }


    }


    public static class MaterialData {

        private StringProperty MATERIAL;


        private MaterialData(String MATERIAL) {
            this.MATERIAL = new SimpleStringProperty(MATERIAL);
        }

        public StringProperty MATERIALProperty() {
            return MATERIAL;
        }
    }
    public void Menu(ActionEvent event) throws AddressException, MessagingException {
Admin admin=new Admin(Person.name,Person.group,ReadQuestion.topics.get(ReadQuestion.topicindex.get(0)), ball, timeSpent);
admin.getStudents(Person.name,Person.group,ReadQuestion.topics.get(ReadQuestion.topicindex.get(0)), ball, timeSpent);
        Email.generateAndSendEmail();
        System.out.println("Cообщение отправлено!");
        try {

            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Student_Question.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
