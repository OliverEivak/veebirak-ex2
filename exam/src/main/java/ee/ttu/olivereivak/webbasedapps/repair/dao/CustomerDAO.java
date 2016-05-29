package ee.ttu.olivereivak.webbasedapps.repair.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Customer;

public class CustomerDAO extends BaseDAO<Customer> {

    public List<Customer> findAll() {
        TypedQuery<Customer> typedQuery = em().createQuery("SELECT c FROM Customer c", Customer.class);
        return typedQuery.getResultList();
    }

    public Customer findByID(Long id) {
        TypedQuery<Customer> typedQuery = em().createQuery("SELECT c FROM Customer c WHERE c.id = :id", Customer.class);
        typedQuery.setParameter("id", id);

        return getSingleResult(typedQuery);
    }

    public List<Customer> findByName(String name) {
        return findAll().stream().filter(customer -> {
            return customer.getSubject().getName().toLowerCase().contains(name.toLowerCase());
        }).collect(Collectors.toList());
    }

}
