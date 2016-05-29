package ee.ttu.olivereivak.webbasedapps.repair.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ee.ttu.olivereivak.webbasedapps.repair.dao.ServiceRequestDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceRequest;

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

    public ServiceRequest update(ServiceRequest serviceRequest) {
        return serviceRequestDAO.update(serviceRequest);
    }

}
