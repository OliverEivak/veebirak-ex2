package ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "device")
@SequenceGenerator(name = "device_seq", sequenceName = "device_id", allocationSize = 1)
public class Device {

    @Id
    @GeneratedValue(generator = "device_seq")
    @Column(name = "device")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_type_fk")
    private DeviceType deviceType;

    @Column(columnDefinition = "text")
    private String name;

    @Column(name = "reg_no", length = 100)
    private String registrationNumber;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String model;

    @Column(columnDefinition = "text")
    private String manufacturer;

    /**
     * Set a device type for this device.
     * @param deviceType DeviceType to set.
     * @throws IllegalArgumentException if the DeviceType has a parent (or is a level 1 DeviceType)
     */
    public void setDeviceType(DeviceType deviceType) {
        if (deviceType.getParent() == null || deviceType.getLevel() == 1L) {
            throw new IllegalArgumentException("Can not set a top level DeviceType");
        }
        this.deviceType = deviceType;
    }

}