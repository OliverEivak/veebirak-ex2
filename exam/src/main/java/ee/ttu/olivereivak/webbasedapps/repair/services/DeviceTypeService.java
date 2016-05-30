package ee.ttu.olivereivak.webbasedapps.repair.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ee.ttu.olivereivak.webbasedapps.repair.dao.DeviceTypeDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.DeviceType;

@Singleton
public class DeviceTypeService {

    @Inject
    private DeviceTypeDAO deviceTypeDAO;

    public List<DeviceType> getAll() {
        return deviceTypeDAO.findAll();
    }

    public DeviceType get(Long id) {
        return deviceTypeDAO.findByID(id);
    }

}
