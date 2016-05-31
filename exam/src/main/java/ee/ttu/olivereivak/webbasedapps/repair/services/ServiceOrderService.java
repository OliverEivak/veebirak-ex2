package ee.ttu.olivereivak.webbasedapps.repair.services;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

import ee.ttu.olivereivak.webbasedapps.repair.dao.ServiceOrderDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.UserAccount;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceOrder;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceOrderStatusType;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServicePart;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceRequestStatusType;

@Singleton
public class ServiceOrderService {

    @Inject
    private ServiceOrderDAO serviceOrderDAO;

    public List<ServiceOrder> getAll() {
        return serviceOrderDAO.findAll();
    }

    public ServiceOrder get(Long id) {
        return serviceOrderDAO.findByID(id);
    }

    public ServiceOrder update(ServiceOrder serviceOrder, UserAccount userAccount) {
        if (serviceOrder.getId() == null) {
            ServiceOrderStatusType status = new ServiceOrderStatusType();
            status.setId(1L); // status = registered

            serviceOrder.setServiceOrderStatusType(status);

            serviceOrder.setCreator(userAccount.getEmployee());
            serviceOrder.setCreated(Instant.now());
        } else {
            // not allowed to change creator and creation time anymore
            ServiceOrder original = serviceOrderDAO.findByID(serviceOrder.getId());
            serviceOrder.setCreator(original.getCreator());
            serviceOrder.setCreated(original.getCreated());

            serviceOrder.setUpdater(userAccount.getEmployee());
            serviceOrder.setUpdated(Instant.now());

            registerStatusChange(serviceOrder, original, userAccount);
        }

        if (serviceOrder.getServiceRequest() != null) {
            ServiceRequestStatusType serviceRequestStatusType = new ServiceRequestStatusType();
            serviceRequestStatusType.setId(3L);
            serviceOrder.getServiceRequest().setServiceRequestStatusType(serviceRequestStatusType);
        }

        registerServicePartCreation(serviceOrder, userAccount);

        return serviceOrderDAO.update(serviceOrder);
    }

    private void registerStatusChange(ServiceOrder serviceOrder, ServiceOrder original, UserAccount userAccount) {
        Long oldStatus = null;
        Long newStatus = null;

        if (original.getServiceOrderStatusType() != null) {
            oldStatus = original.getServiceOrderStatusType().getId();
        }

        if (serviceOrder.getServiceOrderStatusType() != null) {
            newStatus = serviceOrder.getServiceOrderStatusType().getId();
        }

        if (!Objects.equals(oldStatus, newStatus)) {
            serviceOrder.setStatusChanger(userAccount.getEmployee());
            serviceOrder.setStatusChanged(Instant.now());
        }
    }

    private void registerServicePartCreation(ServiceOrder serviceOrder, UserAccount userAccount) {
        if (serviceOrder.getServiceParts() != null) {
            for (ServicePart servicePart : serviceOrder.getServiceParts()) {
                if (servicePart.getId() == null) {
                    servicePart.setCreated(Instant.now());
                    servicePart.setCreator(userAccount.getEmployee());
                }
            }
        }
    }

}
