package ee.ttu.olivereivak.webbasedapps.repair.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceType;

public class ServiceTypeDAO extends BaseDAO<ServiceType> {

    public List<ServiceType> findAll() {
        TypedQuery<ServiceType> typedQuery = em().createQuery("SELECT st FROM ServiceType st", ServiceType.class);
        return typedQuery.getResultList();
    }

    public ServiceType findByID(Long id) {
        TypedQuery<ServiceType> typedQuery = em().createQuery("SELECT s FROM ServiceType s WHERE s.id = :id", ServiceType.class);
        typedQuery.setParameter("id", id);
        return getSingleResult(typedQuery);
    }

}
