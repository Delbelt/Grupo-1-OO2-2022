package app.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Entity // persistence
@Data // Lombok = auto-boilerplate
@Table(name="user") // database
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	
	// Variables in ascending order

	@Range(min = 10000000, max = 99999999)
	@NotNull
	@Column(name="document")
	private long document; // Example: 11.111.111
	
	@Column(name="enabled")
	private boolean enabled; // active or not
	
	@Email
	@NotEmpty
	@Column(name="email")
	private String email; // Example: AAAAA@provider.domain

	@Size(min=2, max=25)
	@Column(name="name")
	private String name; // Example: AAAAAA
	
	@Size(min=8) // Por el momento
	@Column(name="password")
	private String password; // Example: abcde11_UNLA
	
	@ManyToOne(cascade=CascadeType.PERSIST) // Relation with Role
	@JoinColumn(name="idRole")
	private UserRole role;
	
	@Size(min=2, max=30)
	@Column(name="surname")
	private String surname; // Example: BBBBBBB
	
	@Column(name="type")
	private String type; // Example: L.E/DNI - CARNET EXT.
	
	@Size(min=4, max=20)
	@Column(name="userName")
	private String userName; // Example: unlaUser
	
	/* TODO add validations and exceptions:
	 * document: length 8
	 * email: mail format
	 * name: length between 2 to 25 characters
	 * password: Minimum 8 characters, at least one uppercase letter, one lowercase letter, one number and one special character
	 * surname: length between 2 to 30 characters
	 * type: length between 2 to 60 characters
	 * userName: length between 4 to 20 characters
	 * */
}
