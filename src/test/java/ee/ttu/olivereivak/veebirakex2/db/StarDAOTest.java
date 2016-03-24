package ee.ttu.olivereivak.veebirakex2.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import ee.ttu.olivereivak.veebirakex2.model.Star;

public class StarDAOTest {

    @Test
    public void findAll() {
        StarDAO starDAO = new StarDAO();

        Star[] stars = starDAO.findAll();

        assertNotNull(stars);
        assertTrue(stars.length >= 3);

        for (Star star : stars) {
            assertNotNull(star);
            assertNotNull(star.getId());
            assertNotNull(star.getCommonName());
            assertNotNull(star.getDistanceInLightYears());
        }
    }

    @Test
    public void findById() {
        StarDAO starDAO = new StarDAO();

        Star star = starDAO.findById(1L);

        assertNotNull(star);
        assertEquals(Long.valueOf(1L), star.getId());
        assertEquals("Sirius", star.getCommonName());
        assertEquals(new BigDecimal("8.6"), star.getDistanceInLightYears());
        assertEquals("Sirius is the brightest star in the sky, after the Sun", star.getDescription());
    }

    @Test
    public void update() {
        StarDAO starDAO = new StarDAO();

        Star star = starDAO.findById(1L);

        assertNotNull(star);

        String oldDescription = star.getDescription();
        String newDescription = "Sirius is the best star.";
        star.setDescription(newDescription);

        Star returnedStar = starDAO.update(star);

        assertNotNull(returnedStar);
        assertEquals(newDescription, returnedStar.getDescription());

        Star updatedStar = starDAO.findById(1L);
        assertEquals(newDescription, updatedStar.getDescription());

        // change it back
        updatedStar.setDescription(oldDescription);
        starDAO.update(updatedStar);
    }

}
