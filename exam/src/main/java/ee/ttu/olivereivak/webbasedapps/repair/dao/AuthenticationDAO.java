package ee.ttu.olivereivak.webbasedapps.repair.dao;

import javax.persistence.TypedQuery;

import ee.ttu.olivereivak.webbasedapps.repair.entity.Authentication;

public class AuthenticationDAO extends BaseDAO<Authentication> {

    public Authentication findByToken(String token) {
        TypedQuery<Authentication> typedQuery = em().createQuery("SELECT a FROM Authentication a WHERE a.token = :token", Authentication.class);
        typedQuery.setParameter("token", token);

        return getSingleResult(typedQuery);
    }

}
