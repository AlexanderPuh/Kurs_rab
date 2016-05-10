package sample;



import javax.swing.*;
import java.sql.*;

/**
 * Created by User on 27.04.2016.
 */
public class sqliteConnection
{


    public static void ReadSql(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:students.sqlite");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Students;" );
            while ( rs.next() ) {
                String testname= rs.getString("STUDENT_NAME");
                String testgroup= rs.getString("GROUP_NAME");
                String testtime=rs.getString("TIME");
                System.out.println( "NAME = " + testname);
                System.out.println( "GROUP = " + testgroup);
                System.out.println("TIME = " +testtime);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

    }
    public static void WriteSQL()
    {


        try {

            Connection dbConnection = null;
            PreparedStatement preparedStatement = null;
            String insertTableSQL = "INSERT INTO Students"
                    + "(STUDENT_NAME, GROUP_NAME, TIME) VALUES"
                    + "(?,?,?)";
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, Person.name);
            preparedStatement.setString(2, Person.group);
            preparedStatement.setString(3, Person.time);

            // execute insert SQL statement
            preparedStatement.executeUpdate();

            System.out.println("Record is inserted into DBUSER table!");

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
                    "jdbc:sqlite:C:\\Program Files\\Java\\Chumakov_kurs\\src\\sample\\students.sqlite");
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }

}
