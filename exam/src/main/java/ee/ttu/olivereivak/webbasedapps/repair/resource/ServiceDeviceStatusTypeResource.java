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

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceDeviceStatusType;
import ee.ttu.olivereivak.webbasedapps.repair.resource.api.IServiceDeviceStatusTypeResource;
import ee.ttu.olivereivak.webbasedapps.repair.services.ServiceDeviceStatusTypeService;

@Path("/serviceDeviceStatusTypes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceDeviceStatusTypeResource extends BaseResource implements IServiceDeviceStatusTypeResource {

    @Inject
    private ServiceDeviceStatusTypeService serviceDeviceStatusTypeService;

    @GET
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public List<ServiceDeviceStatusType> getAll() {
        return serviceDeviceStatusTypeService.getAll();
    }

    @GET
    @Path("{serviceDeviceStatusTypeID}")
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public ServiceDeviceStatusType get(@PathParam("serviceDeviceStatusTypeID") Long serviceDeviceStatusTypeID) {
        return serviceDeviceStatusTypeService.get(serviceDeviceStatusTypeID);
    }

}
