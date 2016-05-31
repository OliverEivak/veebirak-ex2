package ee.ttu.olivereivak.webbasedapps.repair.resource.api;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceOrder;

@Path("/serviceOrders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IServiceOrderResource {

    @GET
    @RolesAllowed({"EMPLOYEE"})
    List<ServiceOrder> getAll();

    @GET
    @Path("{serviceOrderID}")
    @RolesAllowed({"EMPLOYEE"})
    ServiceOrder get(@PathParam("serviceOrderID") Long serviceOrderID);

    @POST
    @RolesAllowed({"EMPLOYEE"})
    ServiceOrder update(ServiceOrder serviceOrder);

}
