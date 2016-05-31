package ee.ttu.olivereivak.webbasedapps.repair.resource.api;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceType;

@Path("/serviceTypes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IServiceTypeResource {

    @GET
    @RolesAllowed({"EMPLOYEE"})
    List<ServiceType> getAll();

    @GET
    @Path("{serviceTypeID}")
    @RolesAllowed({"EMPLOYEE"})
    ServiceType get(@PathParam("serviceTypeID") Long serviceTypeID);

}
