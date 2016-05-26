package ee.ttu.olivereivak.veebirakex2.validator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ee.ttu.olivereivak.veebirakex2.model.Star;
import ee.ttu.olivereivak.veebirakex2.model.form.StarForm;

public class StarValidator {

    private static final Logger log = LogManager.getLogger(StarValidator.class);

    public Map<String, String> validate(StarForm starForm) {
        Map<String, String> errors = new HashMap<>();

        if (starForm.getId() == null || starForm.getId().isEmpty()) {
            errors.put("id", "Star must have an id");
        }

        if (starForm.getCommonName() == null || starForm.getCommonName().isEmpty()) {
            errors.put("commonName", "Common name is required");
        }

        if (starForm.getDistanceInLightYears() == null || starForm.getDistanceInLightYears().isEmpty()) {
            errors.put("distanceInLightYears", "Distance in light years is required");
        }

        Long id = parseLong(starForm.getId());
        String commonName = starForm.getCommonName();
        BigDecimal distanceInLightYears = parseBigDecimal(starForm.getDistanceInLightYears());
        String description = starForm.getDescription();

        if (id == null && !errors.containsKey("id")) {
            errors.put("id", "Star ID is not valid");
        }

        if (commonName != null && commonName.length() > 32 && !errors.containsKey("commonName")) {
            errors.put("commonName", "Common name can be up to 32 characters long");
        }

        if (distanceInLightYears == null && !errors.containsKey("distanceInLightYears")) {
            errors.put("distanceInLightYears", "Distance in light years is not valid");
        }

        if (distanceInLightYears != null && !errors.containsKey("distanceInLightYears")
                && distanceInLightYears.compareTo(new BigDecimal(99999)) > 0) {
            errors.put("distanceInLightYears", "Distance in light years can not be greater than 99999");
        }

        if (distanceInLightYears != null && !errors.containsKey("distanceInLightYears")
                && distanceInLightYears.signum() == -1) {
            errors.put("distanceInLightYears", "Distance in light years can not be negative");
        }

        if (description != null && description.length() > 5000) {
            errors.put("description", "Description can not be longer than 5000 characters");
        }

        logErrors(errors);

        return errors;
    }

    public Star parse(StarForm starForm) {
        Map<String, String> errors = validate(starForm);

        Star star = new Star();

        if (errors.isEmpty()) {
            star.setId(parseLong(starForm.getId()));
            star.setCommonName(starForm.getCommonName());
            star.setDistanceInLightYears(parseBigDecimal(starForm.getDistanceInLightYears()));
            star.setDescription(starForm.getDescription());
        }

        return star;
    }

    private Long parseLong(String longString) {
        Long value = null;
        if (longString != null) {
            try {
                value = Long.valueOf(longString);
            } catch (NumberFormatException ignored) {

            }
        }
        return value;
    }

    private Double parseDouble(String doubleString) {
        Double value = null;
        if (doubleString != null) {
            try {
                value = Double.valueOf(doubleString);
            } catch (NumberFormatException ignored) {

            }
        }
        return value;
    }

    private BigDecimal parseBigDecimal(String decimalString) {
        BigDecimal value = null;
        if (decimalString != null) {
            try {
                value = new BigDecimal(decimalString);
            } catch (NumberFormatException ignored) {

            }
        }
        return value;
    }

    private void logErrors(Map<String, String> errors) {
        for (Map.Entry<String, String> entry : errors.entrySet()) {
            log.error(String.format("Validation error on field %s: %s", entry.getKey(), entry.getValue()));
        }
    }

}
