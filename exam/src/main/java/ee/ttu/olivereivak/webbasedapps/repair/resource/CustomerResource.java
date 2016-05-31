package ee.ttu.olivereivak.webbasedapps.repair.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Customer;
import ee.ttu.olivereivak.webbasedapps.repair.resource.api.ICustomerResource;
import ee.ttu.olivereivak.webbasedapps.repair.services.CustomerService;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource extends BaseResource implements ICustomerResource {

    @Inject
    private CustomerService customerService;

    @GET
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public List<Customer> searchByName(@QueryParam("name") String name) {
        return customerService.searchByName(name);
    }
}
