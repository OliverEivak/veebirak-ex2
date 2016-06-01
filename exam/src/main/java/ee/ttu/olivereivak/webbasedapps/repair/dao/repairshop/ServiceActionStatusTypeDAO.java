package ee.ttu.olivereivak.webbasedapps.repair.dao.repairshop;

import java.util.List;

import javax.persistence.TypedQuery;

import ee.ttu.olivereivak.webbasedapps.repair.dao.BaseDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.ServiceActionStatusType;

public class ServiceActionStatusTypeDAO extends BaseDAO<ServiceActionStatusType> {

    public List<ServiceActionStatusType> findAll() {
        TypedQuery<ServiceActionStatusType> typedQuery = em().createQuery("SELECT s FROM ServiceActionStatusType s", ServiceActionStatusType.class);
        return typedQuery.getResultList();
    }

    public ServiceActionStatusType findByID(Long id) {
        TypedQuery<ServiceActionStatusType> typedQuery = em().createQuery("SELECT s FROM ServiceActionStatusType s WHERE s.id = :id", ServiceActionStatusType.class);
        typedQuery.setParameter("id", id);
        return getSingleResult(typedQuery);
    }

}
