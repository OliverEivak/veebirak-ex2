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

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceType;
import ee.ttu.olivereivak.webbasedapps.repair.resource.api.IServiceTypeResource;
import ee.ttu.olivereivak.webbasedapps.repair.services.ServiceTypeService;

@Path("/serviceTypes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceTypeResource extends BaseResource implements IServiceTypeResource {

    @Inject
    private ServiceTypeService serviceTypeService;

    @GET
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public List<ServiceType> getAll() {
        return serviceTypeService.getAll();
    }

    @GET
    @Path("{serviceTypeID}")
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public ServiceType get(@PathParam("serviceTypeID") Long serviceTypeID) {
        return serviceTypeService.get(serviceTypeID);
    }

}
