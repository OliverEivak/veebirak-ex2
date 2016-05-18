package ee.ttu.olivereivak.veebirakex2.dao;

import javax.persistence.TypedQuery;

import ee.ttu.olivereivak.veebirakex2.entity.User;

public class UserDAO extends BaseDAO<User> {

    public User findByUsername(String username) {
        TypedQuery<User> typedQuery = em().createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        typedQuery.setParameter("username", username);

        return getSingleResult(typedQuery);
    }

}
