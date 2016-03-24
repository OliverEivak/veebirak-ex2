package ee.ttu.olivereivak.veebirakex2.model;

import java.math.BigDecimal;

public class Star {

    private Long id;

    private String commonName;

    private BigDecimal distanceInLightYears;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public BigDecimal getDistanceInLightYears() {
        return distanceInLightYears;
    }

    public void setDistanceInLightYears(BigDecimal distanceInLightYears) {
        this.distanceInLightYears = distanceInLightYears;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
