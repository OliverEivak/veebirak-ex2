package ee.ttu.olivereivak.webbasedapps.repair.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ee.ttu.olivereivak.webbasedapps.repair.entity.Authentication;
import ee.ttu.olivereivak.webbasedapps.repair.test.DAOTestBase;

public class AuthenticationDAOTest extends DAOTestBase {

    @Test
    public void find() {
        AuthenticationDAO authenticationDAO = instance(AuthenticationDAO.class);

        Authentication foundAuthentication = authenticationDAO.findByToken("asd");
        assertEquals(Long.valueOf(1), foundAuthentication.getUserAccount().getId());
    }

}
