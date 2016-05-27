package ee.ttu.olivereivak.webbasedapps.repair.entity.subject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.MetaValue;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "customer")
@SequenceGenerator(name = "customer_seq", sequenceName = "customer_id", allocationSize = 1)
public class Customer {

    @Id
    @GeneratedValue(generator = "customer_seq")
    @Column(name = "customer")
    private Long id;

    /**
     * Experimenting with Polymorphic Association Mapping in Hibernate
     * http://javabeat.net/polymorphic-association-mapping-relationship-hibernate/
     */
    @Any(metaColumn = @Column(name = "subject_type_fk"))
    @AnyMetaDef(idType = "long", metaType = "long",
            metaValues = {
                    @MetaValue(targetEntity = Person.class, value = "1"),
                    @MetaValue(targetEntity = Enterprise.class, value = "2")
            })
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "subject_fk")
    private Subject subject;

}
