package ee.ttu.olivereivak.webbasedapps.repair.services;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import ee.ttu.olivereivak.webbasedapps.repair.dao.AuthenticationDAO;
import ee.ttu.olivereivak.webbasedapps.repair.dao.UserAccountDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.Authentication;
import ee.ttu.olivereivak.webbasedapps.repair.entity.UserAccount;

@RunWith(EasyMockRunner.class)
public class LoginServiceTest {

    @TestSubject
    private LoginService loginService = new LoginService();

    @Mock
    private UserAccountDAO userAccountDAO;

    @Mock
    private AuthenticationDAO authenticationDAO;

    @Test
    public void login() throws Exception {
        String username = "some-user";
        String password = "some-pw";

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        userAccount.setPassword("$2a$10$IiZjyNNGL6XLR/T3HN5KOO3ZP7yFnhj3E8E.9F6pm7/FlBjd1Vf8G"); // some-pw
        expect(userAccountDAO.findByUsername(username)).andReturn(userAccount);

        Capture<Authentication> capturedAuthentication = EasyMock.newCapture();
        expectUpdate(capturedAuthentication);

        replay(userAccountDAO, authenticationDAO);

        Authentication authentication = loginService.login(username, password);

        verify(userAccountDAO, authenticationDAO);

        assertSame(capturedAuthentication.getValue(), authentication);
        assertEquals(username, authentication.getUserAccount().getUsername());
    }

    @Test
    public void loginWrongPassword() throws Exception {
        String username = "some-user";
        String password = "wrong-pw";

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        userAccount.setPassword("$2a$10$IiZjyNNGL6XLR/T3HN5KOO3ZP7yFnhj3E8E.9F6pm7/FlBjd1Vf8G"); // some-pw
        expect(userAccountDAO.findByUsername(username)).andReturn(userAccount);

        replay(userAccountDAO, authenticationDAO);

        Authentication authentication = loginService.login(username, password);

        verify(userAccountDAO, authenticationDAO);

        assertNull(authentication);
    }

    private void expectUpdate(Capture<Authentication> capturedAuthentication) {
        expect(authenticationDAO.update(EasyMock.capture(capturedAuthentication)))
                .andAnswer(capturedAuthentication::getValue);
    }

}
