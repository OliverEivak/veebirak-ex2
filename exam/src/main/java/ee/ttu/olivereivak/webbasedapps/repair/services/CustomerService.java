package ee.ttu.olivereivak.webbasedapps.repair.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ee.ttu.olivereivak.webbasedapps.repair.dao.CustomerDAO;
import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Customer;

@Singleton
public class CustomerService {

    @Inject
    private CustomerDAO customerDAO;

    public List<Customer> searchByName(String name) {
        return customerDAO.findByName(name);
    }

}
