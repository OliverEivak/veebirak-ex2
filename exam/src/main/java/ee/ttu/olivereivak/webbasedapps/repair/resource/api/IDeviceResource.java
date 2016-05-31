package ee.ttu.olivereivak.webbasedapps.repair.resource.api;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.Device;

@Path("/devices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IDeviceResource {

    @GET
    @RolesAllowed({"EMPLOYEE"})
    List<Device> getAll();

    @GET
    @Path("{deviceID}")
    @RolesAllowed({"EMPLOYEE"})
    Device get(@PathParam("deviceID") Long deviceID);

    @POST
    @RolesAllowed({"EMPLOYEE"})
    Device update(Device device);

    @GET
    @Path("search")
    @RolesAllowed({"EMPLOYEE"})
    List<Device> search(
            @QueryParam("name") String name,
            @QueryParam("model") String model,
            @QueryParam("description") String description,
            @QueryParam("manufacturer") String manufacturer,
            @QueryParam("registrationNumber") String registrationNumber,
            @QueryParam("deviceType") Long deviceType,
            @QueryParam("clientName") String clientName);

}
