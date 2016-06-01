package ee.ttu.olivereivak.webbasedapps.repair.services;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import ee.ttu.olivereivak.webbasedapps.repair.dao.AuthenticationJdbcDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.Authentication;

@Slf4j
@Singleton
public class LogoutService  {

    @Inject
    private AuthenticationJdbcDAO authenticationJdbcDAO;

    public void logout(Authentication authentication) {
        log.info(String.format("User %s is logging out", authentication.getUserAccount().getUsername()));
        authenticationJdbcDAO.remove(authentication);
    }

}
