package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import sample.Controller;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by User on 06.04.2016.
 */
class Person  {

    // JSONObject json=null;
    public static ArrayList<String> surname=new ArrayList<String>();
    public static ArrayList<String> numberOfgroup=new ArrayList<String>();
    FileWriter file;
private String key;
    public static String time;
    public static String name;
    public static String group;
    public boolean flag=false;
            Person(String key)
    {
        this.key=key;
    }
   public Person(String name,String group, String time)
    {
        this.name=name;
        this.group=group;
        this.time=time;
    }

    public void getLogin(String key) {
        System.out.println(key);//Вывод пароля

        if (key.equals("true")) {
            flag=true;
            System.out.println("Вы администратор");
            Admin user = new Admin();
            user.menuAdmin();
        }
        else {
            getUser(name,group, time);
        }
    }
    public void getUser(String name, String group, String time) {
        System.out.println("Введите свое имя:");
          System.out.println(name);
        System.out.println("Введите номер группы:");
        System.out.println(group);
        System.out.println("Время авторизации:");
        System.out.println(time);
        sqliteConnection.WriteSQL();

       // sqliteConnection.ReadSql();
      // writeJson("students.json",name,group);
       // readJson("students.json");
        flag = true;
        User user = new User();
        user.menuUser();

    }}

   /* static JSONArray studentArray=null;
    static JSONArray groupArray=null;
    static boolean flag1=false;
    public static void writeJson(String file, String name, String group)
    {
if(!flag1){
        json=new JSONObject();

    studentArray=new JSONArray();

    groupArray=new JSONArray();
    flag1=true;
}

         studentArray.add(name);
        json.put("students_name",studentArray);
        groupArray.add(group);
        json.put("group_name",groupArray);
        try { System.out.println("Writting JSON into file ...");
            System.out.println(json);
            FileWriter jsonFileWriter = new FileWriter(file, true);
            BufferedWriter buffer=new BufferedWriter(jsonFileWriter);
            buffer.write(json.toJSONString());
            buffer.flush();
            buffer.close();
            jsonFileWriter.close();
            System.out.println("Done"); }
        catch (IOException e)
        { e.printStackTrace(); }
    }
    public static void readJson(String file) {
        JSONParser parser = new JSONParser();

        try {
            System.out.println("Reading JSON file from Java program");
            FileReader fileReader = new FileReader(file);
            JSONObject json = (JSONObject) parser.parse(fileReader);

            JSONArray students_name = (JSONArray)json.get("students_name");
            Iterator i = students_name.iterator();
            JSONArray group_name = (JSONArray)json.get("group_name");
            Iterator n = group_name.iterator();
            System.out.println("students: ");
            while (i.hasNext()) {
                System.out.println(" " + i.next());
            }
            while (n.hasNext()) {
                System.out.println(" " + n.next());
            }

          numberOfgroup.addAll(numberOfgroup);

            for(int j=0;j<surname.size();j++)
            {
                System.out.println(surname.get(j));
                System.out.println(j);
            }
            for(int j=0;j<numberOfgroup.size();j++)
            {
                System.out.println(numberOfgroup.get(j));
                System.out.println(j);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}









   */