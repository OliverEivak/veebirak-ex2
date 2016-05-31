package ee.ttu.olivereivak.webbasedapps.repair.resource;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceRequest;
import ee.ttu.olivereivak.webbasedapps.repair.resource.api.IServiceRequestResource;
import ee.ttu.olivereivak.webbasedapps.repair.services.ServiceRequestService;

@Path("/serviceRequests")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceRequestResource extends BaseResource implements IServiceRequestResource {

    @Inject
    private ServiceRequestService serviceRequestService;

    @GET
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public List<ServiceRequest> getAll() {
        return serviceRequestService.getAll();
    }

    @GET
    @Path("{serviceRequestID}")
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public ServiceRequest get(@PathParam("serviceRequestID") Long serviceRequestID) {
        return serviceRequestService.get(serviceRequestID);
    }

    @POST
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public ServiceRequest update(ServiceRequest serviceRequest) {
        return serviceRequestService.update(serviceRequest, getUserAccount());
    }
}
