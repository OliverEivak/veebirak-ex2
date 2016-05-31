package ee.ttu.olivereivak.webbasedapps.repair.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import ee.ttu.olivereivak.webbasedapps.repair.entity.Authentication;
import ee.ttu.olivereivak.webbasedapps.repair.resource.api.ILoginResource;
import ee.ttu.olivereivak.webbasedapps.repair.test.TestBase;

public class LoginResourceTest extends TestBase {

	@Test
	public void login() throws Exception {
		String username = "juhan";
		String password = "test";

		ILoginResource.LoginForm loginForm = new ILoginResource.LoginForm(username, password);

		Authentication authentication = instance(LoginResource.class).login(loginForm);
		assertNotNull(authentication.getToken());
		assertEquals(username, authentication.getUserAccount().getUsername());
	}

	@Test
	public void loginWrongPassword() throws Exception {
		String username = "juhan";
		String password = "wrong-pw";

		ILoginResource.LoginForm loginForm = new ILoginResource.LoginForm(username, password);

		Authentication authentication = instance(LoginResource.class).login(loginForm);
		assertNull(authentication);
	}

}
