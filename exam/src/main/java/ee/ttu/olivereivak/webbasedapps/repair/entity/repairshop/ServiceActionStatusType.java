package ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "service_action_status_type")
public class ServiceActionStatusType {

    @Id
    @Column(name = "service_action_status_type")
    private Long id;

    @Column(name = "type_name", length = 200)
    private String name;

}