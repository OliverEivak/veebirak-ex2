package ee.ttu.olivereivak.webbasedapps.repair.resource.api;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Customer;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ICustomerResource {

    @GET
    @RolesAllowed({"EMPLOYEE"})
    List<Customer> searchByName(@QueryParam("name") String name);

}
