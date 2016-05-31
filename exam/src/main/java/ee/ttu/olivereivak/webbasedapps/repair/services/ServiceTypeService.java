package ee.ttu.olivereivak.webbasedapps.repair.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ee.ttu.olivereivak.webbasedapps.repair.dao.ServiceTypeDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceType;

@Singleton
public class ServiceTypeService {

    @Inject
    private ServiceTypeDAO serviceTypeDAO;

    public List<ServiceType> getAll() {
        return serviceTypeDAO.findAll();
    }

    public ServiceType get(Long id) {
        return serviceTypeDAO.findByID(id);
    }

}
