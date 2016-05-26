package ee.ttu.olivereivak.veebirakex2.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Test;

import ee.ttu.olivereivak.veebirakex2.model.Star;
import ee.ttu.olivereivak.veebirakex2.model.form.StarForm;

public class StarValidatorTest {

    @Test
    public void validateEmptyForm() {
        StarValidator validator = new StarValidator();

        StarForm form = new StarForm();

        Map<String, String> errors = validator.validate(form);

        assertTrue(errors.size() >= 3);
    }

    @Test
    public void validateBadData() {
        StarValidator validator = new StarValidator();

        StarForm form = new StarForm();
        form.setId("asd");
        form.setCommonName("qwertyuiopqwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        form.setDistanceInLightYears("-9999999999999");

        Map<String, String> errors = validator.validate(form);

        assertEquals("Star ID is not valid", errors.get("id"));
        assertEquals("Common name can be up to 32 characters long", errors.get("commonName"));
        assertEquals("Distance in light years can not be negative", errors.get("distanceInLightYears"));
    }

    @Test
    public void validateGoodData() {
        StarValidator validator = new StarValidator();

        StarForm form = getGoodForm();

        Map<String, String> errors = validator.validate(form);

        assertTrue(errors.isEmpty());
    }

    @Test
    public void parse() {
        StarValidator validator = new StarValidator();

        StarForm form = getGoodForm();

        Star star = validator.parse(form);

        assertNotNull(star);
        assertEquals(Long.valueOf(22), star.getId());
        assertEquals("asdasd", star.getCommonName());
        assertTrue(new BigDecimal("89.2").compareTo(star.getDistanceInLightYears()) == 0);
        assertEquals("Cool star", star.getDescription());
    }

    private StarForm getGoodForm() {
        StarForm form = new StarForm();
        form.setId("22");
        form.setCommonName("asdasd");
        form.setDistanceInLightYears("89.2");
        form.setDescription("Cool star");
        return form;
    }

}
