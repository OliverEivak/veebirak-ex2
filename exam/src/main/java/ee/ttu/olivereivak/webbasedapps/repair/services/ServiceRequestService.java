package ee.ttu.olivereivak.webbasedapps.repair.services;

import java.time.Instant;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ee.ttu.olivereivak.webbasedapps.repair.dao.ServiceRequestDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.UserAccount;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceRequest;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceRequestStatusType;

@Singleton
public class ServiceRequestService {

    @Inject
    private ServiceRequestDAO serviceRequestDAO;

    public List<ServiceRequest> getAll() {
        return serviceRequestDAO.findAll();
    }

    public ServiceRequest get(Long id) {
        return serviceRequestDAO.findByID(id);
    }

    public ServiceRequest update(ServiceRequest serviceRequest, UserAccount userAccount) {
        if (serviceRequest.getId() == null) {
            ServiceRequestStatusType status = new ServiceRequestStatusType();
            status.setId(1L); // status = registered

            serviceRequest.setServiceRequestStatusType(status);
            serviceRequest.setCreated(Instant.now());
        }

        serviceRequest.setCreator(userAccount.getEmployee());

        return serviceRequestDAO.update(serviceRequest);
    }

}
