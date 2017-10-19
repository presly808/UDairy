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

    public boolean useTransportSend = true;
    private String userMail;
    private String userPass;
    private Session session;
    private static final String filePathForNewUser = "/view/mailTemplates/NewUserRegister.txt";


    public EmailNotificator(ConfigHolder configHolder) {
        userMail = configHolder.getProperty("app.smtp.email");
        userPass = configHolder.getProperty("app.smtp.pass");
        initialize();
    }

    protected void initialize() {
        session = createEmailSession();
    }

    public void sendOnUserRegister(User user) throws MessagingException{
        String absFilePass = new File(EmailNotificator.class.getResource(filePathForNewUser).getFile()).getAbsolutePath();

        String emailSubject = "Welcome to UDairy!";
        String text = String.format(readContentFromFile(absFilePass), user.getEmail(), user.getPass());

        sendMail(user.getEmail(), emailSubject, text);
    }

   /*write validate letter maybe in future import mail from txt file*/

    private String readContentFromFile(String absolutePathToFile){
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(absolutePathToFile)));
            String temp;
            while ((temp = reader.readLine()) != null) {
                stringBuffer.append(temp + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    public void sendMail(String to, String emailSubject, String body) throws MessagingException {
        MimeMessage message = new MimeMessage(createEmailSession());
        message.setFrom(new InternetAddress(userMail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(emailSubject);
        message.setText(body);
        if (useTransportSend){
            Transport.send(message);
        }
        System.out.println("Send message successfully....");

    }

    protected Session createEmailSession() {
        Properties props = createProperties();
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userMail, userPass);
                    }
                });
        return session;
    }

    protected final Properties createProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        return props;
    }

}
