package ee.ttu.olivereivak.webbasedapps.repair.dao;

import javax.persistence.TypedQuery;

import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Customer;

public class CustomerDAO extends BaseDAO<Customer>{

    public Customer findByID(Long id) {
        TypedQuery<Customer> typedQuery = em().createQuery("SELECT c FROM Customer c WHERE c.id = :id", Customer.class);
        typedQuery.setParameter("id", id);

        return getSingleResult(typedQuery);
    }

}
