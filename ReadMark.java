package sample;

import javafx.collections.FXCollections;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by User on 09.05.2016.
 */
public class ReadMark {
   public static ArrayList<String> student_name=new ArrayList<String>();
   public static ArrayList<String> topic_name=new ArrayList<String>();
   public static ArrayList<Integer> marks=new ArrayList<Integer>();
public static String msgForLabel;
  public static   int student_marks=0;
  public static   ArrayList<Integer> student_index=new ArrayList<Integer>();
    Statement stat;
    Connection con;
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\Program Files\\Java\\Chumakov_kurs\\src\\sample\\Exams.sqlite");
            con.setAutoCommit(false);
            stat = con.createStatement();
            //stat.executeUpdate("drop table if exists user");
            ResultSet rs = con.createStatement().executeQuery("select * from Exams");
            while (rs.next()) {
                student_name.add(rs.getString("STUDENT"));
                topic_name.add(rs.getString("TOPIC"));
                marks.add(rs.getInt("MARK"));
                // Create an array


            }
            for (int i = 0; i < student_name.size(); i++) {
                //if (student_name.get(i).indexOf(Person.name)==-1)

                if (student_name.get(i).toString().equals(Person.name + "/" + Person.group)) {
System.out.println(student_name.get(i).toString());
                    student_index.add(i);
                }
            }
            for (int i=0; i<student_index.size();i++) {
                for (int j=1;j<student_index.size();j++) {
                    if (topic_name.get(student_index.get(i)) == topic_name.get(student_index.get(j))) {

                        if ((marks.get(student_index.get(i))) >(marks.get(student_index.get(j))))
                        {
                            marks.remove(student_index.get(j));
                            topic_name.remove(student_index.get(j));
                            student_index.remove(j);
                        }
                        else
                        {
                            marks.remove(student_index.get(i));
                            topic_name.remove(student_index.get(i));
                            student_index.remove(i);
                        }
                    }
                }

            }
            for(int i=0;i<marks.size();i++)
            {
                student_marks=student_marks+marks.get(student_index.get(i));
            }



              /*  if (topics.get(i).equals(FillCombobox.SelectForQuestion)) {
                    topicindex.add(i);
                }*/



        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    public void WorthTopic()
    {
        int min=0;
        for (int i =0;i<student_index.size();i++)
        {
            for (int j=1;j<student_index.size();j++) {
                if (marks.get(student_index.get(i))<marks.get(student_index.get(j)))
                {
                    min=student_index.get(i);
                }
                    else
                {
min=student_index.get(j);
                }
            }
        }
        msgForLabel="Обатите внимание на задания по теме "+topic_name.get(min);
    }
}
