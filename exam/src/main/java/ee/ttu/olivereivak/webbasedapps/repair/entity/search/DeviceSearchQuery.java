package ee.ttu.olivereivak.webbasedapps.repair.entity.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceSearchQuery {

    private String name;
    private String model;
    private String description;
    private String manufacturer;
    private String registrationNumber;
    private Long deviceType;
    private String clientName;

}