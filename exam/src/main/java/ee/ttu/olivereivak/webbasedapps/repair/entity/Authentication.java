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
@Table(name = "authentication")
@SequenceGenerator(name = "authentication_seq", sequenceName = "authentication_id", allocationSize = 1)
public class Authentication {

	@Id
	@GeneratedValue(generator = "authentication_seq")
	private Long id;

	@Column(nullable = false, unique = true)
	private String token;

	@ManyToOne
	@JoinColumn(name = "user_account", nullable = false)
	private UserAccount userAccount;

}
