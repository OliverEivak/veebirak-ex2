package ee.ttu.olivereivak.webbasedapps.repair.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.DeviceType;

@Path("/deviceTypes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IDeviceTypeResource {

    @GET
    @RolesAllowed({"EMPLOYEE"})
    List<DeviceType> getAll();

    @GET
    @Path("{deviceTypeID}")
    @RolesAllowed({"EMPLOYEE"})
    DeviceType get(@PathParam("deviceTypeID") Long deviceTypeID);

}
