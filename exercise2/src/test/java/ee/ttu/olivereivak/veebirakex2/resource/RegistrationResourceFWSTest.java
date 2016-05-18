package ee.ttu.olivereivak.veebirakex2.resource;

import static org.assertj.core.api.StrictAssertions.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.InternalServerErrorException;

import org.junit.Test;

import ee.ttu.olivereivak.veebirakex2.entity.Authentication;
import ee.ttu.olivereivak.veebirakex2.test.FullWebStackTestBase;

public class RegistrationResourceFWSTest extends FullWebStackTestBase<IRegistrationResource> {

    @Test
    public void register() throws Exception {
        String username = "totally-new-user";
        String password = "abc";

        IRegistrationResource client = getClient(IRegistrationResource.class);

        IRegistrationResource.RegistrationForm registrationForm = new IRegistrationResource.RegistrationForm(username, password);

        Authentication authentication = client.register(registrationForm);

        assertNotNull(authentication.getToken());
        assertNotNull(authentication.getId());
        assertNotNull(authentication.getUser());
        assertEquals(username, authentication.getUser().getUsername());
    }

    @Test
    public void registerExistingUsername() throws Exception {
        String username = "john";
        String password = "bla";

        IRegistrationResource client = getClient(IRegistrationResource.class);

        IRegistrationResource.RegistrationForm registrationForm = new IRegistrationResource.RegistrationForm(username, password);

        try {
            client.register(registrationForm);
            fail("Exception expected");
        } catch (InternalServerErrorException e) {

        }
    }

}
