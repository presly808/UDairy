package ua.artcode.udiary.tests;

import org.junit.Test;
import static org.mockito.Mockito.*;
import ua.artcode.udiary.model.User;
import ua.artcode.udiary.utils.NotificationUtils;

public class EmailNotificationTest {


    // todo  where is assert?
    // not valid test
    // we dont need mocks
    @Test
    public void testNotification(){

        User userMock = mock(User.class);

        when(userMock.getEmail()).thenReturn("Ivanpion@gmail.com");

        NotificationUtils.sendMail(userMock);


    }

}
