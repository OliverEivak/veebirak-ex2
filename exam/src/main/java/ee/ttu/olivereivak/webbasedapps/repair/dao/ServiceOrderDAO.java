package ee.ttu.olivereivak.webbasedapps.repair.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceOrder;

public class ServiceOrderDAO extends BaseDAO<ServiceOrder> {

    public List<ServiceOrder> findAll() {
        TypedQuery<ServiceOrder> typedQuery = em().createQuery("SELECT so FROM ServiceOrder so", ServiceOrder.class);
        return typedQuery.getResultList();
    }

    public ServiceOrder findByID(Long id) {
        TypedQuery<ServiceOrder> typedQuery = em().createQuery("SELECT so FROM ServiceOrder so WHERE so.id = :id", ServiceOrder.class);
        typedQuery.setParameter("id", id);
        return getSingleResult(typedQuery);
    }
}
