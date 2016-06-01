package ee.ttu.olivereivak.webbasedapps.repair.services;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

import ee.ttu.olivereivak.webbasedapps.repair.dao.repairshop.ServiceActionDAO;
import ee.ttu.olivereivak.webbasedapps.repair.dao.repairshop.ServiceDeviceDAO;
import ee.ttu.olivereivak.webbasedapps.repair.dao.repairshop.ServiceOrderDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.UserAccount;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceAction;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceDevice;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceOrder;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceOrderStatusType;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServicePart;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceRequestStatusType;

@Singleton
public class ServiceOrderService {

    @Inject
    private ServiceOrderDAO serviceOrderDAO;

    @Inject
    private ServiceActionDAO serviceActionDAO;

    @Inject
    private ServiceDeviceDAO serviceDeviceDAO;

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

        updateServiceRequestStatus(serviceOrder);

        registerServicePartCreation(serviceOrder, userAccount);
        registerServiceActionCreation(serviceOrder, userAccount);
        registerServiceDeviceStatusChange(serviceOrder);

        calculateTotalPrice(serviceOrder);

        return serviceOrderDAO.update(serviceOrder);
    }

    private void updateServiceRequestStatus(ServiceOrder serviceOrder) {
        if (serviceOrder.getServiceRequest() != null) {
            ServiceRequestStatusType serviceRequestStatusType = new ServiceRequestStatusType();
            serviceRequestStatusType.setId(3L);
            serviceOrder.getServiceRequest().setServiceRequestStatusType(serviceRequestStatusType);
        }
    }

    private void registerStatusChange(ServiceOrder serviceOrder, ServiceOrder original, UserAccount userAccount) {
        Long oldStatus = null;
        Long newStatus = null;

        if (original != null && original.getServiceOrderStatusType() != null) {
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

    private void registerServiceActionCreation(ServiceOrder serviceOrder, UserAccount userAccount) {
        if (serviceOrder.getServiceActions() != null) {
            for (ServiceAction serviceAction : serviceOrder.getServiceActions()) {
                if (serviceAction.getId() == null) {
                    serviceAction.setCreated(Instant.now());
                    serviceAction.setCreator(userAccount.getEmployee());
                } else {
                    registerPriceChange(serviceAction);
                }
            }
        }
    }

    private void registerPriceChange(ServiceAction serviceAction) {
        ServiceAction originalServiceAction = serviceActionDAO.findByID(serviceAction.getId());

        BigDecimal oldPrice = null;

        if (originalServiceAction != null) {
            oldPrice = originalServiceAction.getPrice();
        }

        if (!Objects.equals(oldPrice, serviceAction.getPrice())) {
            serviceAction.setPriceChanged(Instant.now());
        }
    }

    private void registerServiceDeviceStatusChange(ServiceOrder serviceOrder) {
        if (serviceOrder.getServiceDevices() != null) {
            for (ServiceDevice serviceDevice : serviceOrder.getServiceDevices()) {
                ServiceDevice originalServiceDevice = serviceDeviceDAO.findByID(serviceDevice.getId());

                Long oldStatus = null;
                Long newStatus = null;

                if (originalServiceDevice != null && originalServiceDevice.getServiceDeviceStatusType() != null) {
                    oldStatus = originalServiceDevice.getServiceDeviceStatusType().getId();
                }

                if (serviceDevice.getServiceDeviceStatusType() != null) {
                    newStatus = serviceDevice.getServiceDeviceStatusType().getId();
                }

                if (!Objects.equals(oldStatus, newStatus)) {
                    serviceDevice.setStatusChanged(Instant.now());
                }
            }
        }
    }

    private void calculateTotalPrice(ServiceOrder serviceOrder) {
        serviceOrder.setTotalPrice(BigDecimal.ZERO);

        for (ServiceAction serviceAction : serviceOrder.getServiceActions()) {
            serviceOrder.setTotalPrice(serviceOrder.getTotalPrice().add(serviceAction.getAmount().multiply(serviceAction.getPrice())));
        }

        for (ServicePart servicePart : serviceOrder.getServiceParts()) {
            serviceOrder.setTotalPrice(serviceOrder.getTotalPrice().add(BigDecimal.valueOf(servicePart.getCount()).multiply(servicePart.getPrice())));
        }
    }

}
