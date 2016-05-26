package ee.ttu.olivereivak.webbasedapps.repair.services;

import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;

import ee.ttu.olivereivak.webbasedapps.repair.dao.AuthenticationDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.Authentication;
import ee.ttu.olivereivak.webbasedapps.repair.entity.UserAccount;

@RunWith(EasyMockRunner.class)
public class LogoutServiceTest {

    @TestSubject
    private LogoutService logoutService = new LogoutService();

    @Mock
    private AuthenticationDAO authenticationDAO;

    @Test
    public void logout() throws Exception {
        Authentication authentication = new Authentication();
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername("user-who-is-logging-out");
        authentication.setUserAccount(userAccount);

        authenticationDAO.remove(authentication);

        replay(authenticationDAO);

        logoutService.logout(authentication);

        verify(authenticationDAO);
    }

}
