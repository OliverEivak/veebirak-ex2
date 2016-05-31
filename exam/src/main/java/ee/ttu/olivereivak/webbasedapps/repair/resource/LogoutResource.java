package ee.ttu.olivereivak.webbasedapps.repair.resource;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import ee.ttu.olivereivak.webbasedapps.repair.resource.api.ILogoutResource;
import ee.ttu.olivereivak.webbasedapps.repair.services.LogoutService;

@Path("/logout")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LogoutResource extends BaseResource implements ILogoutResource {

    @Inject
    private LogoutService logoutService;

    @POST
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public void logout() {
        logoutService.logout(getAuthentication());
    }

}
