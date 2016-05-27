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
@Table(name = "device_type")
@SequenceGenerator(name = "device_type_seq", sequenceName = "device_type_id", allocationSize = 1)
public class DeviceType {

    @Id
    @GeneratedValue(generator = "device_type_seq")
    @Column(name = "device_type")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "super_type_fk")
    private DeviceType parent;

    private Long level = 1L;

    @Column(name = "type_name", length = 200)
    private String name;

    /**
     * Set parent DeviceType. Automatically sets the level property.
     * @param deviceType - device type to set as parent
     */
    public void setParent(DeviceType deviceType) {
        if (deviceType != null) {
            parent = deviceType;
            level = 2L;
        } else {
            level = 1L;
        }
    }

}