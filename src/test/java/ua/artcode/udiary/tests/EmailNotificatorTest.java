package ua.artcode.udiary.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.artcode.udiary.config.ConfigHolder;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.model.UserBuilder;
import ua.artcode.udiary.utils.EmailNotificator;

import javax.mail.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailNotificatorTest {


    private static final String CONFIG_FILE_PATH = "/app.properties";

    private ConfigHolder ch = new ConfigHolder(new File(EmailNotificatorTest.class.getResource(CONFIG_FILE_PATH).getFile()).getAbsolutePath());

    private User user = new UserBuilder()
            .pass("oij")
            .email("testmail@example.com")
            .id(12321413241L)
            .dairyList(new ArrayList<>())
            .build();


    private EmailNotificator em;


    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        em = new EmailNotificator(ch);

    }


    public EmailNotificatorTest() throws IOException {
    }

    @Test()
    public void positiveSendMail() throws MessagingException {
        em.sendMail("testemail@example.com", "subject", "body");
    }

    @Test()
    public void positiveSendMailTest() throws MessagingException, IOException {
        em.sendOnUserRegister(user);
    }

    @Test(expected = MessagingException.class)
    public void negativeSendMail() throws MessagingException {

        em.sendMail("testemailexample.com", "subject", "body");

    }




}

