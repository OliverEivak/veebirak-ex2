package ee.ttu.olivereivak.webbasedapps.repair.entity;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import ee.ttu.olivereivak.webbasedapps.repair.entity.converter.SubjectTypeConverter;
import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Employee;
import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.SubjectType;

@Entity
@Getter
@Setter
@Table(name = "user_account")
@SequenceGenerator(name = "user_account_seq", sequenceName = "user_account_id", allocationSize = 1)
public class UserAccount {

    @Id
    @GeneratedValue(generator = "user_account_seq")
    @Column(name = "user_account")
    private Long id;

    // TODO: this is always 3 in this app -> subject is always an employee
    @Basic
    @Convert(converter = SubjectTypeConverter.class)
    @Column(name = "subject_type_fk")
    private SubjectType subjectType;

    @ManyToOne
    @JoinColumn(name = "subject_fk")
    private Employee employee;

    @Column(length = 50)
    private String username;

    @JsonIgnore
    @Column(name = "passw", length = 300)
    private String password;

    // TODO: this data type is retarded
    @Column(name = "status")
    private Long valid;

    @Column(name = "valid_from")
    private LocalDate validFrom;

    @Column(name = "valid_to")
    private LocalDate validTo;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Employee creator;

    private Instant created;

    // TODO: this data type is retarded
    @Column(name = "password_never_expires", length = 1)
    private String passwordNeverExpires;

}
