package ee.ttu.olivereivak.webbasedapps.repair.resource.api;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/logout")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ILogoutResource {

    @POST
    @RolesAllowed({"EMPLOYEE"})
    void logout();

}
