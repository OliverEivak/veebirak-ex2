package ee.ttu.olivereivak.veebirakex2.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import ee.ttu.olivereivak.veebirakex2.entity.Star;

public class StarDAO extends BaseDAO<Star> {

    public Star findByID(Long id) {
        TypedQuery<Star> typedQuery = em().createQuery("SELECT s FROM Star s WHERE s.id = :id", Star.class);
        typedQuery.setParameter("id", id);

        return getSingleResult(typedQuery);
    }

    public List<Star> findAll() {
        TypedQuery<Star> typedQuery = em().createQuery("SELECT s FROM Star s", Star.class);
        return typedQuery.getResultList();
    }

}
