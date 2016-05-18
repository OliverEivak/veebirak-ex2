package ee.ttu.olivereivak.veebirakex2.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ee.ttu.olivereivak.veebirakex2.entity.Authentication;
import ee.ttu.olivereivak.veebirakex2.test.DAOTestBase;

public class AuthenticationDAOTest extends DAOTestBase {

    @Test
    public void find() {
        AuthenticationDAO authenticationDAO = instance(AuthenticationDAO.class);

        Authentication foundAuthentication = authenticationDAO.findByToken("qwe");
        assertEquals("jane", foundAuthentication.getUser().getUsername());
    }

}
