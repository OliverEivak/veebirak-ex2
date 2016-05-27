package ee.ttu.olivereivak.webbasedapps.repair.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.time.LocalDate;

import org.junit.Test;

import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Customer;
import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Person;
import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Subject;
import ee.ttu.olivereivak.webbasedapps.repair.test.DAOTestBase;

public class CustomerDAOTest extends DAOTestBase {

    @Test
    public void find() {
        CustomerDAO customerDAO = instance(CustomerDAO.class);

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

}
