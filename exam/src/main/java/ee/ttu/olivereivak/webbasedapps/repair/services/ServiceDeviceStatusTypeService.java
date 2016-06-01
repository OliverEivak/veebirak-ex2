package ee.ttu.olivereivak.webbasedapps.repair.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ee.ttu.olivereivak.webbasedapps.repair.dao.repairshop.ServiceDeviceStatusTypeDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceDeviceStatusType;

@Singleton
public class ServiceDeviceStatusTypeService {

    @Inject
    private ServiceDeviceStatusTypeDAO serviceDeviceStatusTypeDAO;

    public List<ServiceDeviceStatusType> getAll() {
        return serviceDeviceStatusTypeDAO.findAll();
    }

    public ServiceDeviceStatusType get(Long id) {
        return serviceDeviceStatusTypeDAO.findByID(id);
    }

}
