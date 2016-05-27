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

import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Customer;
import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Employee;

@Entity
@Getter
@Setter
@Table(name = "service_request")
@SequenceGenerator(name = "service_request_seq", sequenceName = "service_request_id", allocationSize = 1)
public class ServiceRequest {

    @Id
    @GeneratedValue(generator = "service_request_seq")
    @Column(name = "service_request")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_fk")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Employee creator;

    private Instant created;

    @Column(name = "service_desc_by_customer", columnDefinition = "text")
    private String descriptionByCustomer;

    @Column(name = "service_desc_by_employee", columnDefinition = "text")
    private String descriptionByEmployee;

    @ManyToOne
    @JoinColumn(name = "service_request_status_type_fk")
    private ServiceRequestStatusType serviceRequestStatusType;

}