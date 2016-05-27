package ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop;

import java.math.BigDecimal;
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

import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Employee;

@Entity
@Getter
@Setter
@Table(name = "service_part")
@SequenceGenerator(name = "service_part_seq", sequenceName = "service_part_id", allocationSize = 1)
public class ServicePart {

    @Id
    @GeneratedValue(generator = "service_part_seq")
    @Column(name = "service_part")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_order_fk")
    private ServiceOrder serviceOrder;

    @ManyToOne
    @JoinColumn(name = "service_device_fk")
    private ServiceDevice serviceDevice;

    @Column(name = "part_name", columnDefinition = "text")
    private String name;

    @Column(name = "serial_no", columnDefinition = "text")
    private String serialNumber;

    @Column(name = "part_count")
    private Long count;

    @Column(name = "part_price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Employee creator;

    private Instant created;

}