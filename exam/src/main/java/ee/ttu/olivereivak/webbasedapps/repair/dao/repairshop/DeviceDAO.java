package ee.ttu.olivereivak.webbasedapps.repair.dao.repairshop;

import static org.apache.commons.lang.StringUtils.isEmpty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ee.ttu.olivereivak.webbasedapps.repair.dao.BaseDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.Device;
import ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop.DeviceType;
import ee.ttu.olivereivak.webbasedapps.repair.entity.search.DeviceSearchQuery;

public class DeviceDAO extends BaseDAO<Device> {

    public List<Device> findAll() {
        TypedQuery<Device> typedQuery = em().createQuery("SELECT d FROM Device d", Device.class);
        return typedQuery.getResultList();
    }

    public Device findByID(Long id) {
        TypedQuery<Device> typedQuery = em().createQuery("SELECT d FROM Device d WHERE d.id = :id", Device.class);
        typedQuery.setParameter("id", id);
        return getSingleResult(typedQuery);
    }

    /**
     *  https://stackoverflow.com/questions/4014390/jpa-criteria-api-like-equal-problem
     *
     *  TODO: search by client name (create a monstrous join)
     */
    public List<Device> search(DeviceSearchQuery query) {
        CriteriaBuilder criteriaBuilder = em().getCriteriaBuilder();
        CriteriaQuery<Device> criteriaQuery = criteriaBuilder.createQuery(Device.class);
        Root<Device> deviceRoot = criteriaQuery.from(Device.class);
        Join<Device, DeviceType> joinDeviceType = deviceRoot.join("deviceType");

        List<Predicate> criteria = new ArrayList<>();
        Map<String, String> queryMap = getQueryMap(query);

        for (Map.Entry<String, String> entry : queryMap.entrySet()) {
            if (!isEmpty(entry.getValue())) {
                Predicate condition = criteriaBuilder
                        .like(
                                criteriaBuilder.lower(deviceRoot.<String>get(entry.getKey())),
                                "%" + entry.getValue().toLowerCase() + "%"
                        );
                criteria.add(condition);
            }
        }

        if (query.getDeviceType() != null) {
            Predicate condition = criteriaBuilder.equal(joinDeviceType.<Long>get("id"), query.getDeviceType());
            criteria.add(condition);
        }

        if (criteria.size() == 0) {
            return new ArrayList<>();
        } else if (criteria.size() == 1) {
            criteriaQuery.where(criteria.get(0));
        } else {
            criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[criteria.size()])));
        }

        return em().createQuery(criteriaQuery).getResultList();
    }

    private Map<String, String> getQueryMap(DeviceSearchQuery query) {
        Map<String, String> fields = new HashMap<>();
        fields.put("name", query.getName());
        fields.put("model", query.getModel());
        fields.put("description", query.getDescription());
        fields.put("manufacturer", query.getManufacturer());
        fields.put("registrationNumber", query.getRegistrationNumber());
        return fields;
    }

}
