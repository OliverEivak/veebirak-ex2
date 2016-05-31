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
@Table(name = "service_action")
@SequenceGenerator(name = "service_action_seq", sequenceName = "service_action_id", allocationSize = 1)
public class ServiceAction {

    @Id
    @GeneratedValue(generator = "service_action_seq")
    @Column(name = "service_action")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_action_status_type_fk")
    private ServiceActionStatusType serviceActionStatusType;

    @ManyToOne
    @JoinColumn(name = "service_type_fk")
    private ServiceType serviceType;

    @ManyToOne
    @JoinColumn(name = "service_device_fk")
    private ServiceDevice serviceDevice;

//    @ManyToOne
//    @JoinColumn(name = "service_order_fk")
//    private ServiceOrder serviceOrder;

    @Column(name = "service_amount")
    private BigDecimal amount;

    private BigDecimal price;

    @Column(name = "price_updated")
    private Instant priceChanged;

    @Column(name = "action_description", columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Employee creator;

    private Instant created;

}