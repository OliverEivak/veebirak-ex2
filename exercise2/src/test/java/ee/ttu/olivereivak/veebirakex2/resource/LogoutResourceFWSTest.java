package ee.ttu.olivereivak.veebirakex2.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.ws.rs.ClientErrorException;

import org.junit.Test;

import ee.ttu.olivereivak.veebirakex2.entity.Authentication;
import ee.ttu.olivereivak.veebirakex2.test.FullWebStackTestBase;

public class LogoutResourceFWSTest extends FullWebStackTestBase<ILogoutResource> {

    @Test
    public void testLogout() throws Exception {
        Authentication authentication = login("john", "test");

        ILogoutResource client = getClientWithAuthentication(ILogoutResource.class, //
                authentication.getToken(), authentication.getUser().getUsername());

        client.logout();
    }

    @Test
    public void testLogoutWhenNotLoggedIn() throws Exception {
        ILogoutResource client = getClient(ILogoutResource.class);

        try {
            client.logout();
            fail("Exception expected");
        } catch (ClientErrorException e) {
            assertEquals("HTTP 403 Forbidden", e.getMessage());
        }
    }

}
