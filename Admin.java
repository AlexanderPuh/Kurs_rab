package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by User on 06.04.2016.
 */
public class Admin {
  //  private ArrayList<String> voprosi=new ArrayList<String>();
   // private ArrayList<String> otveti=new ArrayList<String>();
   // private ArrayList<String> material=new ArrayList<String>();
 //   private ArrayList<String> topic=new ArrayList<String>();
public  static String name=Person.name;
    public static String group=Person.group;
    public static int ball;
    public static int time;
    public static String new_material;
    public static String new_topic;
    public static String topic;
    public static String question_name;
    public static String correct;
    Admin(){};
    Admin(String new_topic,String new_material)
    {
        this.new_material=new_material;
        this.new_topic=new_topic;
    }
    Admin(String name, String group, String topic, int ball, int time)
    {
        this.name=name;
        this.group=group;
        this.topic=topic;
        this.ball=ball;
        this.time=time;

    }
    Admin(String topic, String question_name, String correct)
    {
        this.topic=topic;
        this.question_name=question_name;
        this.correct=correct;
    }
    Admin(String topic)
    {
        this.topic=topic;
    }
public void menuAdmin()
{
    System.out.println("1)Добавить вопросы");
    System.out.println("2)Добавить материал");
    System.out.println("3)Выход из программы");

    System.out.println("Выберите пункт меню:");
  /* Scanner newchoice=new Scanner(System.in);
    int choice=newchoice.nextInt();

    switch (choice)
    {
        case(1):
            setQuestions();
        case (2):
            setMaterial();
        case(3):
            break;
    }

*/

}
    public void setQuestions()
    {
       /* System.out.println("Введите кол-во вопросов, которые Вы собираетесь ввести:");
        Scanner newnumber=new Scanner(System.in);
        int number=newnumber.nextInt();
        for(int i=0; i<number;i++)
        {
            System.out.println("Введите вопрос:");
            Scanner newquestion=new Scanner(System.in);
            voprosi.add(i, newquestion.nextLine());
            System.out.println("Введите ответ:");
            Scanner newotvet=new Scanner(System.in);
            otveti.add(i,newotvet.nextLine());
            try(FileWriter writer = new FileWriter("questions.txt", false))
            {
                // запись всей строки

                writer.write(voprosi.get(i)+"-");
                writer.write(otveti.get(i));
                // запись по символам
                writer.append('\n');

                writer.flush();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }*/
        menuAdmin();
        }
public void getMaterial( String new_topic, String new_material)
{

    System.out.println("Введите тему материала:");
    System.out.println(new_topic);
    System.out.println("Введите материал:");
    System.out.println(new_material);
    sqliteMaterial.WriteSQL();
    menuAdmin();

    /*
        System.out.println("Какая тема вводимого материала?");
        Scanner newtopic=new Scanner(System.in);
        topic.add(newtopic.nextLine());
        System.out.println("Ввод материала");
        Scanner newmaterial=new Scanner(System.in);
        material.add(newmaterial.nextLine());
    try(FileWriter writer = new FileWriter("material.txt", false))
    {
        // запись всей строки

        writer.write(topic+"-");
        writer.write('\n');
        writer.write(material+";");
        // запись по символам
        writer.append('\n');

        writer.flush();*/
    }
public void getQuestion(String topic, String question_name, String correct)
{
    System.out.println("Введите тему вопроса:");
    System.out.println(topic);
    System.out.println("Введите название вопроса:");
    System.out.println(question_name);
    System.out.println("Введите правильный ответ:");
    System.out.println(correct);
    sqliteQuestion.WriteSQL();
    menuAdmin();




}
    public void getStudents(String name, String group, String topic, int ball,int time)

    {

        System.out.println("Студент:");
        System.out.println(name);
        System.out.println("Группа");
        System.out.println(group);
        System.out.println("Урок");
        System.out.println(topic);
        System.out.println("Оценка");
        System.out.println(ball);
        System.out.println("Время прохождения");
        System.out.println(time);
        sqliteSecondTable.WriteSQL();
        menuAdmin();


    }
    }

