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

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceRequest;

@Path("/serviceRequests")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IServiceRequestResource {

    @GET
    @RolesAllowed({"EMPLOYEE"})
    List<ServiceRequest> getAll();

    @GET
    @Path("{serviceRequestID}")
    @RolesAllowed({"EMPLOYEE"})
    ServiceRequest get(@PathParam("serviceRequestID") Long serviceRequestID);

    @POST
    @RolesAllowed({"EMPLOYEE"})
    ServiceRequest update(ServiceRequest serviceRequest);

}
