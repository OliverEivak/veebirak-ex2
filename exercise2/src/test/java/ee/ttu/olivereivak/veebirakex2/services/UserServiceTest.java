package ee.ttu.olivereivak.veebirakex2.services;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import ee.ttu.olivereivak.veebirakex2.dao.UserDAO;
import ee.ttu.olivereivak.veebirakex2.entity.Role;
import ee.ttu.olivereivak.veebirakex2.entity.User;

@RunWith(EasyMockRunner.class)
public class UserServiceTest {

    @TestSubject
    private UserService userService = new UserService();

    @Mock private UserDAO userDAO;

    @Test
    public void create() {
        Capture<User> capturedUser = EasyMock.newCapture();

        User user = new User();
        user.setUsername("testuser");
        expectUpdate(capturedUser);

        replay(userDAO);

        User createdUser = userService.create(user);

        verify(userDAO);

        assertEquals(Role.USER, createdUser.getRole());
        assertEquals("testuser", createdUser.getUsername());
        assertSame(createdUser, capturedUser.getValue());
    }

    private void expectUpdate(Capture<User> capturedUser) {
        expect(userDAO.update(EasyMock.capture(capturedUser))).andAnswer(capturedUser::getValue);
    }

}
