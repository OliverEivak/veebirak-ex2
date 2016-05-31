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

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceOrder;
import ee.ttu.olivereivak.webbasedapps.repair.resource.api.IServiceOrderResource;
import ee.ttu.olivereivak.webbasedapps.repair.services.ServiceOrderService;

@Path("/serviceOrders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServiceOrderResource extends BaseResource implements IServiceOrderResource {

    @Inject
    private ServiceOrderService serviceOrderService;

    @GET
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public List<ServiceOrder> getAll() {
        return serviceOrderService.getAll();
    }

    @GET
    @Path("{serviceOrderID}")
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public ServiceOrder get(@PathParam("serviceOrderID") Long serviceOrderID) {
        return serviceOrderService.get(serviceOrderID);
    }

    @POST
    @RolesAllowed({"EMPLOYEE"})
    @Transactional
    public ServiceOrder update(ServiceOrder serviceOrder) {
        return serviceOrderService.update(serviceOrder, getUserAccount());
    }
}
