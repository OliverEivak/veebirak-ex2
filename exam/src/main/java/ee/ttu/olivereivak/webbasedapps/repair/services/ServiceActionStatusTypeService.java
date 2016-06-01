package ee.ttu.olivereivak.webbasedapps.repair.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ee.ttu.olivereivak.webbasedapps.repair.dao.repairshop.ServiceActionStatusTypeDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceActionStatusType;

@Singleton
public class ServiceActionStatusTypeService {

    @Inject
    private ServiceActionStatusTypeDAO serviceActionStatusTypeDAO;

    public List<ServiceActionStatusType> getAll() {
        return serviceActionStatusTypeDAO.findAll();
    }

    public ServiceActionStatusType get(Long id) {
        return serviceActionStatusTypeDAO.findByID(id);
    }

}
