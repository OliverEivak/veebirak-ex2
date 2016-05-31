package ee.ttu.olivereivak.webbasedapps.repair.services;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ee.ttu.olivereivak.webbasedapps.repair.dao.ServiceOrderDAO;
import ee.ttu.olivereivak.webbasedapps.repair.dao.ServiceRequestDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.UserAccount;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceOrder;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceRequest;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceRequestStatusType;

@Singleton
public class ServiceRequestService {

    @Inject
    private ServiceRequestDAO serviceRequestDAO;

    @Inject
    private ServiceOrderDAO serviceOrderDAO;

    public List<ServiceRequest> getAll() {
        return serviceRequestDAO.findAll();
    }

    public ServiceRequest get(Long id) {
        return serviceRequestDAO.findByID(id);
    }

    public ServiceRequest update(ServiceRequest serviceRequest, UserAccount userAccount) {
        if (serviceRequest.getId() == null) {
            if (serviceRequest.getServiceRequestStatusType() == null) {
                ServiceRequestStatusType status = new ServiceRequestStatusType();
                status.setId(1L); // status = registered

                serviceRequest.setServiceRequestStatusType(status);
            }

            serviceRequest.setCreator(userAccount.getEmployee());
            serviceRequest.setCreated(Instant.now());
        } else {
            // not allowed to change creator and creation time anymore
            ServiceRequest original = serviceRequestDAO.findByID(serviceRequest.getId());
            serviceRequest.setCreator(original.getCreator());
            serviceRequest.setCreated(original.getCreated());
        }

        return serviceRequestDAO.update(serviceRequest);
    }

    public void delete(Long id) {
        ServiceRequest serviceRequest = serviceRequestDAO.findByID(id);

        // Status can not be = service order created
        if (serviceRequest.getServiceRequestStatusType() != null && serviceRequest.getServiceRequestStatusType().getId().equals(3L)) {
            throw new RuntimeException("Can not delete service request with status 'order created' (3)");
        }

        // Service request can not be used in any service order
        List<ServiceOrder> serviceOrdersWithServiceRequest = serviceOrderDAO.findByServiceRequest(serviceRequest);
        if (!serviceOrdersWithServiceRequest.isEmpty()) {
            throw new RuntimeException("Can not delete service request that is referenced in service orders");
        }

        serviceRequestDAO.remove(serviceRequest);
    }

}
