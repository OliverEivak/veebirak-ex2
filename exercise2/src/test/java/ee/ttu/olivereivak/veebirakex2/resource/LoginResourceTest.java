package ee.ttu.olivereivak.veebirakex2.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import ee.ttu.olivereivak.veebirakex2.entity.Authentication;
import ee.ttu.olivereivak.veebirakex2.test.TestBase;

public class LoginResourceTest extends TestBase {

	@Test
	public void login() throws Exception {
		String username = "jane";
		String password = "best";

		ILoginResource.LoginForm loginForm = new ILoginResource.LoginForm(username, password);

		Authentication authentication = instance(LoginResource.class).login(loginForm);
		assertNotNull(authentication.getToken());
		assertEquals(username, authentication.getUser().getUsername());
	}

	@Test
	public void loginWrongPassword() throws Exception {
		String username = "jane";
		String password = "wrong-pw";

		ILoginResource.LoginForm loginForm = new ILoginResource.LoginForm(username, password);

		Authentication authentication = instance(LoginResource.class).login(loginForm);
		assertNull(authentication);
	}

}
