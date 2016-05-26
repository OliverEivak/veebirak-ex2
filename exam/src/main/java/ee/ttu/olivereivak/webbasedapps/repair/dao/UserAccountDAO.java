package ee.ttu.olivereivak.webbasedapps.repair.dao;

import javax.persistence.TypedQuery;

import ee.ttu.olivereivak.webbasedapps.repair.entity.UserAccount;

public class UserAccountDAO extends BaseDAO<UserAccount> {

    public UserAccount findByUsername(String username) {
        TypedQuery<UserAccount> typedQuery = em().createQuery("SELECT u FROM UserAccount u WHERE u.username = :username", UserAccount.class);
        typedQuery.setParameter("username", username);

        return getSingleResult(typedQuery);
    }

}
