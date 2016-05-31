package ee.ttu.olivereivak.webbasedapps.repair.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.DeviceType;
import ee.ttu.olivereivak.webbasedapps.repair.resource.api.IDeviceTypeResource;
import ee.ttu.olivereivak.webbasedapps.repair.services.DeviceTypeService;

@Path("/deviceTypes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeviceTypeResource extends BaseResource implements IDeviceTypeResource {

    @Inject
    private DeviceTypeService deviceTypeService;

    @GET
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public List<DeviceType> getAll() {
        return deviceTypeService.getAll();
    }

    @GET
    @Path("{deviceTypeID}")
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public DeviceType get(@PathParam("deviceTypeID") Long deviceTypeID) {
        return deviceTypeService.get(deviceTypeID);
    }

}
