package sample;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by User on 26.04.2016.
 */
public class User {



        public void getMaterial()
        {
            System.out.println("Введите искомый материал");
            Scanner newfind=new Scanner(System.in);
            BufferedReader in;
            String s;
            StringBuffer s2;
            try {
                in = new BufferedReader(new FileReader("material.txt"));
                s2 = new StringBuffer();
                while ((s = in.readLine()) != null) {
                    s2.append(s + "\n") ;
                }
                System.out.println(s2);
                in.close();
            } catch(FileNotFoundException ex) {
                System.out.println(ex);
            } catch (IOException ex){
                System.out.println(ex);
            }

        }
        public void getQuestions()
        {
            BufferedReader in;
            String s;
            StringBuffer s2;
            try {
                in = new BufferedReader(new FileReader("questions.txt"));
                s2 = new StringBuffer();
                while ((s = in.readLine()) != null) {
                    if(s.contains("у"))
                    {


                    }
                    s2.append(s + "\n") ;
                }
                System.out.println(s2);
                in.close();
            } catch(FileNotFoundException ex) {
                System.out.println(ex);
            } catch (IOException ex){
                System.out.println(ex);
            }

        }
        public void menuUser()
        {
            System.out.println("1)Материал");
            System.out.println("2)Вопросы");
            System.out.println("3)Выход из программы");

            System.out.println("Выберите пункт меню:");
    /*    Scanner newchoice=new Scanner(System.in);
        int choice=newchoice.nextInt();

        switch (choice)
        {
            case(1):
                getQuestions();
            case (2):
                getMaterial();
            case(3):
                break;
        }
*/


        }

    }
