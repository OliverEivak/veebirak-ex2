package ee.ttu.olivereivak.webbasedapps.repair.entity.subject;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "person")
@SequenceGenerator(name = "person_seq", sequenceName = "person_id", allocationSize = 1)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Person implements Subject {

    @Id
    @GeneratedValue(generator = "person_seq")
    @Column(name = "person")
    private Long id;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Formula(value = "concat(first_name, ' ', last_name)")
    private String name;

    @Column(name = "identity_code", length = 20)
    private String ssn;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Employee creator;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Employee updater;

    private Instant created;

    private Instant updated;

}