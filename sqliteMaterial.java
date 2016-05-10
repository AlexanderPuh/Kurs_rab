package sample;

import java.sql.*;

/**
 * Created by User on 28.04.2016.
 */
public class sqliteMaterial {

    public static void ReadSql() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Material.sqlite");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Material;");
            while (rs.next()) {
                String testname = rs.getString("TOPIC");
                String testgroup = rs.getString("MATERIAL");
                System.out.println("Topic = " + testname);
                System.out.println("Material = " + testgroup);
                System.out.println();
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
            String insertTableSQL = "INSERT INTO Material"
                    + "(TOPIC, MATERIAL) VALUES"
                    + "(?,?)";
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, Admin.new_topic);
            preparedStatement.setString(2, Admin.new_material);

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
                    "jdbc:sqlite:C:\\Program Files\\Java\\Chumakov_kurs\\src\\sample\\Material.sqlite");
            return dbConnection;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }
}
