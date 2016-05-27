package ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop;

import java.math.BigDecimal;

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
@Table(name = "service_type")
@SequenceGenerator(name = "service_type_seq", sequenceName = "service_type_id", allocationSize = 1)
public class ServiceType {

    @Id
    @GeneratedValue(generator = "service_type_seq")
    @Column(name = "service_type")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_unit_type_fk")
    private ServiceUnitType serviceUnitType;

    @Column(name = "type_name", length = 200)
    private String name;

    @Column(name = "service_price")
    private BigDecimal price;

}