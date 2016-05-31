package ee.ttu.olivereivak.webbasedapps.repair.resource;

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

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.Device;
import ee.ttu.olivereivak.webbasedapps.repair.entity.search.DeviceSearchQuery;
import ee.ttu.olivereivak.webbasedapps.repair.resource.api.IDeviceResource;
import ee.ttu.olivereivak.webbasedapps.repair.services.DeviceService;

@Path("/devices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeviceResource extends BaseResource implements IDeviceResource {

    @Inject
    private DeviceService deviceService;

    @GET
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public List<Device> getAll() {
        return deviceService.getAll();
    }

    @GET
    @Path("{deviceID}")
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public Device get(@PathParam("deviceID") Long deviceID) {
        return deviceService.get(deviceID);
    }

    @POST
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public Device update(Device device) {
        return deviceService.update(device, getUserAccount());
    }

    @GET
    @Path("search")
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public List<Device> search(
            @QueryParam("name") String name,
            @QueryParam("model") String model,
            @QueryParam("description") String description,
            @QueryParam("manufacturer") String manufacturer,
            @QueryParam("registrationNumber") String registrationNumber,
            @QueryParam("deviceType") Long deviceType,
            @QueryParam("clientName") String clientName) {

        DeviceSearchQuery query = new DeviceSearchQuery();
        query.setName(name);
        query.setModel(model);
        query.setDescription(description);
        query.setManufacturer(manufacturer);
        query.setRegistrationNumber(registrationNumber);
        query.setDeviceType(deviceType);
        query.setClientName(clientName);

        return deviceService.search(query);
    }
}
