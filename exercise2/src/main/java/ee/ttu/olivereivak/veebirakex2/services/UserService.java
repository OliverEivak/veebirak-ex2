package ee.ttu.olivereivak.veebirakex2.services;

import javax.inject.Inject;
import javax.inject.Singleton;

import ee.ttu.olivereivak.veebirakex2.dao.UserDAO;
import ee.ttu.olivereivak.veebirakex2.entity.Role;
import ee.ttu.olivereivak.veebirakex2.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class UserService {

    @Inject
    private UserDAO userDAO;

    public User create(User user) {
        user.setRole(Role.USER);
        return userDAO.update(user);
    }
}
