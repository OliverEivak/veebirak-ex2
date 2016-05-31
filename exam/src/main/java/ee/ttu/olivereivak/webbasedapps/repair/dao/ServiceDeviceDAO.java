package ee.ttu.olivereivak.webbasedapps.repair.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceDevice;

public class ServiceDeviceDAO extends BaseDAO<ServiceDevice> {

    public List<ServiceDevice> findAll() {
        TypedQuery<ServiceDevice> typedQuery = em().createQuery("SELECT s FROM ServiceDevice s", ServiceDevice.class);
        return typedQuery.getResultList();
    }

    public ServiceDevice findByID(Long id) {
        TypedQuery<ServiceDevice> typedQuery = em().createQuery("SELECT s FROM ServiceDevice s WHERE s.id = :id", ServiceDevice.class);
        typedQuery.setParameter("id", id);
        return getSingleResult(typedQuery);
    }
}
