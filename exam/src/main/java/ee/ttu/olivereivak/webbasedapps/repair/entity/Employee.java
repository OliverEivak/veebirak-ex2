package ee.ttu.olivereivak.webbasedapps.repair.entity;

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
@Table(name = "employee")
@SequenceGenerator(name = "employee_seq", sequenceName = "employee_id", allocationSize = 1)
public class Employee {

    @Id
    @GeneratedValue(generator = "employee_seq")
    @Column(name = "employee")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_fk")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "enterprise_fk")
    private Enterprise enterprise;

    // TODO: can we delete this from db?
    @Column(name = "struct_unit")
    private Long structureUnit;

    // TODO: this data type is retarded
    @Column(length = 1)
    private String active;

}