package ee.ttu.olivereivak.webbasedapps.repair.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceDeviceStatusType;

public class ServiceDeviceStatusTypeDAO extends BaseDAO<ServiceDeviceStatusType> {

    public List<ServiceDeviceStatusType> findAll() {
        TypedQuery<ServiceDeviceStatusType> typedQuery = em().createQuery("SELECT s FROM ServiceDeviceStatusType s", ServiceDeviceStatusType.class);
        return typedQuery.getResultList();
    }

    public ServiceDeviceStatusType findByID(Long id) {
        TypedQuery<ServiceDeviceStatusType> typedQuery = em().createQuery("SELECT s FROM ServiceDeviceStatusType s WHERE s.id = :id", ServiceDeviceStatusType.class);
        typedQuery.setParameter("id", id);
        return getSingleResult(typedQuery);
    }

}
