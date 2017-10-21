package ua.artcode.udiary.tests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.artcode.udiary.config.ConfigHolder;
import ua.artcode.udiary.config.ObjectHolder;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.model.UserBuilder;
import ua.artcode.udiary.utils.EmailNotificator;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;

@Ignore
public class EmailNotificatorTest {




    private ConfigHolder ch;
    private EmailNotificator em;
    private User user;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        ch = ObjectHolder.getObject("ConfigHolder", ConfigHolder.class);
        em = new EmailNotificator(ch);
        user = new UserBuilder()
                .pass("oij")
                .email("presly808@gmail.com")
                .id(12321413241L)
                .dairyList(new ArrayList<>())
                .build();
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

