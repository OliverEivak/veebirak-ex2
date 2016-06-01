package ee.ttu.olivereivak.webbasedapps.repair.resource;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import ee.ttu.olivereivak.webbasedapps.repair.entity.Authentication;
import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Customer;
import ee.ttu.olivereivak.webbasedapps.repair.resource.api.ICustomerResource;
import ee.ttu.olivereivak.webbasedapps.repair.test.IntegrationTestBase;

public class CustomerResourceITest extends IntegrationTestBase<ICustomerResource> {

    @Test
    public void findByName() {
        Authentication authentication = login("juhan", "test");
        ICustomerResource client = getClientWithAuthentication(authentication);

        List<Customer> customers = client.searchByName("a");
        assertEquals(2, customers.size());
    }

}
