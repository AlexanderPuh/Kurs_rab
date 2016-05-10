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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by User on 03.05.2016.
 */
public class ReadMaterial implements Initializable
{
    @FXML
Label MaterialGet;
    @FXML
    Text NameOfMaterial;

    private static int Searching_index;
    private ObservableList<MaterialData> data;
    ArrayList<String> topics=new ArrayList<String>();
    ArrayList<String> search_material=new ArrayList<String>();
    ArrayList<String> false_first=new ArrayList<String>();
    ArrayList<String> false_second=new ArrayList<String>();
    ArrayList<String> false_third=new ArrayList<String>();
    ArrayList<String> correct=new ArrayList<String>();
    Statement stat;
    Connection con;
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\Program Files\\Java\\Chumakov_kurs\\src\\sample\\Material.sqlite");
            con.setAutoCommit(false);
            stat = con.createStatement();
            //stat.executeUpdate("drop table if exists user");
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("select * from Material");
            while (rs.next()) {
                topics.add(rs.getString("TOPIC"));
                search_material.add(rs.getString("MATERIAL"));
//MaterialGet.setText(rs.getString("MATERIAL"));
         //       MaterialGet.textProperty().setValue(data.toString());
              //  data.add(new MaterialData(rs.getString("MATERIAL")));
              //  System.out.println(data);
            }
            for(int i=0;i<topics.size();i++)
            {

                if(topics.get(i).equals(FillCombobox.SelectCombo))
                {
                 Searching_index= i;
                }
            }
            NameOfMaterial.setText(topics.get(Searching_index).toString());
            MaterialGet.setText(search_material.get(Searching_index).toString());
         //MaterialGet.setCellValueFactory(new PropertyValueFactory("STUDENT_NAME"));
          //  tableUser.setItems(null);
           // tableUser.setItems(data);
           // MaterialGet.setText(data.toString());
//MaterialGet.textProperty().setValue(data.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
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
    public void BacktoSelect(ActionEvent event) {


        try {

            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Student_Material.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
