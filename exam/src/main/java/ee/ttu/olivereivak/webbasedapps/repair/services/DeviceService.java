package ee.ttu.olivereivak.webbasedapps.repair.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ee.ttu.olivereivak.webbasedapps.repair.dao.repairshop.DeviceDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.UserAccount;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.Device;
import ee.ttu.olivereivak.webbasedapps.repair.entity.search.DeviceSearchQuery;

@Singleton
public class DeviceService {

    @Inject
    private DeviceDAO deviceDAO;

    public List<Device> getAll() {
        return deviceDAO.findAll();
    }

    public Device get(Long id) {
        return deviceDAO.findByID(id);
    }

    public Device update(Device device, UserAccount userAccount) {
        return deviceDAO.update(device);
    }

    public List<Device> search(DeviceSearchQuery query) {
        return deviceDAO.search(query);
    }

}
