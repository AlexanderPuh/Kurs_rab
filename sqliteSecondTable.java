package sample;

import java.sql.*;

/**
 * Created by User on 04.05.2016.
 */
public class sqliteSecondTable {




        public static void WriteSQL()
        {


            try {

                Connection dbConnection = null;
                PreparedStatement preparedStatement = null;

                System.out.println("я в бд");
                dbConnection = getDBConnection();
                System.out.println("я в бд");

                preparedStatement = dbConnection.prepareStatement("INSERT INTO Exams"
                        + "(STUDENT, TOPIC, MARK, EXAM_TIME) VALUES"
                        + "(?,?,?,?)");
                System.out.println("я в бд");

                preparedStatement.setString(1, Admin.name+"/"+Admin.group);
                System.out.println("я в бд1");

               /* preparedStatement.setString(2, Admin.group);
                System.out.println("я в бд2");*/

                preparedStatement.setString(2, Admin.topic);
                preparedStatement.setInt(3, Admin.ball);
                preparedStatement.setInt(4, Admin.time);
              //  preparedStatement.setString(5, Person.group);
                // execute insert SQL statement
                preparedStatement.executeUpdate();

                System.out.println("Record is inserted into DBUSER table!");

            } catch (SQLException e) {

                System.out.println(e.getMessage());

            }

        }

        private static Connection getDBConnection() {

            Connection dbConnection = null;

            try {

                Class.forName("org.sqlite.JDBC");

            } catch (ClassNotFoundException e) {

                System.out.println(e.getMessage());

            }

            try {

                dbConnection = DriverManager.getConnection(
                        "jdbc:sqlite:C:\\Program Files\\Java\\Chumakov_kurs\\src\\sample\\Exams.sqlite");
                return dbConnection;

            } catch (SQLException e) {

                System.out.println(e.getMessage());

            }

            return dbConnection;

        }

    }

