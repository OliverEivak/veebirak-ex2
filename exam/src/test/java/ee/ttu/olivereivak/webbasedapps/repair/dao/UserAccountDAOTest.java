package ee.ttu.olivereivak.webbasedapps.repair.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ee.ttu.olivereivak.webbasedapps.repair.entity.SubjectType;
import ee.ttu.olivereivak.webbasedapps.repair.entity.UserAccount;
import ee.ttu.olivereivak.webbasedapps.repair.test.DAOTestBase;

public class UserAccountDAOTest extends DAOTestBase {

    @Test
    public void find() {
        UserAccountDAO userAccountDAO = instance(UserAccountDAO.class);

        UserAccount userAccount = userAccountDAO.findByUsername("juhan");
        assertEquals(Long.valueOf(1), userAccount.getId());
        assertEquals(SubjectType.EMPLOYEE, userAccount.getSubjectType());

        assertEquals(Long.valueOf(1), userAccount.getEmployee().getId());
        assertEquals("Yhendatud Systeemid", userAccount.getEmployee().getEnterprise().getName());
    }

}
