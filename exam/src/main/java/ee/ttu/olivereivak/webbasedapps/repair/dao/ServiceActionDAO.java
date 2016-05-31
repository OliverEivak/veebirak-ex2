package ee.ttu.olivereivak.webbasedapps.repair.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceAction;

public class ServiceActionDAO extends BaseDAO<ServiceAction> {

    public List<ServiceAction> findAll() {
        TypedQuery<ServiceAction> typedQuery = em().createQuery("SELECT s FROM ServiceAction s", ServiceAction.class);
        return typedQuery.getResultList();
    }

    public ServiceAction findByID(Long id) {
        TypedQuery<ServiceAction> typedQuery = em().createQuery("SELECT s FROM ServiceAction s WHERE s.id = :id", ServiceAction.class);
        typedQuery.setParameter("id", id);
        return getSingleResult(typedQuery);
    }
}
