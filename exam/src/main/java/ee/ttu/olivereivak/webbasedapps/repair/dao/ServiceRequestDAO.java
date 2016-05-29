package ee.ttu.olivereivak.webbasedapps.repair.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceRequest;

public class ServiceRequestDAO extends BaseDAO<ServiceRequest> {

    public List<ServiceRequest> findAll() {
        TypedQuery<ServiceRequest> typedQuery = em().createQuery("SELECT sr FROM ServiceRequest sr", ServiceRequest.class);
        return typedQuery.getResultList();
    }

    public ServiceRequest findByID(Long id) {
        TypedQuery<ServiceRequest> typedQuery = em().createQuery("SELECT sr FROM ServiceRequest sr WHERE sr.id = :id", ServiceRequest.class);
        typedQuery.setParameter("id", id);
        return getSingleResult(typedQuery);
    }
}
