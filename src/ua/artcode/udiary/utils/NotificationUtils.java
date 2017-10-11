package ua.artcode.udiary.utils;
import ua.artcode.udiary.model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class NotificationUtils {

   private final static String USER_EMAIL = "UDairy.app@gmail.com";
   private final static String USER_PASS = "udairyaco21";
   private static String emailSubject = "Welcome to UDairy!";

    /*write validate letter maybe in future import mail from txt file*/

    public static void sendMail(User user){
        String to = user.getEmail();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USER_EMAIL, USER_PASS);
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(emailSubject);
            String text = String.format("Welcome to UDairy! \n" +
                            " Congratulations, your registration has passed successfully \n \n" +
                            " Your login: %s \n" +
                            " Your password: %s \n", user.getEmail(), user.getPass());
            message.setText(text);
            Transport.send(message);
            System.out.println("Send message successfully....");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
