package ua.artcode.udiary.utils;

import ua.artcode.udiary.config.ConfigHolder;
import ua.artcode.udiary.model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;

// todo not static, we will be able to mock
public class EmailNotificator {

    private final String userMail;
    private final String userPass;

    // todo extract
    private static final String filePathForNewUser = "/view/mailTemplates/NewUserRegister.txt";


    public EmailNotificator(ConfigHolder configHolder) {
        userMail = configHolder.getProperty("app.smtp.email");
        userPass = configHolder.getProperty("app.smtp.pass");
    }


    // todo extract
    public void sendOnUserRegister(User user) throws MessagingException{
        String absFilePass = new File(ClassPathUtils.classpathToAbsolutePath(filePathForNewUser)).getAbsolutePath();

        String emailSubject = "Welcome to UDairy!";
        String text = String.format(FileUtils.readContentFromFile(absFilePass), user.getEmail(), user.getPass());

        sendMail(user.getEmail(), emailSubject, text);
    }


    public void sendMail(String to, String emailSubject, String body) throws MessagingException {
        MimeMessage message = new MimeMessage(createEmailSession());
        message.setFrom(new InternetAddress(userMail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(emailSubject);
        message.setText(body);
        Transport.send(message);
        System.out.println("Send message successfully....");

    }

    private Session createEmailSession() {
        Properties props = createProperties();
        return Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userMail, userPass);
                    }
                });
    }

    private final Properties createProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        return props;
    }

}
