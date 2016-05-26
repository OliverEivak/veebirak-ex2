package ee.ttu.olivereivak.webbasedapps.repair.services;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.mindrot.jbcrypt.BCrypt;

import ee.ttu.olivereivak.webbasedapps.repair.dao.AuthenticationDAO;
import ee.ttu.olivereivak.webbasedapps.repair.dao.UserAccountDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.Authentication;
import ee.ttu.olivereivak.webbasedapps.repair.entity.UserAccount;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class LoginService {

    @Inject
    private UserAccountDAO userAccountDAO;

    @Inject
    private AuthenticationDAO authenticationDAO;

    private SecureRandom random = new SecureRandom();

    public Authentication login(String username, String password) {
        UserAccount userAccount = userAccountDAO.findByUsername(username);

        if (userAccount != null && BCrypt.checkpw(password, userAccount.getPassword())) {
            log.info(String.format("User %s logged in using password", username));
            return getAuthentication(userAccount);
        }

        log.info(String.format("User %s failed to log in", username));
        return null;
    }

    private Authentication getAuthentication(UserAccount userAccount) {
        Authentication authentication = new Authentication();
        authentication.setUserAccount(userAccount);
        authentication.setToken(new BigInteger(130, random).toString(32));

        return authenticationDAO.update(authentication);
    }
}
