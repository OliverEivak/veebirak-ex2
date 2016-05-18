package ee.ttu.olivereivak.veebirakex2.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import ee.ttu.olivereivak.veebirakex2.entity.Star;
import ee.ttu.olivereivak.veebirakex2.test.DAOTestBase;

public class StarDAOTest extends DAOTestBase {

    @Test
    public void getByID() {
        StarDAO starDAO = instance(StarDAO.class);
        Star star = starDAO.findByID(1L);

        assertEquals(Long.valueOf(1L), star.getId());
        assertEquals("Sirius", star.getCommonName());
        assertTrue(BigDecimal.valueOf(8.6).compareTo(star.getDistanceInLightYears()) == 0);
        assertEquals("Sirius is the brightest star in the sky, after the Sun", star.getDescription());
    }

    @Test
    public void getAll() {
        StarDAO starDAO = instance(StarDAO.class);
        List<Star> stars = starDAO.findAll();

        assertTrue(stars.size() >= 3);
    }

}
