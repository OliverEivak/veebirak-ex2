package ee.ttu.olivereivak.veebirakex2.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ttu.olivereivak.veebirakex2.entity.Star;
import ee.ttu.olivereivak.veebirakex2.test.FullWebStackTestBase;

public class StarResourceITest extends FullWebStackTestBase<IStarResource> {

    private IStarResource client;

    @Before
    public void setup() {
        client = getClient(IStarResource.class);
    }

    @Test
    public void getByID() {
        Star star = client.get(1L);

        assertEquals(Long.valueOf(1L), star.getId());
        assertEquals("Sirius", star.getCommonName());
        assertTrue(BigDecimal.valueOf(8.6).compareTo(star.getDistanceInLightYears()) == 0);
        assertEquals("Sirius is the brightest star in the sky, after the Sun", star.getDescription());
    }

    @Test
    public void getAll() {
        List<Star> stars = client.getAll();

        assertTrue(stars.size() >= 3);
    }

    @Test
    public void update() {
        Star star = client.get(1L);
        assertEquals("Sirius", star.getCommonName());

        star.setCommonName("Sirius 2000");
        Star updated = client.update(star);
        assertEquals(star.getCommonName(), updated.getCommonName());
    }

}
