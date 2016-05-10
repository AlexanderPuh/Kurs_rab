package sample;

import java.sql.*;

/**
 * Created by User on 01.05.2016.
 */
public class sqliteQuestion
{
    public static void ReadSql() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Question.sqlite");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Question;");
            while (rs.next()) {


                String topic = rs.getString("TOPIC");
                String question = rs.getString("QUESTION");
                String correct=rs.getString("CORRECT");

                System.out.println("Topic = " + topic);
                System.out.println("Question = " + question);
                System.out.println("Correct answer = " + correct);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");

    }

    public static void WriteSQL() {


        try {

            Connection dbConnection = null;
            PreparedStatement preparedStatement = null;
            String insertTableSQL = "INSERT INTO Question"
                    + "(TOPIC, QUESTION, CORRECT) VALUES"
                    + "(?,?,?)";
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, Admin.topic);
            preparedStatement.setString(2, Admin.question_name);
            preparedStatement.setString(3, Admin.correct);

            // execute insert SQL statement
            preparedStatement.executeUpdate();

            System.out.println("Record is inserted into DBUSER table!");
            dbConnection.close();


        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } /*finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }*/

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
                    "jdbc:sqlite:C:\\Program Files\\Java\\Chumakov_kurs\\src\\sample\\Question.sqlite");
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }
}
