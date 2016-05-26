package ee.ttu.olivereivak.webbasedapps.repair.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.ws.rs.ClientErrorException;

import org.junit.Test;

import ee.ttu.olivereivak.webbasedapps.repair.entity.Authentication;
import ee.ttu.olivereivak.webbasedapps.repair.test.IntegrationTestBase;

public class LogoutResourceITest extends IntegrationTestBase<ILogoutResource> {

    @Test
    public void testLogout() throws Exception {
        Authentication authentication = login("juhan", "test");

        ILogoutResource client = getClientWithAuthentication(authentication);

        client.logout();
    }

    @Test
    public void testLogoutWhenNotLoggedIn() throws Exception {
        ILogoutResource client = getClient();

        try {
            client.logout();
            fail("Exception expected");
        } catch (ClientErrorException e) {
            assertEquals("HTTP 403 Forbidden", e.getMessage());
        }
    }

}
