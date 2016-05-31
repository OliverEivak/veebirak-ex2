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

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceActionStatusType;
import ee.ttu.olivereivak.webbasedapps.repair.resource.api.IServiceActionStatusTypeResource;
import ee.ttu.olivereivak.webbasedapps.repair.services.ServiceActionStatusTypeService;

@Path("/serviceActionStatusTypes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceActionStatusTypeResource extends BaseResource implements IServiceActionStatusTypeResource {

    @Inject
    private ServiceActionStatusTypeService serviceActionStatusTypeService;

    @GET
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public List<ServiceActionStatusType> getAll() {
        return serviceActionStatusTypeService.getAll();
    }

    @GET
    @Path("{serviceActionStatusTypeID}")
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public ServiceActionStatusType get(@PathParam("serviceActionStatusTypeID") Long serviceActionStatusTypeID) {
        return serviceActionStatusTypeService.get(serviceActionStatusTypeID);
    }

}
