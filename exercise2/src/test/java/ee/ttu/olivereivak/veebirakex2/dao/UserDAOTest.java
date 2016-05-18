package ee.ttu.olivereivak.veebirakex2.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import ee.ttu.olivereivak.veebirakex2.entity.Role;
import ee.ttu.olivereivak.veebirakex2.entity.User;
import ee.ttu.olivereivak.veebirakex2.test.DAOTestBase;

public class UserDAOTest extends DAOTestBase {

    @Test
    public void find() {
        UserDAO userDAO = instance(UserDAO.class);

        User user = userDAO.findByUsername("john");
        assertEquals(Long.valueOf(1), user.getId());
        Assert.assertEquals(Role.USER, user.getRole());
    }

}
