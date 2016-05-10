package sample;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by User on 08.05.2016.
 */
public class Email
{
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    static final String ENCODING = "koi8-r";
/*
    public static void main(String args[]) throws AddressException, MessagingException {
        generateAndSendEmail();
        System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
    }
*/
    public static void generateAndSendEmail() throws AddressException, MessagingException {

        // Step1
        System.out.println("\n 1st ===> setup Mail Server Properties..");
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");

        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("alexb8466@gmail.com"));
        generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("alexbodrov12@gmail.com"));
        generateMailMessage.setSubject("Прохождение заданий в программе ИПИГ");
   // "<!DOCTYPE html> <html> <head> <meta charset="+"utf-8"+"> </head> <body> <p>Cтудент "+Person.name+"<br>Группы"+Person.group+"<br>Прошел тест по материалу "+ Admin.topic+"<br>За "+Admin.time/60+"</br>минут "+"</br>Оценка "+Admin.ball+"бала(ов)"+ "</br>"+"</br>"+" С уважением, </br>ИПИГ </p> </body> </html>";
                String emailBody = "Cтудент "+Person.name +". Группы"+Person.group+". Прошел тест по материалу "+ Admin.topic+". За "+Admin.time+"секунд "+". Оценка "+Admin.ball+"бала(ов)"+". С уважением, ИПИГ";
        generateMailMessage.setText(emailBody);
        System.out.println("Mail Session has been created successfully..");

        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("smtp.gmail.com", "alexbodrov12@gmail.com", "Test123!");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}
