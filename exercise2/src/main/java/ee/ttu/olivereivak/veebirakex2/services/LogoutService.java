package ee.ttu.olivereivak.veebirakex2.services;

import javax.inject.Inject;
import javax.inject.Singleton;

import ee.ttu.olivereivak.veebirakex2.dao.AuthenticationDAO;
import ee.ttu.olivereivak.veebirakex2.entity.Authentication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class LogoutService  {

    @Inject private AuthenticationDAO authenticationDAO;

    public void logout(Authentication authentication) {
        log.info(String.format("User %s is logging out", authentication.getUser().getUsername()));
        authenticationDAO.remove(authentication);
    }

}
