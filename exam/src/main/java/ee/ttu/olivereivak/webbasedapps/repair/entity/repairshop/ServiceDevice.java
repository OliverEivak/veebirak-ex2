package ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop;

import java.time.Instant;

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
@Table(name = "service_device")
@SequenceGenerator(name = "service_device_seq", sequenceName = "service_device_id", allocationSize = 1)
public class ServiceDevice {

    @Id
    @GeneratedValue(generator = "service_device_seq")
    @Column(name = "service_device")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_device_status_type_fk")
    private ServiceDeviceStatusType serviceDeviceStatusType;

    @ManyToOne
    @JoinColumn(name = "device_fk")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "service_order_fk")
    private ServiceOrder serviceOrder;

    @Column(name = "to_store")
    private Instant toStore;

    @Column(name = "from_store")
    private Instant fromStore;

    @Column(name = "status_changed")
    private Instant statusChanged;

}