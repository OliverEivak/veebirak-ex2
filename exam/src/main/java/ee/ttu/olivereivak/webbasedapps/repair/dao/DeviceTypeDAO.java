package ee.ttu.olivereivak.webbasedapps.repair.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.DeviceType;

public class DeviceTypeDAO extends BaseDAO<DeviceType> {

    public List<DeviceType> findAll() {
        TypedQuery<DeviceType> typedQuery = em().createQuery("SELECT dt FROM DeviceType dt", DeviceType.class);
        return typedQuery.getResultList();
    }

    public DeviceType findByID(Long id) {
        TypedQuery<DeviceType> typedQuery = em().createQuery("SELECT dt FROM DeviceType dt WHERE dt.id = :id", DeviceType.class);
        typedQuery.setParameter("id", id);
        return getSingleResult(typedQuery);
    }

}
