package ee.ttu.olivereivak.webbasedapps.repair.resource.api;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceDeviceStatusType;

@Path("/serviceDeviceStatusTypes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IServiceDeviceStatusTypeResource {

    @GET
    @RolesAllowed({"EMPLOYEE"})
    List<ServiceDeviceStatusType> getAll();

    @GET
    @Path("{serviceDeviceStatusTypeID}")
    @RolesAllowed({"EMPLOYEE"})
    ServiceDeviceStatusType get(@PathParam("serviceDeviceStatusTypeID") Long serviceDeviceStatusTypeID);

}
