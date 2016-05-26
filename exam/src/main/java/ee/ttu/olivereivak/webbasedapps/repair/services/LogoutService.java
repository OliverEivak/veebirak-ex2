package ee.ttu.olivereivak.webbasedapps.repair.services;

import javax.inject.Inject;
import javax.inject.Singleton;

import ee.ttu.olivereivak.webbasedapps.repair.dao.AuthenticationDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.Authentication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class LogoutService  {

    @Inject private AuthenticationDAO authenticationDAO;

    public void logout(Authentication authentication) {
        log.info(String.format("User %s is logging out", authentication.getUserAccount().getUsername()));
        authenticationDAO.remove(authentication);
    }

}
