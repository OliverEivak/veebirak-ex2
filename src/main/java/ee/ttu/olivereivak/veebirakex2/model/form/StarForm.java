package ee.ttu.olivereivak.veebirakex2.model.form;

public class StarForm {

    private String id;

    private String commonName;

    private String distanceInLightYears;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getDistanceInLightYears() {
        return distanceInLightYears;
    }

    public void setDistanceInLightYears(String distanceInLightYears) {
        this.distanceInLightYears = distanceInLightYears;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
