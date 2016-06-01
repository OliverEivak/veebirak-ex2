package ee.ttu.olivereivak.webbasedapps.repair.dao.repairshop;

import java.util.List;

import javax.persistence.TypedQuery;

import ee.ttu.olivereivak.webbasedapps.repair.dao.BaseDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceOrder;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceRequest;

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

    public List<ServiceOrder> findByServiceRequest(ServiceRequest serviceRequest) {
        TypedQuery<ServiceOrder> typedQuery = em().createQuery("SELECT so FROM ServiceOrder so WHERE so.serviceRequest.id = :id", ServiceOrder.class);
        typedQuery.setParameter("id", serviceRequest.getId());
        return typedQuery.getResultList();
    }
}
