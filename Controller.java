package sample;

import com.itextpdf.text.DocumentException;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.ibex.nestedvm.util.Seekable;
import sun.net.www.protocol.file.Handler;
import sun.util.calendar.LocalGregorianCalendar;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by User on 11.04.2016.
 */
public class Controller
{
@FXML
    TextField Topic;
    @FXML
    TextArea Material;
    @FXML
    private PasswordField kod=new PasswordField();
    @FXML
   TextField fio;
    @FXML
    TextField group_name;
    @FXML
    TableColumn<Person, String> student_column;
    @FXML
    TableColumn<Person, String> number_column;
    @FXML
    ImageView viewimage;
    @FXML
    Label failed_exams;
    @FXML
    Label password;
    @FXML
    Label admin_pass;
    @FXML
    Label post_material;



                                         public void User(ActionEvent event) throws Exception {
                                    try {

                                            ((Node) (event.getSource())).getScene().getWindow().hide();
                                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserIn.fxml"));
                                            Parent root1 = (Parent) fxmlLoader.load();
                                            Stage stage = new Stage();
                                            stage.setScene(new Scene(root1));
                                            stage.show();
                                        }

                                     catch(Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                                                                            public void UserMenu(ActionEvent event) throws Exception {

                                                                                                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyг.");
                                                                                                Date currentDate = new Date();
                                                                                                System.out.println("currentDate = " + sdf.format(currentDate).toString());
                                                                                               String time=sdf.format(currentDate).toString();

                                                                                                Person person = new Person(fio.getText(), group_name.getText(), time);
                                                                                                person.getUser(fio.getText(), group_name.getText(), time);
                                                                                                if((fio.getText().length()<8)||(fio.getText().isEmpty())||(group_name.getText().isEmpty())||(group_name.getText().contains("-"))||(fio.getText().contains(" ")))
                                                                                                {
                                                                                                if (person.flag) {
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
                                                                                                }}
                                                                                                else
                                                                                                {

                                                                                                        password.setText("Данные для входа не прошли валидацию");

                                                                                                }

                                                                                            }
    public void StudentMaterial(ActionEvent event) {


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
    public void StudentQuestion(ActionEvent event) {


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
    public void Login(ActionEvent event) {


        try {

            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
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
    public void Chooser(ActionEvent event) throws IOException, DocumentException {
        ReadMark readMark=new ReadMark();
        readMark.initialize(null,null);
        readMark.WorthTopic();
        System.out.println(ReadMark.student_marks);
        if(ReadMark.student_marks>60)
        {
        String userSelectedPath;
        FileChooser fc=new FileChooser();
        fc.setTitle("Save file");
        fc.setInitialFileName("Сертифекат");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Adobe Acrobat Document", ".pdf"));
        final File selectedFile = fc.showSaveDialog(null);

        userSelectedPath = selectedFile.getAbsolutePath();
        if (!userSelectedPath.endsWith(".pdf")) {
            userSelectedPath = userSelectedPath + ".pdf";
        }
        Border.createPdf(userSelectedPath);}
        else
        {
            System.out.println("Вы не прошли контроль");
            failed_exams.setText(ReadMark.msgForLabel);
System.out.println(ReadMark.msgForLabel);
        }
        /*try {

            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Student_Question.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
    @FXML
                                                        public void Admin(ActionEvent event) throws Exception {
                                                            try {

                                                                ((Node) (event.getSource())).getScene().getWindow().hide();
                                                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminIn.fxml"));
                                                                Parent root1 = (Parent) fxmlLoader.load();
                                                                Stage stage = new Stage();
                                                                stage.setScene(new Scene(root1));
                                                                stage.show();


                                                            } catch(Exception e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                                                        public void AdminMenu(ActionEvent event) {



                                                                                          if ((!kod.getText().isEmpty())||(kod.getText().equals("true"))) {
                                                                                              Person person=new Person(kod.getText());
                                                                                              person.getLogin(kod.getText());
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
                                                                                            else
                                                                                          {
                                                                                              admin_pass.setText("Пароль неверный!");
                                                                                          }
                                                                                        }
    public void StudyStudent(ActionEvent event) {



            try {

                ((Node) (event.getSource())).getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudyStudent.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public void AddMaterial(ActionEvent event) {


            try {

                ((Node) (event.getSource())).getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Material.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    public void StudentList(ActionEvent event) {

        try {

            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Students.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void SaveMaterial(ActionEvent event) {
if((!Topic.getText().isEmpty())|| (!Material.getText().isEmpty())) {
            Admin admin = new Admin(Topic.getText(), Material.getText());
            admin.getMaterial(Topic.getText(), Material.getText());
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
        else
        {
            post_material.setText("Проверте тему материала и содержимое материала");
        }

    }
    public void AddImage(ActionEvent event) {

        FileChooser fc=new FileChooser();
        fc.getInitialDirectory();
       fc.getExtensionFilters().addAll(new
                FileChooser.ExtensionFilter("Image", "*.jpg"));
        File selectedFile=fc.showOpenDialog(null);

        if (selectedFile!=null)
        {
            try {

                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                viewimage.setImage(image);

            }
            catch (IOException ex)
            {
System.out.println("Не загрузилось");
            }
        }
        else
        {
            System.out.println("Картинка не загружена");
        }

    }
   public void AddQuestion(ActionEvent event) {


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

    }