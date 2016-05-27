package ee.ttu.olivereivak.webbasedapps.repair.entity.subject;

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
@Table(name = "enterprise")
@SequenceGenerator(name = "enterprise_seq", sequenceName = "enterprise_id", allocationSize = 1)
public class Enterprise implements Subject {

    @Id
    @GeneratedValue(generator = "enterprise_seq")
    @Column(name = "enterprise")
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

    @Column(name = "full_name", columnDefinition = "text")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Employee creator;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Employee updater;

    private Instant created;

    private Instant updated;

}
