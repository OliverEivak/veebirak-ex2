package ee.ttu.olivereivak.webbasedapps.repair.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Customer;
import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Person;
import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Subject;
import ee.ttu.olivereivak.webbasedapps.repair.test.DAOTestBase;

public class CustomerDAOTest extends DAOTestBase {

    private CustomerDAO customerDAO;

    @Before
    public void setUp() {
        customerDAO = instance(CustomerDAO.class);
    }

    @Test
    public void findbyID() {
        Customer customer = customerDAO.findByID(1L);
        Subject subject = customer.getSubject();

        assertTrue(subject instanceof Person);
        Person person = (Person) subject;

        assertEquals("Kaarel", person.getFirstName());
        assertEquals("Klient", person.getLastName());
        assertEquals(LocalDate.of(1970, 11, 11), person.getBirthDate());
        assertEquals("5555555555XXXX", person.getSsn());
        assertTrue(person.getCreated().isBefore(Instant.now()));
    }

    @Test
    public void findByName() {
        List<Customer> customers = customerDAO.findByName("Kaarel");
        assertEquals(1, customers.size());
        assertEquals(Long.valueOf(4), customers.get(0).getSubject().getId());

        customers = customerDAO.findByName("Toru");
        assertEquals(1, customers.size());
        assertEquals(Long.valueOf(2), customers.get(0).getSubject().getId());

        customers = customerDAO.findByName("pood");
        assertEquals(1, customers.size());
        assertEquals(Long.valueOf(2), customers.get(0).getSubject().getId());
    }

}
