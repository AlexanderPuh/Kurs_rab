package sample;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Created by User on 06.04.2016.
 */
public class Main extends Application {
    @Override
            public void start(Stage primaryStage) throws Exception{

              Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                primaryStage.setTitle("Chumakov");
                primaryStage.setMinHeight(600);
                primaryStage.setMaxWidth(1375);
               primaryStage.setScene(new Scene(root, 1375, 705));
                primaryStage.show();

            }

                                public static void main(String[] args)

                                {
                                    System.out.println("Я в мэине");
                                    launch(args);
                                }
    }

