package ua.artcode.udiary.utils;
import ua.artcode.udiary.config.ConfigHolder;
import ua.artcode.udiary.config.ObjectHolder;
import ua.artcode.udiary.model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

// todo not static, we will be able to mock
public class NotificationUtils {

   private static ConfigHolder ch = ObjectHolder.getObject("ConfigHolder",ConfigHolder.class);
   private final static String USER_EMAIL = ch.getProperty("smtp.email");
   private final static String USER_PASS = ch.getProperty("smtp.pass");
   private final static String WELCOME_SUBJECT = ch.getProperty("app.smtp.welcomesubj");

    /*write validate letter maybe in future import mail from txt file*/

    // todo pass (to..., title, body)
    public static void sendMail(User user){
        // todo init session , only one time, prepare message
        String to = user.getEmail();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USER_EMAIL, USER_PASS);
                    }
                });
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(USER_EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(WELCOME_SUBJECT);
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
